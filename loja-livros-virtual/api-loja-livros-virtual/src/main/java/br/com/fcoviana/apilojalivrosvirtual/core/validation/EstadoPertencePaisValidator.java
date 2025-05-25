package br.com.fcoviana.apilojalivrosvirtual.core.validation;

import br.com.fcoviana.apilojalivrosvirtual.compra.criar.CriarCompraRequest;
import br.com.fcoviana.apilojalivrosvirtual.core.model.Estado;
import br.com.fcoviana.apilojalivrosvirtual.core.model.Pais;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EstadoPertencePaisValidator implements Validator {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> clazz) {
        return CriarCompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var request = (CriarCompraRequest) target;
        var pais = entityManager.find(Pais.class, request.getPaisId());

        if (pais == null) {
            errors.rejectValue("paisId", null, "País não encontrado");
            return;
        }

        boolean paisTemEstados = !pais.getEstados().isEmpty();

        if (paisTemEstados && request.getEstadoId() == null) {
            errors.rejectValue("estadoId", null, "Estado é obrigatório pois o país possui estados");
            return;
        }

        if (!paisTemEstados) return;

        var estado = entityManager.find(Estado.class, request.getEstadoId());

        if (estado == null) {
            errors.rejectValue("estadoId", null, "Estado não encontrado");
            return;
        }

        if (!estado.estadoPertencePais(pais)) {
            errors.rejectValue("estadoId", null, "O estado não pertence ao país selecionado");
        }
    }
}