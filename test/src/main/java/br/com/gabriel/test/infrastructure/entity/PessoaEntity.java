package br.com.gabriel.test.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pessoas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@NamedQuery(name = "getAllWhenGreatherThenId1", query = "from PessoaEntity pe where pe.nome = :nome")
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String genero;

    private String telefone;

    private String email;
}
