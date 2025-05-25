package br.com.fcoviana.apilojalivrosvirtual.compra.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.validation.EstadoPertencePaisValidator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Compras", description = "rotas de compras")
@RestController
@RequestMapping("/api/v1/compras")
public class CriarCompraController {

    private final EstadoPertencePaisValidator estadoPertencePaisValidator;

    public CriarCompraController(EstadoPertencePaisValidator estadoPertencePaisValidator) {
        this.estadoPertencePaisValidator = estadoPertencePaisValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(estadoPertencePaisValidator);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> execute(@Valid @RequestBody CriarCompraRequest request) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
