package br.com.gabriel.test.application.usecaseImpl;

import br.com.gabriel.test.application.exception.PessoaNotFoundException;
import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.exception.DomainInvalidException;
import br.com.gabriel.test.gateway.IBuscarPessoaGateway;
import br.com.gabriel.test.usecases.IBuscarPessoaUseCase;

public class BuscarPessoaUseCase implements IBuscarPessoaUseCase {

    private final IBuscarPessoaGateway buscarPessoaGateway;

    public BuscarPessoaUseCase(IBuscarPessoaGateway buscarPessoaGateway) {
        this.buscarPessoaGateway = buscarPessoaGateway;
    }

    @Override
    public Pessoa executar(Long id) throws DomainInvalidException {
        var pessoa = buscarPessoaGateway.getById(id);
        if (pessoa == null) {
            throw new PessoaNotFoundException("Pessoa não encontrada");
        }
        return pessoa;
    }
    
}
