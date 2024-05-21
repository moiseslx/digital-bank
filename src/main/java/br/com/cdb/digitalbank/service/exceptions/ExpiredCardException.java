package br.com.cdb.digitalbank.service.exceptions;

public class ExpiredCardException extends RuntimeException {
    public ExpiredCardException(String message) {
        super(message);
    }
}
