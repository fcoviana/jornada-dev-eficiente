package br.com.fcoviana.apilojalivrosvirtual.livro.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.repository.LivroRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Livro", description = "rotas de livros")
@RestController
@RequestMapping("/api/v1/livros")
public class CriarLivroController {

    @Autowired
    private LivroRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> execute(@Valid @RequestBody CriarLivroRequest request) {
        this.repository.save(request.toModel(entityManager));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
