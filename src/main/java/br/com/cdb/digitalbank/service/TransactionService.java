package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.*;
import br.com.cdb.digitalbank.model.enums.CustomerType;
import br.com.cdb.digitalbank.model.enums.TransactionType;
import br.com.cdb.digitalbank.repository.TransactionRepository;

import br.com.cdb.digitalbank.service.exceptions.ExpiredCardException;
import br.com.cdb.digitalbank.service.exceptions.IncorrectPasswordException;
import br.com.cdb.digitalbank.service.exceptions.InsufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Serviço de transferência de pix
    public Transaction execute(Account origin, Pix destination, BigDecimal amount) {

        validatePix(origin, amount);

        origin.setBalance(origin.getBalance().subtract(amount));
        destination.getAccount().setBalance(destination.getAccount().getBalance().add(amount));

        return transactionRepository.save(new Transaction(TransactionType.PIX, amount, origin, destination.getAccount(), Instant.now(), BigDecimal.ZERO));
    }

    //Serviço de transferência de cartão de débito
    public Transaction execute(DebitCard origin, Account destination, BigDecimal amount, String password) {
        if (origin.getPassword().equals(password)) {
            BigDecimal tax = calculateTax(origin.getAccount(), amount, TransactionType.DEBIT);

            validateDebitCard(origin, amount, tax);

            origin.getAccount().setBalance(origin.getAccount().getBalance().subtract(amount.add(tax)));
            destination.setBalance(destination.getBalance().add(amount));

            Transaction transaction = new Transaction();
            transaction.setType(TransactionType.DEBIT);
            transaction.setAmount(amount);
            transaction.setTax(tax);
            transaction.setOrigin(origin.getAccount());
            transaction.setDestination(destination);
            transaction.setTimestamp(Instant.now());

            return transactionRepository.save(transaction);
        }

        throw new IncorrectPasswordException("Senha inválida");
    }

    // Serviço de transferência de cartão de crédito
    public Transaction execute(CreditCard origin, Account destination, BigDecimal amount, String password) {
        if (!origin.getPassword().equals(password)) {
            BigDecimal tax = calculateTax(origin.getAccount(), amount, TransactionType.CREDIT);
            validateCreditCard(origin, amount, tax);

            origin.setUsedLimit(origin.getUsedLimit().add(amount.add(tax)));
            destination.setBalance(destination.getBalance().add(amount));

            Invoice lastInvoice = origin.getInvoices().get(origin.getInvoices().size() - 1);

            lastInvoice.setInvoiceValue(lastInvoice.getInvoiceValue().add(amount.add(tax)));

            Transaction transaction = new Transaction();
            transaction.setType(TransactionType.CREDIT);
            transaction.setAmount(amount);
            transaction.setOrigin(origin.getAccount());
            transaction.setDestination(destination);
            transaction.setTimestamp(Instant.now());

            BigDecimal newUsedLimit = origin.getUsedLimit().add(amount);
            origin.setUsedLimit(newUsedLimit);

            return transactionRepository.save(transaction);
        }

        throw new IncorrectPasswordException("Senha inválida");
    }

    private void validateCreditCard(CreditCard origin, BigDecimal amount, BigDecimal tax) {
        if (!origin.isActive()) {
            throw new IllegalArgumentException("Cartão de crédito inativo");
        }

        //Validar se a fatura do cartão de crédito foi ultrapassada. Se sim, uma exceção de "Fatura ultrapassada" é gerada.
        //A condicional verifica a fatura atual, ou seja, a última da lista
        //Se a fatura estiver ultrapassada, a transação não pode ser efetuada
        //E o cartão de crédito deve ser desativado
        if (origin.getInvoices().get(origin.getInvoices().size() - 1).isPastDue()) {
            origin.setActive(false);
            throw new IllegalArgumentException("Fatura ultrapassada");
        }

        //Verifica se o limite de crédito disponível no cartão é suficiente para realizar a transação. Se não for, uma exceção de "Limite insuficiente" é lançada.
        if (origin.getLimitCard().compareTo(amount.add(tax)) < 0) {
            throw new InsufficientBalanceException("Limite insuficiente");
        }

        //Verifica se o cartão de crédito ainda está ativo. Se não estiver, uma exceção de "Cartão expirado ou inativo" é gerada.
        if (origin.getExpirationDate().isBefore(LocalDate.now()) || !origin.isActive()) {
            throw new ExpiredCardException("Cartão expirado ou inativo");
        }

        List<Transaction> transactions = transactionRepository.findByOriginAndTimestampBetween(origin.getAccount(), Instant.now().minus(1, ChronoUnit.DAYS), Instant.now());
        BigDecimal sum = transactions.stream().filter(t -> t.getType().equals(TransactionType.CREDIT)).map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        //Verifica se o limite diário disponível no cartão é suficiente para realizar a transação. Se não for, uma exceção de "Limite diário insuficiente" é gerada.
        if (origin.getDailyLimit().compareTo(amount) < 0 || sum.add(amount).compareTo(origin.getDailyLimit()) > 0) {
            throw new InsufficientBalanceException("Limite diário insuficiente");
        }
    }

    private void validateDebitCard(DebitCard origin, BigDecimal amount, BigDecimal tax) {
        if (!origin.isActive()) {
            throw new IllegalArgumentException("Cartão de crédito inativo");
        }

        if (origin.getAccount().getBalance().compareTo(amount.add(tax)) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente para transferência");
        }

        if (origin.getExpirationDate().isBefore(LocalDate.now()) || !origin.isActive()) {
            throw new ExpiredCardException("Cartão expirado ou inativo");
        }

        List<Transaction> transactions = transactionRepository.findByOriginAndTimestampBetween(origin.getAccount(), Instant.now().minus(1, ChronoUnit.DAYS), Instant.now());
        BigDecimal sum = transactions.stream().filter(t -> t.getType().equals(TransactionType.DEBIT)).map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (origin.getDailyLimit().compareTo(amount) < 0 || sum.add(amount).compareTo(origin.getDailyLimit()) > 0) {
            throw new InsufficientBalanceException("Limite diário insuficiente");
        }
    }

    private void validatePix(Account origin, BigDecimal amount) {
        if (origin.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente para transferência");
        }
    }

    private BigDecimal calculateTax(Account account, BigDecimal amount, TransactionType type) {
        if (account.getCustomer().getType().equals(CustomerType.PREMIUM)) {
            return switch (type) {
                case DEBIT -> amount.multiply(BigDecimal.valueOf(0.02)); // Tem uma taxa de 2% para débito
                case CREDIT -> amount.multiply(BigDecimal.valueOf(0.03)); // Tem uma taxa de 3% para crédito
                case TED -> amount.multiply(BigDecimal.valueOf(0.04)); // Tem uma taxa de 4% para TED
                default -> BigDecimal.ZERO;
            };
        }

        if (account.getCustomer().getType().equals(CustomerType.SUPER)) {
            return switch (type) {
                case DEBIT -> amount.multiply(BigDecimal.valueOf(0.04)); // Tem uma taxa de 4% para débito
                case CREDIT -> amount.multiply(BigDecimal.valueOf(0.06)); // Tem uma taxa de 6% para crédito
                case TED -> amount.multiply(BigDecimal.valueOf(0.08)); // Tem uma taxa de 8% para TED
                default -> BigDecimal.ZERO;
            };
        }

        if (account.getCustomer().getType().equals(CustomerType.COMMON)) {
            return switch (type) {
                case DEBIT -> amount.multiply(BigDecimal.valueOf(0.08)); // Tem uma taxa de 8% para débito
                case CREDIT -> amount.multiply(BigDecimal.valueOf(0.12)); // Tem uma taxa de 12% para crédito
                case TED -> amount.multiply(BigDecimal.valueOf(0.16)); // Tem uma taxa de 16% para TED
                default -> BigDecimal.ZERO;
            };
        }

        return BigDecimal.ZERO;
    }

    public List<Transaction> findAllByAccountId(Account account) {
        return transactionRepository.findByAccount(account);
    }
}