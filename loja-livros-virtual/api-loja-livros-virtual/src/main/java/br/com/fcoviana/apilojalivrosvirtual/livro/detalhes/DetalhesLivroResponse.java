package br.com.fcoviana.apilojalivrosvirtual.livro.detalhes;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Autor;
import br.com.fcoviana.apilojalivrosvirtual.core.model.Categoria;
import br.com.fcoviana.apilojalivrosvirtual.core.model.Livro;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record DetalhesLivroResponse(
        @Schema(description = "Id do livro", example = "1")
        Long id,

        @Schema(description = "Titulo do livro", example = "Pai Rico, Pai Pobre")
        String titulo,

        @Schema(description = "Resumo do livro", example = "Pai Rico, Pai Pobre é um livro de finanças pessoais que se tornou um best-seller mundial. O autor, Robert Kiyosaki, conta a história de dois pais, o pai rico e o pai pobre, e as lições que aprendeu com eles.")
        String resumo,

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
        String sumario,

        @Schema(description = "Preco do livro", example = "100.00")
        double preco,

        @Schema(description = "Quantidade de paginas do livro", example = "100")
        int quantidadePaginas,

        @Schema(description = "ISBN do livro", example = "978-85-286-1801-4")
        String isbn,

        @Schema(description = "Data de lançamento do livro", example = "2028-10-02")
        LocalDate dataPublicacao,

        @Schema(description = "Autor do livro", example = "{\n" +
                "  \"id\": 1,\n" +
                "  \"nome\": \"Paulo Ricardo\",\n" +
                "  \"email\": \"paulo.ricardo@mail.com\",\n" +
                "  \"descricao\": \"Foi escritor; poeta, dramaturgo, linguista, tradutor e crítico. Formou-se em literatura inglesa pela Universidade de Manchester e morreu em 1993, aos 76 anos, deixando em seu legado uma grande e reconhecida obra, entre romances, biografias, peças de teatro, estudos literários, roteiros de cinema e TV.\"\n" +
                "}")
        Autor autor,

        @Schema(description = "Data de lançamento do livro", example = "{\n" +
                "  \"id\": 1,\n" +
                "  \"nome\": \"Informática\"\n" +
                "}")
        Categoria categoria
) {
    public static DetalhesLivroResponse toResponse(Livro livro) {
        return new DetalhesLivroResponse(livro.getId(), livro.getTitulo(), livro.getResumo(), livro.getSumario(), livro.getPreco(), livro.getQuantidadePaginas(), livro.getIsbn(), livro.getDataPublicacao(), livro.getAutor(), livro.getCategoria());
    }
}
