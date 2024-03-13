package br.com.cdb.digitalbank.dto;

import br.com.cdb.digitalbank.model.Pix;
import br.com.cdb.digitalbank.model.enums.KeyType;

public record PixDTO(KeyType type, Long customerId) {
}
