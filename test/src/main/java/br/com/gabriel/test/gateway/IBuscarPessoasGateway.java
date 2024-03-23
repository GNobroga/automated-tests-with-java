package br.com.gabriel.test.gateway;

import java.util.List;

import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.exception.DomainInvalidException;

public interface IBuscarPessoasGateway {

    List<Pessoa> buscar() throws DomainInvalidException;
    
}
