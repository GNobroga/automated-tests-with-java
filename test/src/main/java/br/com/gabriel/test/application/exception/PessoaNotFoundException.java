package br.com.gabriel.test.application.exception;

public class PessoaNotFoundException extends RuntimeException{
    public PessoaNotFoundException(String message) {
        super(message);
    }
}
