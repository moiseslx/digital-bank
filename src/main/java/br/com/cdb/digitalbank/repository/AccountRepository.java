package br.com.cdb.digitalbank.repository;

import br.com.cdb.digitalbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByNumber(Long number);
    Account findByCustomerId(Long customerId);
}
