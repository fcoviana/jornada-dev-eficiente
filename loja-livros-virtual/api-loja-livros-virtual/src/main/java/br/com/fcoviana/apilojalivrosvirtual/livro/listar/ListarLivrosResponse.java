package br.com.fcoviana.apilojalivrosvirtual.livro.listar;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Livro;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record  ListarLivrosResponse(
        @Schema(description = "Id do livro", example = "1") Long id,
        @Schema(description = "Titulo do livro", example = "Pai Rico, Pai Pobre") String titulo
) {
    public static List<ListarLivrosResponse> toResponse(List<Livro> livros) {
        return livros.stream()
                .map(livro -> new ListarLivrosResponse(livro.getId(), livro.getTitulo()))
                .toList();
    }
}
