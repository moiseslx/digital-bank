package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.DebitCard;
import br.com.cdb.digitalbank.model.enums.CardType;
import br.com.cdb.digitalbank.repository.DebitCardRepository;
import br.com.cdb.digitalbank.service.exceptions.EntityNotFoundException;
import br.com.cdb.digitalbank.service.exceptions.IncorrectPasswordException;
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

        card.setDailyLimit(new BigDecimal("100.00"));

        return debitCardRepository.save(card);
    }

    public DebitCard findById(Long id) {
        return debitCardRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cartão de débito não encontrada com id: " + id)
        );
    }

    public DebitCard updateDailyLimit(Long id, BigDecimal dailyLimit) {
        return debitCardRepository.findById(id).map(debitCard -> {
            debitCard.setDailyLimit(dailyLimit);
            return debitCardRepository.save(debitCard);
        }).orElseThrow(() -> new EntityNotFoundException("Cartão de crédito não encontrada com id: " + id));
    }

    public void disable(Long id) {
        debitCardRepository.findById(id).ifPresent(debitCard -> debitCard.setActive(false));
    }

    public void changePassword(Long id, String password, String newPassword) {
        debitCardRepository.findById(id).ifPresent(debitCard -> {
            if (!debitCard.getPassword().equals(password)) {
                throw new IncorrectPasswordException("Senha inválida");
            }
            debitCard.setPassword(newPassword);
        });
    }
}
