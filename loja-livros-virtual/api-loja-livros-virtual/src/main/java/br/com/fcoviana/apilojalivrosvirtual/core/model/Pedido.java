package br.com.fcoviana.apilojalivrosvirtual.core.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total")
    private double total;

    @OneToOne(mappedBy = "pedido")
    private Compra compra;

    // @OneToMany(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(double total, List<ItemPedido> itens) {
        this.total = total;
        this.itens = itens;
        itens.forEach(item -> item.setPedido(this));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
        itens.forEach(item -> item.setPedido(this));
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

//    public void addItem(ItemPedido item) {
//        this.itens.add(item);
//        item.setPedido(this);
//    }
}
