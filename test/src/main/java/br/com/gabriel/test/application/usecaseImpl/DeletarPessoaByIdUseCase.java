package br.com.gabriel.test.application.usecaseImpl;

import br.com.gabriel.test.gateway.IDeletarPessoaByIdGateway;
import br.com.gabriel.test.usecases.IDeletePessoaByIdUseCase;

public class DeletarPessoaByIdUseCase implements IDeletePessoaByIdUseCase {

    private final IDeletarPessoaByIdGateway deletarPessoaByIdGateway;

    public DeletarPessoaByIdUseCase(IDeletarPessoaByIdGateway deletarPessoaByIdGateway) {
        this.deletarPessoaByIdGateway = deletarPessoaByIdGateway;
    }

    @Override
    public void execute(Long id) {
       if (id == null) {
        throw new RuntimeException("The user id cannot be null");
       }

       deletarPessoaByIdGateway.deletaById(id);
    }
    
}
