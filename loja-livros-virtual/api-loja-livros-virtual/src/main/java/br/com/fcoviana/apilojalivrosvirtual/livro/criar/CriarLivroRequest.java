package br.com.fcoviana.apilojalivrosvirtual.livro.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Autor;
import br.com.fcoviana.apilojalivrosvirtual.core.model.Categoria;
import br.com.fcoviana.apilojalivrosvirtual.core.model.Livro;
import br.com.fcoviana.apilojalivrosvirtual.core.validation.ExistsId;
import br.com.fcoviana.apilojalivrosvirtual.core.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class CriarLivroRequest {
    @Schema(description = "Titulo do livro", example = "Pai Rico, Pai Pobre")
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    @NotEmpty(message = "O titulo é obrigatório")
    private String titulo;

    @Schema(description = "Resumo do livro", example = "Pai Rico, Pai Pobre é um livro de finanças pessoais que se tornou um best-seller mundial. O autor, Robert Kiyosaki, conta a história de dois pais, o pai rico e o pai pobre, e as lições que aprendeu com eles.")
    @NotEmpty(message = "O resumo é obrigatório")
    @Size(min = 1, max = 500, message = "O resumo deve ter entre 1 e 500 caracteres")
    private String resumo;

    // TODO: aceitar markdown como sumario
    @Schema(description = "Sumario do livro", example = "Pai Rico, Pai Pobre é um livro de finanças pessoais que se tornou um best-seller mundial. O autor, Robert Kiyosaki, conta a história de dois pais, o pai rico e o pai pobre, e as lições que aprendeu com eles.")
    private String sumario;

    @Schema(description = "Preco do livro", example = "100.00")
    @NotNull(message = "O preco é obrigatório")
    @Min(value = 20, message = "O preco deve ser maior que ou igual 20.00")
    private double preco;

    @Schema(description = "Quantidade de paginas do livro", example = "100")
    @NotNull(message = "A quantidadePaginas é obrigatória")
    @Min(value = 100, message = "A quantidadePaginas deve ser maior ou igual que 100")
    private int quantidadePaginas;

    @Schema(description = "ISBN do livro", example = "978-85-286-1801-4")
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    @NotEmpty(message = "O isbn é obrigatório")
    private String isbn;

    // TODO: so deve aceitar datas futuras
    @Future
    @Schema(description = "Data de lançamento do livro", example = "10/10/2028")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    //@DateTimeFormat
    @NotNull(message = "A dataLancamento é obrigatória")
    private LocalDate dataLancamento;

    @Schema(description = "ID do Autor", example = "1")
    @ExistsId(domainClass = Autor.class, fieldName = "id")
    @NotNull(message = "A autorId é obrigatório")
    private Long autorId;

    @Schema(description = "ID da Categoria", example = "1")
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    @NotNull(message = "A categoriaId é obrigatória")
    private Long categoriaId;

    @Override
    public String toString() {
        return "CriarLivroRequest{" +
                "titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", quantidadePaginas=" + quantidadePaginas +
                ", isbn='" + isbn + '\'' +
                ", dataLancamento='" + dataLancamento + '\'' +
                ", autorId=" + autorId +
                ", categoriaId=" + categoriaId +
                '}';
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadePaginas() {
        return quantidadePaginas;
    }

    public void setQuantidadePaginas(int quantidadePaginas) {
        this.quantidadePaginas = quantidadePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Livro toModel(EntityManager entityManager) {
        var autor = entityManager.find(Autor.class, autorId);
        var categoria = entityManager.find(Categoria.class, categoriaId);
        return new Livro(titulo, resumo, sumario, preco, quantidadePaginas, isbn, dataLancamento, autor, categoria);
    }
}
