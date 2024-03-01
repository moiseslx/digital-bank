package br.com.cdb.digitalbank.dto;

import br.com.cdb.digitalbank.model.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record CustomerDTO(@NotBlank(message = "O nome deve ser informado") String name,
                          @Email(message = "Email inválido") String email,
                          @NotBlank(message = "O telefone deve ser informado") String phone,
                          @CPF(message = "CPF inválido") String cpf,
                          @Past(message = "Data de nascimento inválido") LocalDate birthDate, AddressDTO address) {

    public Customer toCustomer() {
        return new Customer(name, email, phone, cpf, birthDate, address.toAddress());
    }
}