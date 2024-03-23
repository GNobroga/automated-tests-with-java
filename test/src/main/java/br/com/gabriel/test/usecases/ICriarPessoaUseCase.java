package br.com.gabriel.test.usecases;

import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.exception.DomainInvalidException;

public interface ICriarPessoaUseCase {
    
    Pessoa executar(Pessoa pessoa) throws DomainInvalidException;
}
