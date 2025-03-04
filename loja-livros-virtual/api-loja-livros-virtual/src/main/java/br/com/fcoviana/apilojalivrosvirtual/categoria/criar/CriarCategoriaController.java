package br.com.fcoviana.apilojalivrosvirtual.categoria.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.repository.CategoriaRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "Categoria", description = "rotas de categorias")
@RestController
@RequestMapping("/api/v1/categorias")
public class CriarCategoriaController {

    @Autowired
    private CategoriaRepository repository;
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> execute(@Valid @RequestBody CriarCategoriaRequest request) {
        var categoria = repository.findByNome(request.getNome());
        categoria.ifPresent(u -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria já cadastrada");
        });
        this.repository.save(request.toModel());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
