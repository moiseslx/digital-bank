package br.com.cdb.digitalbank.service.exceptions;

public class DuplicateDataException extends RuntimeException {

    public DuplicateDataException(String message) {
        super(message);
    }
}
