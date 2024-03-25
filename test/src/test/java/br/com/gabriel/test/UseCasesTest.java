package br.com.gabriel.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gabriel.test.application.exception.EmailInvalidException;
import br.com.gabriel.test.application.usecaseImpl.CriarPessoaUseCase;
import br.com.gabriel.test.application.usecaseImpl.DeletarPessoaByIdUseCase;
import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.enums.PessoaGenero;
import br.com.gabriel.test.domain.exception.DomainInvalidException;
import br.com.gabriel.test.gateway.ICriarPessoaGateway;
import br.com.gabriel.test.gateway.IDeletarPessoaByIdGateway;

@ExtendWith(MockitoExtension.class)
public class UseCasesTest {

    @Mock
    private ICriarPessoaGateway criarPessoaGateway;

    @InjectMocks
    private CriarPessoaUseCase criarPessoaUseCase;

    @Mock
    private IDeletarPessoaByIdGateway deletarPessoaByIdGateway;

    @InjectMocks
    private DeletarPessoaByIdUseCase deletarPessoaByIdUseCase; 

    @Test
    void testCreatePerson_WhenUseCaseIsCalled_ReturnPerson() throws DomainInvalidException {
        // Given / Arrange
        // When / Act
        // Then / Assert
        
        // Given / Arrange
        var pessoa = new Pessoa("Gabriel", PessoaGenero.MASCULINO, "28999505410", "gabrielcardoso_stelo@hotmail.com");
        var createdPessoa = new Pessoa(1L, "Gabriel", PessoaGenero.MASCULINO, "28999505410", "gabrielcardoso_stelo@hotmail.com");

        when(criarPessoaGateway.existsEmail(anyString())).thenReturn(false);
        when(criarPessoaGateway.criar(pessoa)).thenReturn(createdPessoa);
        
        // When / Act
        var result = criarPessoaUseCase.executar(pessoa);

        // Then / Assert
        // Verificando se o resultado retornado pelo método executar() é igual ao objeto esperado
        assertEquals(createdPessoa, result, () -> "O objeto retornado deve ser igual ao objeto criado");
    }


    @Test
    void testCreatePerson_WhenUseCaseIsCalledAndExistsEmail_ThrowDomainInvalidException() throws DomainInvalidException {
        var pessoa = new Pessoa("Gabriel", PessoaGenero.MASCULINO, "28999505410", "gabrielcardoso_stelo@hotmail.com");

        when(criarPessoaGateway.existsEmail(anyString())).thenReturn(true);

        var exception = assertThrows(EmailInvalidException.class, () -> {
            criarPessoaUseCase.executar(pessoa);
        });

        assertEquals("O e-mail já está sendo usado", exception.getMessage());

        // verify(repository, never()).save(any(Person.class));
    }

    @Test
    void testDeletePerson_WhenExistsId_ReturnVoid() {
        deletarPessoaByIdUseCase.execute(1L);
        verify(deletarPessoaByIdGateway, only()).deletaById(anyLong());
    }

    @Test
    void testDeletePerson_WhenNotExistsId_ReturnVoid() {
        var exception = assertThrows(RuntimeException.class, () -> {
            deletarPessoaByIdUseCase.execute(null);
        });
        verify(deletarPessoaByIdGateway, never()).deletaById(anyLong());
        assertEquals("The user id cannot be null", exception.getMessage());
    }



    
}
