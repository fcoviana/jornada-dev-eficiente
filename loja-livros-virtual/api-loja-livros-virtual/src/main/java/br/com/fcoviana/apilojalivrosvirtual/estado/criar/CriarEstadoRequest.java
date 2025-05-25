package br.com.fcoviana.apilojalivrosvirtual.estado.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Estado;
import br.com.fcoviana.apilojalivrosvirtual.core.model.Pais;
import br.com.fcoviana.apilojalivrosvirtual.core.validation.ExistsId;
import br.com.fcoviana.apilojalivrosvirtual.core.validation.UniqueValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CriarEstadoRequest {
    @Schema(description = "Nome do pais", example = "São Paulo")
    @UniqueValue(domainClass = Estado.class, fieldName = "nome")
    @NotEmpty(message = "O nome é obrigatório")
    private String nome;

    @Schema(description = "Id do Pais", example = "1")
    @ExistsId(domainClass = Pais.class, fieldName = "id", message = "O pais não existe")
    @NotNull(message = "A paisId é obrigatório")
    private Long paisId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPaisId() {
        return paisId;
    }

    public void setPaisId(Long paisId) {
        this.paisId = paisId;
    }

    public Estado toModel(EntityManager entityManager) {
        var pais = entityManager.find(Pais.class, paisId);
        return new Estado(nome, pais);
    }
}
