package br.com.cdb.digitalbank.dto;

import java.math.BigDecimal;

public record TransactionPixDTO(Long originId, String destinationKey, BigDecimal amount) {
}
