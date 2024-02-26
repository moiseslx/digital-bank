package br.com.cdb.digitalbank.dto;

import br.com.cdb.digitalbank.model.Address;

public record AddressDTO(String street, Integer number, String neighborhood, String city, String state, String zipCode) {

    public Address toAddress() {
        return new Address(street, number, neighborhood, city, state, zipCode);
    }
}
