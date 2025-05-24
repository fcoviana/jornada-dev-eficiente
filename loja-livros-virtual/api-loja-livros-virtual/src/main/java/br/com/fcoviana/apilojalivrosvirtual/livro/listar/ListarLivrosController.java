package br.com.fcoviana.apilojalivrosvirtual.livro.listar;

import br.com.fcoviana.apilojalivrosvirtual.core.repository.LivroRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Livro", description = "rotas de livros")
@RestController
@RequestMapping("/api/v1/livros")
public class ListarLivrosController {

    @Autowired
    private LivroRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ListarLivrosResponse>> execute() {
        var livros = this.repository.findAll();
        return ResponseEntity.ok(ListarLivrosResponse.toResponse(livros));
    }
}
