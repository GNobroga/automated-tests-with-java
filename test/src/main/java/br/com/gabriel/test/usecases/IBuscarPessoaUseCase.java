package br.com.gabriel.test.usecases;

import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.exception.DomainInvalidException;

public interface IBuscarPessoaUseCase {
    
    Pessoa executar(Long id) throws DomainInvalidException;
}
