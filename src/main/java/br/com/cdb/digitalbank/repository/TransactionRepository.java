package br.com.cdb.digitalbank.repository;

import br.com.cdb.digitalbank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
