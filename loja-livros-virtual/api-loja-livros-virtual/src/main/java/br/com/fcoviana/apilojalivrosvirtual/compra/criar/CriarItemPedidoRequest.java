package br.com.fcoviana.apilojalivrosvirtual.compra.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Livro;
import br.com.fcoviana.apilojalivrosvirtual.core.validation.ExistsId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CriarItemPedidoRequest {
    @Schema(description = "Id do Livro", example = "1")
    @NotNull(message = "O Id do livro é obrigatório")
    @ExistsId(domainClass = Livro.class, fieldName = "id", message = "O livro informado nāo existe.")
    private Long livroId;

    @Schema(description = "Quantidade de livros", example = "1")
    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private Integer quantidade;

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
