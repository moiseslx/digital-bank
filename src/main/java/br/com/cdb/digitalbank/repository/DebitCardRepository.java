package br.com.cdb.digitalbank.repository;

import br.com.cdb.digitalbank.model.Card;
import br.com.cdb.digitalbank.model.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebitCardRepository extends JpaRepository<DebitCard, Long> {

    DebitCard findByCardNumber(String cardNumber);
}
