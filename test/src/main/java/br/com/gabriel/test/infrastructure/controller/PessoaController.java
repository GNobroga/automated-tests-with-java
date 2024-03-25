package br.com.gabriel.test.infrastructure.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gabriel.test.application.mapper.PessoaMapper;
import br.com.gabriel.test.domain.entities.Pessoa;
import br.com.gabriel.test.domain.exception.DomainInvalidException;
import br.com.gabriel.test.infrastructure.dto.CriarPessoaRequestDTO;
import br.com.gabriel.test.usecases.IBuscarPessoaUseCase;
import br.com.gabriel.test.usecases.IBuscarPessoasUseCase;
import br.com.gabriel.test.usecases.ICriarPessoaUseCase;
import br.com.gabriel.test.usecases.IDeletePessoaByIdUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PessoaController {

    private final IBuscarPessoasUseCase buscarPessoasUseCase;

    private final ICriarPessoaUseCase criarPessoaUseCase;

    private final IBuscarPessoaUseCase buscarPessoaUseCase;

    private final IDeletePessoaByIdUseCase deletePessoaByIdUseCase;

    private final PessoaMapper pessoaMapper;
    

    @GetMapping
    public ResponseEntity<List<Pessoa>> get() throws DomainInvalidException {
        return ResponseEntity.ok(buscarPessoasUseCase.executar());
    }

    @PostMapping
    public ResponseEntity<Pessoa> post(@RequestBody CriarPessoaRequestDTO criarPessoaRequestDTO) throws DomainInvalidException {
        var result = criarPessoaUseCase.executar(pessoaMapper.toPessoa(criarPessoaRequestDTO));
        if (result == null) return null;
        var urlBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
        urlBuilder.pathSegment("{id}");
        var uriComponent = urlBuilder.buildAndExpand(new HashMap<String, Object>() {
            {
                put("id", result.getId());
            }
        });
        return ResponseEntity.created(uriComponent.toUri()).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> get(@PathVariable Long id) throws DomainInvalidException {
        return ResponseEntity.ok(buscarPessoaUseCase.executar(id));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        deletePessoaByIdUseCase.execute(id);
        return ResponseEntity.ok().build();
    }
}
