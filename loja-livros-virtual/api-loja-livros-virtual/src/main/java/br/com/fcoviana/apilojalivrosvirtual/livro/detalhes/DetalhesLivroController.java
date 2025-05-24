package br.com.fcoviana.apilojalivrosvirtual.livro.detalhes;

import br.com.fcoviana.apilojalivrosvirtual.core.repository.LivroRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Livro", description = "rotas de livros")
@RestController
@RequestMapping("/api/v1/livros")
public class DetalhesLivroController {

    @Autowired
    private LivroRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("detalhes/{id}")
    @ResponseBody
    public ResponseEntity<DetalhesLivroResponse> execute(@PathVariable @Schema(description = "Id do livro", example = "1") Long id) {
        var livro = this.repository.findById(id);
        if (livro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(DetalhesLivroResponse.toResponse(livro.get()));
    }
}
