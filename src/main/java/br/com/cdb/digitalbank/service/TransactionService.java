package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.Transaction;
import br.com.cdb.digitalbank.model.enums.TransactionType;
import br.com.cdb.digitalbank.repository.TransactionRepository;

import br.com.cdb.digitalbank.service.exceptions.InsufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction execute(Account origin, Account destination, BigDecimal amount) {
        System.out.println("Origin: " + origin);
        System.out.println("Destination: " + destination);


        if (origin.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente");
        }

        origin.setBalance(origin.getBalance().subtract(amount));
        destination.setBalance(destination.getBalance().add(amount));

        return new Transaction(TransactionType.PIX, amount, origin, destination);
    }
}
