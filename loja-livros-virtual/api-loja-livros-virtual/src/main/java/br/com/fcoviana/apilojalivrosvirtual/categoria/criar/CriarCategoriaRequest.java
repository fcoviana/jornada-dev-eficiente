package br.com.fcoviana.apilojalivrosvirtual.categoria.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CriarCategoriaRequest {
    @Schema(description = "Nome da categoria", example = "Informática")
    @NotEmpty(message = "O nome é obrigatório")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria toModel() {
        return new Categoria(nome);
    }
}
