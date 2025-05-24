package br.com.fcoviana.apilojalivrosvirtual.estado.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.repository.EstadoRepository;
import br.com.fcoviana.apilojalivrosvirtual.core.repository.PaisRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Paises", description = "rotas de paises")
@RestController
@RequestMapping("/api/v1/paises")
public class CriarEstadoController {

    @Autowired
    private EstadoRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/estados")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> execute(@Valid @RequestBody CriarEstadoRequest request) {
        this.repository.save(request.toModel(entityManager));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
