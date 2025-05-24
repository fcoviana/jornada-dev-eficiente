package br.com.fcoviana.apilojalivrosvirtual.livro.criar;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Autor;
import br.com.fcoviana.apilojalivrosvirtual.core.model.Categoria;
import br.com.fcoviana.apilojalivrosvirtual.core.model.Livro;
import br.com.fcoviana.apilojalivrosvirtual.core.validation.ExistsId;
import br.com.fcoviana.apilojalivrosvirtual.core.validation.UniqueValue;
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
    @Schema(description = "Sumario do livro", example = "# 📘 Sumário — *Pai Rico, Pai Pobre*  \n" + //
                "*Robert T. Kiyosaki*\n" + //
                "\n" + //
                "---\n" + //
                "\n" + //
                "## 1. Introdução  \n" + //
                "**O que os ricos ensinam aos filhos que os pobres e a classe média não ensinam**  \n" + //
                "- O autor apresenta seus dois “pais”: o Pai Rico (mentor) e o Pai Pobre (seu pai biológico).  \n" + //
                "- Contraste de mentalidades sobre dinheiro, trabalho e educação.\n" + //
                "\n" + //
                "---\n" + //
                "\n" + //
                "## 2. Os ricos não trabalham por dinheiro  \n" + //
                "- A importância de aprender ao invés de apenas trabalhar por salário.  \n" + //
                "- Desenvolver inteligência financeira desde cedo.\n" + //
                "\n" + //
                "---\n" + //
                "\n" + //
                "## 3. Por que ensinar educação financeira?  \n" + //
                "- Crítica ao sistema tradicional de ensino.  \n" + //
                "- Diferença entre ativos e passivos.  \n" + //
                "- Enriquecimento exige conhecimento financeiro.\n" + //
                "\n" + //
                "---\n" + //
                "\n" + //
                "## 4. Cuide do seu próprio negócio  \n" + //
                "- Invista em ativos: imóveis, ações, empresas.  \n" + //
                "- Não dependa apenas do emprego.  \n" + //
                "- Mentalidade empreendedora.\n" + //
                "\n" + //
                "---\n" + //
                "\n" + //
                "## 5. A história dos impostos e o poder das corporações  \n" + //
                "- Como os ricos usam empresas para pagar menos impostos.  \n" + //
                "- Diferença entre pessoa física e jurídica no sistema tributário.  \n" + //
                "- Conhecimento fiscal como vantagem estratégica.\n" + //
                "\n" + //
                "---\n" + //
                "\n" + //
                "## 6. Os ricos inventam dinheiro  \n" + //
                "- Criatividade e ousadia nos investimentos.  \n" + //
                "- Identificar oportunidades que os outros não veem.  \n" + //
                "- Assumir riscos calculados.\n" + //
                "\n" + //
                "---\n" + //
                "\n" + //
                "## 7. Trabalhe para aprender — não para ganhar dinheiro  \n" + //
                "- Aprender habilidades múltiplas (vendas, marketing, gestão).  \n" + //
                "- Evitar especialização excessiva.  \n" + //
                "- O conhecimento é o verdadeiro ativo.\n" + //
                "\n" + //
                "---\n" + //
                "\n" + //
                "## 8. Superando obstáculos  \n" + //
                "- Medo, cinismo, preguiça, maus hábitos e arrogância são os principais bloqueios mentais.  \n" + //
                "- Controlar emoções para tomar decisões racionais.\n" + //
                "\n" + //
                "---\n" + //
                "\n" + //
                "## 9. Comece  \n" + //
                "- Dicas práticas para começar a investir e mudar sua mentalidade.  \n" + //
                "- A importância da ação.\n" + //
                "\n" + //
                "---\n" + //
                "\n" + //
                "## 10. Ainda quer mais? Aqui está o que você precisa fazer  \n" + //
                "- Recursos e estratégias para continuar o aprendizado financeiro.  \n" + //
                "- Como manter o progresso rumo à independência financeira.\n" + //
                "\n" + //
                "---\n" + //
                "\n" + //
                "## Epílogo  \n" + //
                "- O legado das lições do Pai Rico.  \n" + //
                "- Inspiração para ensinar educação financeira às futuras gerações.\n" + //
                "")
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
    @Schema(description = "Data de lançamento do livro", example = "2028-10-02")
    //@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    //@DateTimeFormat
    @NotNull(message = "A dataPublicacao é obrigatória")
    private LocalDate dataPublicacao;

    @Schema(description = "Id do Autor", example = "1")
    @ExistsId(domainClass = Autor.class, fieldName = "id")
    @NotNull(message = "A autorId é obrigatório")
    private Long autorId;

    @Schema(description = "Id da Categoria", example = "1")
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
                ", dataPublicacao='" + dataPublicacao + '\'' +
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

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
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
        return new Livro(titulo, resumo, sumario, preco, quantidadePaginas, isbn, dataPublicacao, autor, categoria);
    }
}
