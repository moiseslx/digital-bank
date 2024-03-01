package br.com.cdb.digitalbank.dto;

import br.com.cdb.digitalbank.model.Address;
import jakarta.validation.constraints.NotBlank;

public record AddressDTO(@NotBlank(message = "O nome da rua deve ser informado") String street,
                         @NotBlank(message = "O número de residência deve ser informado") Integer number,
                         @NotBlank(message = "O bairro deve ser informado") String neighborhood,
                         @NotBlank(message = "A cidade deve ser informada") String city,
                         @NotBlank(message = "O estado deve ser informado") String state,
                         @NotBlank(message = "O CEP deve ser informado") String zipCode) {

    public Address toAddress() {
        return new Address(street, number, neighborhood, city, state, zipCode);
    }
}
