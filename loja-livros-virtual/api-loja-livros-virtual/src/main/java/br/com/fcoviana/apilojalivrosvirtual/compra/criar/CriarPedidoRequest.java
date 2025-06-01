package br.com.fcoviana.apilojalivrosvirtual.compra.criar;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CriarPedidoRequest {
    // TODO: add validacao do valor real
    @Schema(description = "Total da compra", example = "100")
    @NotNull(message = "O total é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O total deve ser maior que zero")
    private double total;

    @NotEmpty(message = "A lista de itens não pode estar vazia")
    @Valid
    private List<CriarItemPedidoRequest> itens;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<CriarItemPedidoRequest> getItens() {
        return itens;
    }

    public void setItens(List<CriarItemPedidoRequest> itens) {
        this.itens = itens;
    }
}
