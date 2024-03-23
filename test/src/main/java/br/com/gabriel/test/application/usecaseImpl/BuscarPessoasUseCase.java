package br.com.gabriel.test.application.usecaseImpl;

import java.util.List;

import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.exception.DomainInvalidException;
import br.com.gabriel.test.gateway.IBuscarPessoasGateway;
import br.com.gabriel.test.usecases.IBuscarPessoasUseCase;

public class BuscarPessoasUseCase implements IBuscarPessoasUseCase {

    private final IBuscarPessoasGateway buscarPessoasGateway;
    

    public BuscarPessoasUseCase(IBuscarPessoasGateway buscarPessoasGateway) {
        this.buscarPessoasGateway = buscarPessoasGateway;
    }

    @Override
    public List<Pessoa> executar() throws DomainInvalidException {
      return buscarPessoasGateway.buscar();
    }
    
}
