package br.com.gabriel.test.application.gatewayImpl;

import org.springframework.stereotype.Service;

import br.com.gabriel.test.gateway.IDeletarPessoaByIdGateway;
import br.com.gabriel.test.infrastructure.entity.PessoaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletarPessoaByIdGateway implements IDeletarPessoaByIdGateway {
    
    private final EntityManager entityManager;

    @Override
    public void deletaById(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        var cd = cb.createCriteriaDelete(PessoaEntity.class);
        var root = cd.getRoot();
        cd.where(cb.equal(root.get("id"), id));
        entityManager.createQuery(cd).executeUpdate();
    }
    


}
