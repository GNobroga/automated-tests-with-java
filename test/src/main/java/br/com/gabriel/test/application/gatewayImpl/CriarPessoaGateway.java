package br.com.gabriel.test.application.gatewayImpl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import br.com.gabriel.test.application.mapper.PessoaMapper;
import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.exception.DomainInvalidException;
import br.com.gabriel.test.gateway.ICriarPessoaGateway;
import br.com.gabriel.test.infrastructure.entity.PessoaEntity;
import br.com.gabriel.test.infrastructure.repository.PessoaJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriarPessoaGateway implements ICriarPessoaGateway {

    private final PessoaJpaRepository pessoaJpaRepository;

    private final PessoaMapper pessoaMapper;

    @Override
    public Pessoa criar(Pessoa pessoa) throws DomainInvalidException {
      var pessoaSaved = pessoaJpaRepository.save(pessoaMapper.toEntity(pessoa));
      return pessoaMapper.toPessoa(pessoaSaved);
    }

    @Override
    public boolean existsEmail(String email) {
        var pessoaEntity = PessoaEntity.builder().email(email).build();
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<PessoaEntity> example = Example.of(pessoaEntity, exampleMatcher);
        return pessoaJpaRepository.findBy(example, q -> q.exists());
    }
    
}
