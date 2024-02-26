package br.com.cdb.digitalbank.dto;

import br.com.cdb.digitalbank.model.Customer;

import java.time.LocalDate;

public record CustomerDTO(String name, String email, String phone, String cpf, LocalDate birthDate, AddressDTO address) {

    public Customer toCustomer() {
        return new Customer(name, email, phone, cpf, birthDate, address.toAddress());
    }
}