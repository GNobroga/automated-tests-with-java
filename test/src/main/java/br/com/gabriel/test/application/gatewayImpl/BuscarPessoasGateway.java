package br.com.gabriel.test.application.gatewayImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gabriel.test.application.mapper.PessoaMapper;
import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.exception.DomainInvalidException;
import br.com.gabriel.test.gateway.IBuscarPessoasGateway;
import br.com.gabriel.test.infrastructure.repository.PessoaJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscarPessoasGateway implements IBuscarPessoasGateway {

    private final PessoaJpaRepository pessoaJpaRepository;

    private final PessoaMapper pessoaMapper;

    @Override
    public List<Pessoa> buscar() throws DomainInvalidException {
        List<Pessoa> pessoas = new ArrayList<>();

        for (var pessoaEntity: pessoaJpaRepository.findAll()) {
            pessoas.add(pessoaMapper.toPessoa(pessoaEntity));
        }

        return pessoas;
    }
    
}
