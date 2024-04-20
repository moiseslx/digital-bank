package br.com.cdb.digitalbank.service.exceptions;

import java.util.List;

public class MultipleValidationException extends RuntimeException {

    private final List<Exception> exceptions;

    public MultipleValidationException(List<Exception> exceptions) {
        super("Ocorreram erros de validação");
        this.exceptions = exceptions;
    }

    public List<Exception> getExceptions() {
        return exceptions;
    }
}
