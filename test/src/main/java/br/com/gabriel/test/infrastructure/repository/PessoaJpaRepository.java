package br.com.gabriel.test.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.gabriel.test.infrastructure.entity.PessoaEntity;


public interface PessoaJpaRepository extends JpaRepository<PessoaEntity, Long>, JpaSpecificationExecutor<PessoaEntity> {

    List<PessoaEntity> findByEmailOrGenero(String email, String genero);
}
