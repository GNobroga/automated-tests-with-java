package br.com.gabriel.test.application.gatewayImpl;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.gabriel.test.application.mapper.PessoaMapper;
import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.exception.DomainInvalidException;
import br.com.gabriel.test.gateway.IBuscarPessoaGateway;
import br.com.gabriel.test.infrastructure.repository.PessoaJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscarPessoaGateway implements IBuscarPessoaGateway {

    private final PessoaJpaRepository pessoaJpaRepository;

    private final PessoaMapper pessoaMapper;

    @Override
    public Pessoa getById(Long id) throws DomainInvalidException {
        Assert.notNull(id, "O id não pode ser nulo dentro de BuscarPessoaGateway");
        var pessoaEntity = pessoaJpaRepository.findById(id);
        if (pessoaEntity.isPresent()) {
            return pessoaMapper.toPessoa(pessoaEntity.get());
        }
        return null;
        
    }
    
}
