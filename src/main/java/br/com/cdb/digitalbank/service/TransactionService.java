package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.*;
import br.com.cdb.digitalbank.model.enums.TransactionType;
import br.com.cdb.digitalbank.repository.TransactionRepository;

import br.com.cdb.digitalbank.service.exceptions.ExpiredCardException;
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

        if (origin.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }

        origin.setBalance(origin.getBalance().subtract(amount));
        destination.getAccount().setBalance(destination.getAccount().getBalance().add(amount));

        return transactionRepository.save(new Transaction(TransactionType.PIX, amount, origin, destination.getAccount(), Instant.now()));
    }

    // Serviço de transferência de cartão de crédito
    public Transaction execute(CreditCard origin, Account destination, BigDecimal amount) {
        if (origin.getLimitCard().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Limite insuficiente");
        }

        if (origin.getExpirationDate().isBefore(LocalDate.now())) {
            throw new ExpiredCardException("Cartão expirado");
        }

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.CREDIT);
        transaction.setAmount(amount);
        transaction.setOrigin(origin.getAccount());
        transaction.setDestination(destination);
        transaction.setTimestamp(Instant.now());

        BigDecimal newUsedLimit = origin.getUsedLimit().add(amount);
        origin.setUsedLimit(newUsedLimit);

        return transaction;
    }

    //Serviço de transferência de cartão de débito
    public Transaction execute(DebitCard origin, Account destination, BigDecimal amount) {
        if (origin.getAccount().getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }

        if (origin.getExpirationDate().isBefore(LocalDate.now())) {
            throw new ExpiredCardException("Cartão expirado");
        }

        if (origin.getDailyLimit().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Limite diário insuficiente");
        }

        // TODO: Implementar a lógica do limite diário
        // TODO: Implementar taxa de transferência de cartão de debito
        List<Transaction> transactions = transactionRepository.findByOriginAndTimestampBetween(origin.getAccount(), Instant.now().minus(1, ChronoUnit.DAYS), Instant.now());
        BigDecimal sum = transactions.stream().filter(t -> t.getType().equals(TransactionType.DEBIT)).map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (sum.add(amount).compareTo(origin.getDailyLimit()) > 0) {
            throw new InsufficientBalanceException("Limite diário insuficiente");
        }

        origin.getAccount().setBalance(origin.getAccount().getBalance().subtract(amount));
        destination.setBalance(destination.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEBIT);
        transaction.setAmount(amount);
        transaction.setOrigin(origin.getAccount());
        transaction.setDestination(destination);
        transaction.setTimestamp(Instant.now());

        return transactionRepository.save(transaction);
    }

    // Serviço de transferência por TED
    public Transaction execute(Account origin, Account destination, BigDecimal amount) {
        return null;
    }
}
