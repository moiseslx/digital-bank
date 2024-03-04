package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.Transaction;
import br.com.cdb.digitalbank.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    // TODO: Implementar transações PIX, TED, CREDIT, DEBIT, BETWEEN

    public Transaction createPixTransaction(Account originAccount, String destinationKey, BigDecimal amount) {
        // Implemente a lógica para criar uma transação PIX
        // Por exemplo, validar as contas, o saldo, etc.
        // Depois, crie e salve a transação no repositório
        return null;
    }

    public Transaction createTEDTransaction(Account originAccount, Account destinationAccount, BigDecimal amount) {
        // Implemente a lógica para criar uma transação TED
        // Por exemplo, validar as contas, o saldo, etc.
        // Depois, crie e salve a transação no repositório
        return null;
    }

    public Transaction createCreditTransaction(Account destinationAccount, BigDecimal amount) {
        // Implemente a lógica para criar uma transação de crédito
        // Por exemplo, validar a conta de destino, adicionar o valor ao saldo, etc.
        // Depois, crie e salve a transação no repositório
        return null;
    }

    public Transaction createDebitTransaction(Account originAccount, BigDecimal amount) {
        // Implemente a lógica para criar uma transação de débito
        // Por exemplo, validar a conta de origem, deduzir o valor do saldo, etc.
        // Depois, crie e salve a transação no repositório
        return null;
    }

    public Transaction createBetweenAccountTransaction(Account originAccount, Account destinationAccount, BigDecimal amount) {
        // Implemente a lógica para criar uma transação entre contas
        // Por exemplo, validar as contas, transferir o valor entre elas, etc.
        // Depois, crie e salve a transação no repositório
        return null;
    }
}
