package br.com.fcoviana.apilojalivrosvirtual.pais.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.repository.PaisRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Paises", description = "rotas de paises")
@RestController
@RequestMapping("/api/v1/paises")
public class CriarPaisController {

    @Autowired
    private PaisRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> execute(@Valid @RequestBody CriarPaisRequest request) {
        this.repository.save(request.toModel());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
