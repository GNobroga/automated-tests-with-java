package br.com.gabriel.test.gateway;

import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.exception.DomainInvalidException;

public interface IBuscarPessoaGateway {
    Pessoa getById(Long id) throws DomainInvalidException;
}
