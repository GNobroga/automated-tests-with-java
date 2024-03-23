package br.com.gabriel.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.gabriel.test.infrastructure.entity.PessoaEntity;
import br.com.gabriel.test.infrastructure.repository.PessoaJpaRepository;
import jakarta.persistence.EntityManager;

@DataJpaTest
public class PessoaJpaRepositoryTest {

    @Autowired
    private PessoaJpaRepository pessoaJpaRepository;

    @Autowired
    private EntityManager entityManager;

    private static PessoaEntity pessoaEntity;

    @BeforeAll
    static void setup() {
        pessoaEntity = PessoaEntity.builder()
            .email("gabrielcardoso_stelo@hotmail.com")
            .genero("Masculino")
            .nome("Gabriel Cardoso")
            .telefone("28999505410")
            .build();
    }

    @DisplayName("Criar e retornar pessoa quando tudo estiver ok")
    @Test    
    void testCriarERetornarPessoa_Quando_TudoEstiverOk() {

        var pessoaSaved = pessoaJpaRepository.save(pessoaEntity);
        assertNotNull(pessoaSaved, () -> "A pessoa não pode ser nula");
        assertNotNull(pessoaSaved.getId(), () -> "O id do usuário não pode ser nulo");
    }   

    @DisplayName("Obtém a listagem de pessoas quando buscar todos e retorna uma lista de pessoas")
    @Test
    void obtemListagemDePessoas_quandoBuscarTodas_RetornaListaDePessoas() {


        // private Long id;

        // private String nome;
    
        // private String genero;
    
        // private String telefone;
    
        // private String email;
        var pessoas = new ArrayList<PessoaEntity>() 
        {
            {
                add(new PessoaEntity(null, "Gabriel1", "Masculino", "28999505410", "gabrielcardoso_stelo@hotmail.com"));
                add(new PessoaEntity(null, "Gabriel2", "Masculino", "28999505410", "gabrielcardoso_stelo@hotmail.com"));
                add(new PessoaEntity(null, "Gabriel3", "Masculino", "28999505410", "gabrielcardoso_stelo@hotmail.com"));
            }
        };

        var pessoasListadas = pessoaJpaRepository.saveAll(pessoas);

        assertNotNull(pessoasListadas, () -> "As pessoas listadas não podem ser nulas");
        assertTrue(pessoasListadas.size() == 3, () -> "O tamanho deve ser igual a 3");

        assertTrue(pessoas.stream().allMatch(x -> x.getId() != null), () -> "As pessoas precisam ter ids");

    }


    @DisplayName("Filtering person when email or password is passed and return person list")
    @Test
    void testFilteringPerson_WhenEmailOrPasswordIsPassed_ReturnPersonList() {
        var pessoas = new ArrayList<PessoaEntity>() 
        {
            {
                add(new PessoaEntity(null, "Gabriel1", "Masculino", "28999505410", "gabrielcardoso_stelo@hotmail.com"));
                add(new PessoaEntity(null, "Gabriel2", "Feminino", "28999505410", "gabrielcardoso_stelo@hotmail.com"));
                add(new PessoaEntity(null, "Gabriel3", "Masculino", "28999505410", "gabrielcardos_stelo@hotmail.com"));
            }
        };

        pessoaJpaRepository.saveAll(pessoas);

        var list = pessoaJpaRepository.findByEmailOrGenero("gabrielcardoso_stelo@hotmail.com", "Feminino");

        assertTrue(list.size() == 2, "A lista de pessoas deve ter 2");
    }
    
    @DisplayName("Get all person when is greather then 1")
    @Test
    void testGetAllPerson_WhenIsGretherThen1_ReturnPersonList() {
        var pessoas = new ArrayList<PessoaEntity>() 
        {
            {
                add(new PessoaEntity(null, "Gabriel1", "Masculino", "28999505410", "gabrielcardoso_stelo@hotmail.com"));
                add(new PessoaEntity(null, "Gabriel2", "Feminino", "28999505410", "gabrielcardoso_stelo@hotmail.com"));
                add(new PessoaEntity(null, "Gabriel3", "Masculino", "28999505410", "gabrielcardos_stelo@hotmail.com"));
            }
        };

        
        pessoaJpaRepository.saveAll(pessoas);

        var result = entityManager.createNamedQuery("getAllWhenGreatherThenId1", PessoaEntity.class);

        result.setParameter("nome", "Gabriel1");

        var list = result.getResultList();

        assertTrue(list.size() == 1, "A lista de pessoas de ter 2 pessoas, currentSize = %d");

    }
}
