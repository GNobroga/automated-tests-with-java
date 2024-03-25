package br.com.gabriel.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gabriel.test.application.exception.EmailInvalidException;
import br.com.gabriel.test.application.usecaseImpl.CriarPessoaUseCase;
import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.enums.PessoaGenero;
import br.com.gabriel.test.domain.exception.DomainInvalidException;
import br.com.gabriel.test.gateway.ICriarPessoaGateway;

@ExtendWith(MockitoExtension.class)
public class UseCasesTest {

    @Mock
    private ICriarPessoaGateway criarPessoaGateway;

    @InjectMocks
    private CriarPessoaUseCase criarPessoaUseCase;

    @Test
    void testCreatePerson_WhenUseCaseIsCalled_ReturnPerson() throws DomainInvalidException {
        var pessoa = new Pessoa("Gabriel", PessoaGenero.MASCULINO, "28999505410", "gabrielcardoso_stelo@hotmail.com");
        var createdPessoa = new Pessoa(1L, "Gabriel", PessoaGenero.MASCULINO, "28999505410", "gabrielcardoso_stelo@hotmail.com");

        when(criarPessoaGateway.existsEmail(pessoa.getEmail())).thenReturn(false);
        when(criarPessoaGateway.criar(pessoa)).thenReturn(createdPessoa);
                
        var result = criarPessoaUseCase.executar(pessoa);

        // Verificando se o resultado retornado pelo método executar() é igual ao objeto esperado
        assertEquals(createdPessoa, result, () -> "O objeto retornado deve ser igual ao objeto criado");
    }


    @Test
    void testCreatePerson_WhenUseCaseIsCalledAndExistsEmail_ThrowDomainInvalidException() throws DomainInvalidException {
        var pessoa = new Pessoa("Gabriel", PessoaGenero.MASCULINO, "28999505410", "gabrielcardoso_stelo@hotmail.com");

        when(criarPessoaGateway.existsEmail(pessoa.getEmail())).thenReturn(true);

        var exception = assertThrows(EmailInvalidException.class, () -> {
            criarPessoaUseCase.executar(pessoa);
        });

        assertEquals("O e-mail já está sendo usado", exception.getMessage());
    }



    
}
