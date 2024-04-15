package br.com.cdb.digitalbank.dto;

import br.com.cdb.digitalbank.model.Customer;
import br.com.cdb.digitalbank.model.enums.AccountType;
import br.com.cdb.digitalbank.model.enums.CustomerType;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record AccountDTO(@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Nome inválido") @Size(min = 2, max = 100) String name,
                         @Email(message = "Email inválido") String email,
                         @NotBlank(message = "O telefone deve ser informado") String phone,
                         @CPF(message = "CPF inválido") String cpf,
                         @Past(message = "Data de nascimento inválido") LocalDate birthDate,
                         AddressDTO address,
                         CustomerType customerType,
                         AccountType accountType) {

    public Customer toCustomer() {
        return new Customer(name, email, phone, cpf, birthDate, address.toAddress(), customerType);
    }
}