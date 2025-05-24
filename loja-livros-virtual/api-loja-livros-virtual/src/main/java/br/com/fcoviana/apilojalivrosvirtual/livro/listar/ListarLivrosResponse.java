package br.com.fcoviana.apilojalivrosvirtual.livro.listar;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Livro;

import java.util.List;

public record  ListarLivrosResponse(Long id, String titulo) {
    public static List<ListarLivrosResponse> toResponse(List<Livro> livros) {
        return livros.stream()
                .map(livro -> new ListarLivrosResponse(livro.getId(), livro.getTitulo()))
                .toList();
    }
}
