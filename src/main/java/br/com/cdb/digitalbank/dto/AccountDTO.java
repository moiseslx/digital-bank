package br.com.cdb.digitalbank.dto;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.Customer;
import br.com.cdb.digitalbank.model.enums.AccountType;

public record AccountDTO(AccountType type, Long customerId) {

    public Account toAccount(Customer customer) {
        return new Account(type, customer);
    }
}
