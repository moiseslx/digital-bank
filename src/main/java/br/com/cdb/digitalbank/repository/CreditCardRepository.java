package br.com.cdb.digitalbank.repository;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.Card;
import br.com.cdb.digitalbank.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    CreditCard findByCardNumber(String cardNumber);

    CreditCard findByAccount(Account account);
}
