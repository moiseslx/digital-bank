package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.CreditCard;
import br.com.cdb.digitalbank.model.enums.CardType;
import br.com.cdb.digitalbank.repository.CreditCardRepository;
import br.com.cdb.digitalbank.service.util.GenerateCardNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard save(Account account, String password) {
        CreditCard card = new CreditCard();

        card.setAccount(account);
        BigDecimal limitCard;
        BigDecimal dailyLimit;
        card.setCardNumber(GenerateCardNumber.execute());

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
        card.setPassword(password);


        return creditCardRepository.save(card);
    }

    public void disable(Long id) {
        creditCardRepository.findById(id).ifPresent(creditCard -> creditCard.setActive(false));
    }
}
