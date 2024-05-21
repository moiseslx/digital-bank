package br.com.cdb.digitalbank.repository;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.origin = :origin AND t.timestamp BETWEEN :start AND :end")
    List<Transaction> findByOriginAndTimestampBetween(Account origin, Instant start, Instant end);

    @Query("SELECT t FROM Transaction t WHERE t.origin = :account OR t.destination = :account")
    List<Transaction> findByAccount(Account account);

}
