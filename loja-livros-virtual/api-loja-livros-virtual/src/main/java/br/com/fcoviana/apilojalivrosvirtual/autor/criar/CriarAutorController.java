package br.com.fcoviana.apilojalivrosvirtual.autor.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Autor;
import br.com.fcoviana.apilojalivrosvirtual.core.repository.AutorRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Tag(name = "Autor", description = "rotas de autores")
@RestController
@RequestMapping("/api/v1/autores")
public class CriarAutorController {

    @Autowired
    private AutorRepository repository;
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> execute(@Valid @RequestBody CriarAutorRequest request) {
        Optional<Autor> autor = repository.findByEmail(request.getEmail());
        autor.ifPresent(u -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado");
        });
        this.repository.save(request.toModel());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
