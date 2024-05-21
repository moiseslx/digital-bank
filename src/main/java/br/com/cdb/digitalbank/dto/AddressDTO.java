package br.com.cdb.digitalbank.dto;

import br.com.cdb.digitalbank.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(@NotBlank(message = "O nome da rua deve ser informado") String street,
                         @NotBlank(message = "O número de residência deve ser informado") Integer number,
                         @NotBlank(message = "O bairro deve ser informado") String neighborhood,
                         @NotBlank(message = "A cidade deve ser informada") String city,
                         @NotBlank(message = "O estado deve ser informado") String state,
                         @Pattern(regexp = "^[0-9]{5}-[0-9]{3}$", message = "CEP inválido") String zipCode) {

    public Address toAddress() {
        return new Address(street, number, neighborhood, city, state, zipCode);
    }
}
