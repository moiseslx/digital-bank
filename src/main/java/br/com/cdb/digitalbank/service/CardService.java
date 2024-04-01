package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.Card;
import br.com.cdb.digitalbank.model.CreditCard;
import br.com.cdb.digitalbank.model.DebitCard;
import br.com.cdb.digitalbank.model.enums.CardType;
import br.com.cdb.digitalbank.repository.CreditCardRepository;
import br.com.cdb.digitalbank.repository.DebitCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;


@Service
public class CardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private DebitCardRepository debitCardRepository;

    public Card save(Account account, CardType cardType, String password) {
        return cardType.equals(CardType.CREDIT) ? saveCreditCard(account, password) : saveDebitCard(account, password);
    }

    private CreditCard saveCreditCard(Account account, String password) {
        CreditCard card = new CreditCard();

        card.setAccount(account);
        BigDecimal limitCard;
        BigDecimal dailyLimit;
        card.setCardNumber(generateCardNumber());

        switch (account.getCustomer().getType()) {
            case SUPER -> {
                limitCard = BigDecimal.valueOf(1000.00);
                dailyLimit = BigDecimal.valueOf(500.00);
            }
            case PREMIUM -> {
                limitCard = BigDecimal.valueOf(500.00);
                dailyLimit = BigDecimal.valueOf(250.00);
            }
            default -> {
                limitCard = BigDecimal.valueOf(100.00);
                dailyLimit = BigDecimal.valueOf(50.00);
            }
        }

        card.setCardType(CardType.CREDIT);
        card.setDailyLimit(dailyLimit);
        card.setLimitCard(limitCard);
        card.setActive(true);
        card.setExpirationDate(LocalDate.now().plusYears(5));
        card.setUsedLimit(BigDecimal.ZERO);


        return creditCardRepository.save(card);
    }

    private DebitCard saveDebitCard(Account account, String password) {
        DebitCard card = new DebitCard();
        card.setAccount(account);
        card.setCardType(CardType.DEBIT);
        card.setActive(true);
        card.setExpirationDate(LocalDate.now().plusYears(5));
        card.setCardNumber(generateCardNumber());

        switch (account.getCustomer().getType()) {
            case SUPER -> card.setDailyLimit(BigDecimal.valueOf(500.00));
            case PREMIUM -> card.setDailyLimit(BigDecimal.valueOf(250.00));
            default -> card.setDailyLimit(BigDecimal.valueOf(50.00));
        }

        return debitCardRepository.save(card);
    }

    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        cardNumber.append((random.nextInt(9) + 1));

        for (int i = 0; i < 15; i++) {
            cardNumber.append(random.nextInt(10));
        }

        return cardNumber.toString();
    }
}
