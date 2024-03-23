package br.com.gabriel.test.application.mapper;

import org.springframework.stereotype.Component;

import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.enums.PessoaGenero;
import br.com.gabriel.test.domain.exception.DomainInvalidException;
import br.com.gabriel.test.infrastructure.dto.CriarPessoaRequestDTO;
import br.com.gabriel.test.infrastructure.entity.PessoaEntity;

@Component
public class PessoaMapper {

    public PessoaEntity toEntity(Pessoa pessoa) {
        return PessoaEntity.builder()
            .id(pessoa.getId())
            .email(pessoa.getEmail())
            .nome(pessoa.getNome())
            .genero(pessoa.getGenero().getValor())
            .telefone(pessoa.getTelefone())
            .build();
    }

    public Pessoa toPessoa(PessoaEntity pessoaEntity) throws DomainInvalidException {
        return new Pessoa(
            pessoaEntity.getId(), 
            pessoaEntity.getNome(), 
            PessoaGenero.valueOf(pessoaEntity.getGenero().toUpperCase()),
            pessoaEntity.getTelefone(),
            pessoaEntity.getEmail());
    }


    public Pessoa toPessoa(CriarPessoaRequestDTO criarPessoaRequestDTO) throws DomainInvalidException {
        return new Pessoa(
                criarPessoaRequestDTO.nome(), 
                PessoaGenero.valueOf(criarPessoaRequestDTO.genero().toUpperCase()), 
                criarPessoaRequestDTO.telefone(),
                criarPessoaRequestDTO.email()
            );
    }
}
