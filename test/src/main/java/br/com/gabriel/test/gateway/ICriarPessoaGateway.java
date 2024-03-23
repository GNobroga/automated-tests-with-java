package br.com.gabriel.test.gateway;

import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.exception.DomainInvalidException;

public interface ICriarPessoaGateway {
    Pessoa criar(Pessoa pessoa) throws DomainInvalidException;

    boolean existsEmail(String email);
}
