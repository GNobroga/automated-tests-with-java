package br.com.gabriel.test.usecases;

import java.util.List;

import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.exception.DomainInvalidException;

public interface IBuscarPessoasUseCase {
    
    List<Pessoa> executar() throws DomainInvalidException;
}
