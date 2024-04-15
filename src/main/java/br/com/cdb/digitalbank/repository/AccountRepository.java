package br.com.cdb.digitalbank.repository;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByNumber(Long number);
    Account findByCustomerId(Long customerId);

    @Query(value = "SELECT * FROM tb_account WHERE AGE(current_date, creation_date) >= INTERVAL '1 month'", nativeQuery = true)
    List<Account> findByAccountsCompleteAnotherMonth();
}
