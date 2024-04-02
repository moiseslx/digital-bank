package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.DebitCard;
import br.com.cdb.digitalbank.model.enums.CardType;
import br.com.cdb.digitalbank.repository.DebitCardRepository;
import br.com.cdb.digitalbank.service.util.GenerateCardNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DebitCardService {

    @Autowired
    private DebitCardRepository debitCardRepository;

    public DebitCard save(Account account, String password) {
        DebitCard card = new DebitCard();
        card.setAccount(account);
        card.setCardType(CardType.DEBIT);
        card.setActive(true);
        card.setExpirationDate(LocalDate.now().plusYears(5));
        card.setCardNumber(GenerateCardNumber.execute());
        card.setPassword(password);

        switch (account.getCustomer().getType()) {
            case SUPER -> card.setDailyLimit(BigDecimal.valueOf(500.00));
            case PREMIUM -> card.setDailyLimit(BigDecimal.valueOf(250.00));
            default -> card.setDailyLimit(BigDecimal.valueOf(50.00));
        }

        return debitCardRepository.save(card);
    }

    public DebitCard findById(Long id) {
        return debitCardRepository.findById(id).orElse(null);
    }

    public void disable(Long id) {
        debitCardRepository.findById(id).ifPresent(debitCard -> debitCard.setActive(false));
    }
}
