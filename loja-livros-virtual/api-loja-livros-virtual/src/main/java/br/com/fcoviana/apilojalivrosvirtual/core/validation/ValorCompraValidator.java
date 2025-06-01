package br.com.fcoviana.apilojalivrosvirtual.core.validation;

import br.com.fcoviana.apilojalivrosvirtual.compra.criar.CriarCompraRequest;
import br.com.fcoviana.apilojalivrosvirtual.core.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ValorCompraValidator implements Validator {
    @Autowired
    private LivroRepository livroRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CriarCompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var request = (CriarCompraRequest) target;

        // Agrupa os itens somando as quantidades por livroId
        Map<Long, Integer> livroIdParaQuantidade = request.getPedido().getItens().stream()
                .collect(Collectors.toMap(
                        item -> item.getLivroId(),
                        item -> item.getQuantidade(),
                        Integer::sum // soma as quantidades dos itens com mesmo livroId
                ));


        // Busca os livros no banco com base nos IDs únicos
        List<Long> livroIds = new ArrayList<>(livroIdParaQuantidade.keySet());
        var livros = livroRepository.findByIdIn(livroIds);

        // Valida se algum ID informado não existe no banco
        Set<Long> encontrados = livros.stream().map(l -> l.getId()).collect(Collectors.toSet());
        livroIds.forEach(id -> {
            if (!encontrados.contains(id)) {
                errors.rejectValue("pedido.itens", null, "Livro com id: " + id + " não existe.");
            }
        });

        if (errors.hasErrors()) return;

        // Soma o valor real da compra com base no preço * quantidade de cada livro
        double valorCalculado = livros.stream()
                .mapToDouble(livro -> {
                    int quantidade = livroIdParaQuantidade.getOrDefault(livro.getId(), 0);
                    return livro.getPreco() * quantidade;
                })
                .sum();

        // Verifica se o valor informado é o mesmo do calculado
        if (Double.compare(request.getPedido().getTotal(), valorCalculado) != 0) {
            errors.rejectValue("pedido.total", null,
                    "Valor total informado está incorreto. Valor correto seria: " + valorCalculado);
        }
    }
}