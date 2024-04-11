package br.com.cdb.digitalbank.controller.exceptions;

import br.com.cdb.digitalbank.service.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(EntityNotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now().toString());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Resource not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<StandardError>> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        List<StandardError> errors = fieldErrors.stream()
                .map(fieldError -> {
                    StandardError error = new StandardError();
                    error.setTimestamp(Instant.now().toString());
                    error.setStatus(HttpStatus.BAD_REQUEST.value());
                    error.setError("Validation Error");
                    error.setMessage(fieldError.getDefaultMessage());
                    error.setPath(request.getRequestURI());
                    return error;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<StandardError> duplicateCustomer(DuplicateDataException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now().toString());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Validation Error");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ExpiredCardException.class)
    public ResponseEntity<StandardError> expiredCard(ExpiredCardException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now().toString());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Validation Error");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<StandardError> insufficientBalance(InsufficientBalanceException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now().toString());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Validation Error");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(TEDUnavailableException.class)
    public ResponseEntity<StandardError> tedUnavailable(TEDUnavailableException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now().toString());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Validation Error");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now().toString());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Validation Error");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<StandardError> incorrectPassword(IncorrectPasswordException e, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now().toString());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Validation Error");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
