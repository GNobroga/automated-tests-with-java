package br.com.gabriel.test.application.exception;

public class EmailInvalidException extends RuntimeException{
    public EmailInvalidException(String message) {
        super(message);
    }
}
