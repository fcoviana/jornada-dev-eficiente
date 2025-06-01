package br.com.fcoviana.apilojalivrosvirtual.compra.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.repository.CompraRepository;
import br.com.fcoviana.apilojalivrosvirtual.core.validation.EstadoPertencePaisValidator;
import br.com.fcoviana.apilojalivrosvirtual.core.validation.ValorCompraValidator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Compras", description = "rotas de compras")
@RestController
@RequestMapping("/api/v1/compras")
public class CriarCompraController {

    private final EstadoPertencePaisValidator estadoPertencePaisValidator;
    private final ValorCompraValidator valorCompraValidator;


    public CriarCompraController(EstadoPertencePaisValidator estadoPertencePaisValidator, ValorCompraValidator valorCompraValidator) {
        this.estadoPertencePaisValidator = estadoPertencePaisValidator;
        this.valorCompraValidator = valorCompraValidator;
    }

    @Autowired
    CompraRepository compraRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(estadoPertencePaisValidator, valorCompraValidator);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> execute(@Valid @RequestBody CriarCompraRequest request) {
        try {
            var compra = request.toModel(entityManager);

            // Persiste os itens do pedido
            compra.getPedido().getItens().forEach(entityManager::persist);

            // Persiste o pedido
            entityManager.persist(compra.getPedido());

            // Persiste a compra
            entityManager.persist(compra);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
