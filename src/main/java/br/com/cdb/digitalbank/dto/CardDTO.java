package br.com.cdb.digitalbank.dto;

import br.com.cdb.digitalbank.model.enums.CardType;

public record CardDTO(Long accountId, CardType cardType, String password) {}
