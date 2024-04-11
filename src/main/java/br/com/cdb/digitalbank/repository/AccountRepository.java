package br.com.cdb.digitalbank.repository;

import br.com.cdb.digitalbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByNumber(Long number);
    Account findByCustomerId(Long customerId);
}
