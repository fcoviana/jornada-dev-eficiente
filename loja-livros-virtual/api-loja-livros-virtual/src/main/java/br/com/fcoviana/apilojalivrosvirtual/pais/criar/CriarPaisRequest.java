package br.com.fcoviana.apilojalivrosvirtual.pais.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Pais;
import br.com.fcoviana.apilojalivrosvirtual.core.validation.UniqueValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public class CriarPaisRequest {
    @Schema(description = "Nome do pais", example = "Brasil")
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    @NotEmpty(message = "O nome é obrigatório")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pais toModel() {
        return new Pais(nome);
    }
}
