package br.com.gabriel.test.domain.exception;

public class DomainInvalidException extends Exception {
    
    public DomainInvalidException(String message) {
        super(message);
    }
}
