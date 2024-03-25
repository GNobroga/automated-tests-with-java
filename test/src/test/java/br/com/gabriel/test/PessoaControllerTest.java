package br.com.gabriel.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gabriel.test.application.mapper.PessoaMapper;
import br.com.gabriel.test.application.usecaseImpl.BuscarPessoaUseCase;
import br.com.gabriel.test.application.usecaseImpl.BuscarPessoasUseCase;
import br.com.gabriel.test.application.usecaseImpl.CriarPessoaUseCase;
import br.com.gabriel.test.application.usecaseImpl.DeletarPessoaByIdUseCase;
import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.enums.PessoaGenero;
import br.com.gabriel.test.domain.exception.DomainInvalidException;
import br.com.gabriel.test.infrastructure.controller.PessoaController;
import br.com.gabriel.test.infrastructure.dto.CriarPessoaRequestDTO;
import br.com.gabriel.test.usecases.ICriarPessoaUseCase;

@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {
    
    /**
     * O MockMvc é uma classe fornecida pelo Spring Framework para testar 
     * ontroladores da camada de apresentação em aplicativos da web. 
     * Através do MockMvc, você pode simular solicitações HTTP para o seu controlador e verificar o resultado da execução.
     */
    @Autowired
    private MockMvc mockMvc;
    
    /**
     * A anotação @MockBean é usada para criar e injetar um mock no contexto do aplicativo Spring.
     * Ela substitui qualquer bean existente do mesmo tipo pelo mock configurado. Esses mocks são então injetados 
     * nas classes que precisam deles durante os testes.
     */
    @MockBean 
    private CriarPessoaUseCase mockCriarPessoaUseCase;

    @MockBean 
    private BuscarPessoaUseCase buscarPessoaUseCase;

    @MockBean
    private BuscarPessoasUseCase buscarPessoasUseCase;

    @MockBean
    private DeletarPessoaByIdUseCase deletarPessoaByIdUseCase;

    @MockBean
    private PessoaMapper pessoaMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private static Pessoa pessoa;


    @BeforeAll
    static void setup() throws DomainInvalidException {
        pessoa = new Pessoa("Gabriel Cardoso", PessoaGenero.MASCULINO, "28999505410", "gabrielcardoso_stelo@hotmail.com");
    }

    //public record CriarPessoaRequestDTO(String nome, String genero, String telefone, String email) 
    
    @Test
    void testCreatePerson_WhenPostMEthodIsInvoked_thenReturnCreatedPerson() throws Exception {
        
        var criarPessoaRequest = new CriarPessoaRequestDTO(pessoa.getNome(), pessoa.getGenero().getValor(), pessoa.getTelefone(), pessoa.getEmail());

        when(pessoaMapper.toPessoa(any(CriarPessoaRequestDTO.class))).thenReturn(pessoa);

        when(mockCriarPessoaUseCase.executar(any(Pessoa.class))).then(invocation -> {
            Pessoa pessoa = invocation.getArgument(0);
            pessoa.setId(1L);
            return pessoa;
        });

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/pessoas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(criarPessoaRequest))
        )
        // Fazendo Asserções
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(pessoa.getNome()));
    }

    @Test
    void testFindAllPerson_WhenGetIsRequested_ReturnPerson() throws Exception {


        String expected = objectMapper.writeValueAsString(new ArrayList<Pessoa>() {
            {
                add(pessoa);
                add(pessoa);
                add(pessoa);
                add(pessoa);
                add(pessoa);
                add(pessoa);
                add(pessoa);
            }
        });
        
        when(buscarPessoasUseCase.executar()).thenReturn(new ArrayList<Pessoa>() {
            {
                add(pessoa);
                add(pessoa);
                add(pessoa);
                add(pessoa);
                add(pessoa);
                add(pessoa);
                add(pessoa);
            }
        });

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pessoas"))
            .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
            .andExpect(MockMvcResultMatchers.content().json(expected))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
}
