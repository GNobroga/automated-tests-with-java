package br.com.gabriel.test.application.usecaseImpl;

import br.com.gabriel.test.application.exception.EmailInvalidException;
import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.exception.DomainInvalidException;
import br.com.gabriel.test.gateway.ICriarPessoaGateway;
import br.com.gabriel.test.usecases.ICriarPessoaUseCase;

public class CriarPessoaUseCase implements ICriarPessoaUseCase {
    
    private final ICriarPessoaGateway criarPessoaGateway;

    public CriarPessoaUseCase(ICriarPessoaGateway criarPessoaGateway) {
        this.criarPessoaGateway = criarPessoaGateway;
    }

    @Override
    public Pessoa executar(Pessoa pessoa) throws DomainInvalidException {

        if (criarPessoaGateway.existsEmail(pessoa.getEmail())) {
            throw new EmailInvalidException("O e-mail já está sendo usado");
        }

       return criarPessoaGateway.criar(pessoa);
    }


}
