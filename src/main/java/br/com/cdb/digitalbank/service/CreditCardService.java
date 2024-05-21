package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.CreditCard;
import br.com.cdb.digitalbank.model.Invoice;
import br.com.cdb.digitalbank.model.enums.CardType;
import br.com.cdb.digitalbank.repository.CreditCardRepository;
import br.com.cdb.digitalbank.service.exceptions.DuplicateDataException;
import br.com.cdb.digitalbank.service.exceptions.EntityNotFoundException;
import br.com.cdb.digitalbank.service.exceptions.IncorrectPasswordException;
import br.com.cdb.digitalbank.service.util.GenerateNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard save(Account account, String password) {
        if (creditCardRepository.findByAccount(account) != null) {
            throw new DuplicateDataException("Cartão de crédito existente para a conta: " + account.getId());
        }

        CreditCard card = new CreditCard();

        card.setAccount(account);
        BigDecimal limitCard;
        card.setCardNumber(GenerateNumber.generateCardNumber());

        switch (account.getCustomer().getType()) {
            case SUPER -> limitCard = BigDecimal.valueOf(5000.00);
            case PREMIUM -> limitCard = BigDecimal.valueOf(10000.00);
            default -> limitCard = BigDecimal.valueOf(1000.00);
        }
        card.setCardType(CardType.CREDIT);
        card.setDailyLimit(new BigDecimal("100.00"));
        card.setLimitCard(limitCard);
        card.setActive(true);
        card.setExpirationDate(LocalDate.now().plusYears(5));
        card.setUsedLimit(BigDecimal.ZERO);
        card.setPassword(password);

        card.getInvoices().add(new Invoice(
                LocalDate.now().plusMonths(1).minusDays(5), // A data de abertura deve ser 5 dias antes da data de fechamento, permitindo o pagamento durante este prazo.
                LocalDate.now().plusMonths(1), // Fechando o mês seguinte gerando bloqueio do cartão
                false)); // boolean que diz se a fatura foi paga ou não

        return creditCardRepository.save(card);
    }

    public void disable(Long id) {
        creditCardRepository.findById(id).ifPresent(creditCard -> creditCard.setActive(false));
    }

    public CreditCard findById(Long id) {
        return creditCardRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cartão de crédito não encontrada com id: " + id));
    }

    public void changePassword(Long id, String password, String newPassword) {
        creditCardRepository.findById(id).ifPresent(creditCard -> {
            if (!creditCard.getPassword().equals(password)) {
                throw new IncorrectPasswordException("Senha inválida");
            }
            creditCard.setPassword(newPassword);
        });
    }

    public CreditCard updateDailyLimit(Long id, BigDecimal dailyLimit) {
        return creditCardRepository.findById(id).map(creditCard -> {
            creditCard.setDailyLimit(dailyLimit);
            return creditCardRepository.save(creditCard);
        }).orElseThrow(() -> new EntityNotFoundException("Cartão de crédito não encontrada com id: " + id));
    }


    public BigDecimal getLimit(Long id) {
        //Retorna quanto restou de limite disponível para o cartão de crédito
        return creditCardRepository.findById(id).map(creditCard -> creditCard.getLimitCard().subtract(creditCard.getUsedLimit())).orElse(BigDecimal.ZERO);
    }
}
