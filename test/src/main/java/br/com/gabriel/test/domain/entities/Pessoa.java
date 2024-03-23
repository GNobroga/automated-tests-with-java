package br.com.gabriel.test.domain.entities;

import java.util.regex.Pattern;

import br.com.gabriel.test.domain.enums.PessoaGenero;
import br.com.gabriel.test.domain.exception.DomainInvalidException;

public class Pessoa {
    
    private Long id;

    private String nome;

    private PessoaGenero genero;

    private String telefone;

    private String email;

    public Pessoa(Long id, String nome, PessoaGenero genero, String telefone, String email) throws DomainInvalidException {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.telefone = telefone;
        this.email = email;
        this.validate();
    }

    public Pessoa(String nome, PessoaGenero genero, String telefone, String email) throws DomainInvalidException {
        this.nome = nome;
        this.genero = genero;
        this.telefone = telefone;
        this.email = email;
        this.validate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws DomainInvalidException {
        this.nome = nome;
        this.validate();
    }

    public PessoaGenero getGenero() {
        return genero;
    }

    public void setGenero(PessoaGenero genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws DomainInvalidException {
        this.email = email;
        this.validate();
    }

    private void validate() throws DomainInvalidException {
        if (nome == null || nome.isEmpty()) {
            throw new DomainInvalidException("Nome não pode ser nulo ou vazio.");
        }
       
        if (!Pattern.compile("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$").matcher(email).matches()) {
            throw new DomainInvalidException("O e-mail inválido");
        }
    }


}
