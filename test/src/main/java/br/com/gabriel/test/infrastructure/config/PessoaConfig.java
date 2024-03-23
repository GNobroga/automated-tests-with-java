package br.com.gabriel.test.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gabriel.test.application.usecaseImpl.BuscarPessoaUseCase;
import br.com.gabriel.test.application.usecaseImpl.BuscarPessoasUseCase;
import br.com.gabriel.test.application.usecaseImpl.CriarPessoaUseCase;
import br.com.gabriel.test.gateway.IBuscarPessoaGateway;
import br.com.gabriel.test.gateway.IBuscarPessoasGateway;
import br.com.gabriel.test.gateway.ICriarPessoaGateway;
import br.com.gabriel.test.usecases.IBuscarPessoaUseCase;
import br.com.gabriel.test.usecases.IBuscarPessoasUseCase;
import br.com.gabriel.test.usecases.ICriarPessoaUseCase;

@Configuration
public class PessoaConfig {
    

    @Bean
    IBuscarPessoasUseCase buscarPessoasUseCase(IBuscarPessoasGateway buscarPessoasGateway) {
        return new BuscarPessoasUseCase(buscarPessoasGateway);
    }

    @Bean
    ICriarPessoaUseCase criarPessoaUseCase(ICriarPessoaGateway criarPessoaGateway) {
        return new CriarPessoaUseCase(criarPessoaGateway);
    }

    @Bean
    IBuscarPessoaUseCase buscarPessoaUseCase(IBuscarPessoaGateway buscarPessoaGateway) {
        return new BuscarPessoaUseCase(buscarPessoaGateway);
    }
}
