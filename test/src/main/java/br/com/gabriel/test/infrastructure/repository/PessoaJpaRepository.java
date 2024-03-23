package br.com.gabriel.test.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.gabriel.test.infrastructure.entity.PessoaEntity;

public interface PessoaJpaRepository extends JpaRepository<PessoaEntity, Long>, JpaSpecificationExecutor<PessoaEntity> {
     
}
