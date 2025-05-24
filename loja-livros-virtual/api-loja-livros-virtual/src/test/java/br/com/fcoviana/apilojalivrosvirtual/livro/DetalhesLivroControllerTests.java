package br.com.fcoviana.apilojalivrosvirtual.livro;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Autor;
import br.com.fcoviana.apilojalivrosvirtual.core.model.Categoria;
import br.com.fcoviana.apilojalivrosvirtual.core.model.Livro;
import br.com.fcoviana.apilojalivrosvirtual.core.repository.AutorRepository;
import br.com.fcoviana.apilojalivrosvirtual.core.repository.CategoriaRepository;
import br.com.fcoviana.apilojalivrosvirtual.core.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Calendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DetalhesLivroControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @AfterEach
    void tearDown() {
        livroRepository.deleteAll();
        autorRepository.deleteAll();
        categoriaRepository.deleteAll();
    }

    private void criarLivroValido() {
        var categoria = new Categoria("Informática");
        categoriaRepository.save(categoria);

        var autor = new Autor("Chico", "chico@mail.com", "Descricao");
        autorRepository.save(autor);

        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR) + 1;

        var livro = new Livro();
        livro.setTitulo("Pai Rico, Pai Pobre");
        livro.setResumo("Pai Rico, Pai Pobre é um livro de finanças pessoais que se tornou um best-seller mundial. O autor, Robert Kiyosaki, conta a história de dois pais");
        livro.setSumario("# 📘 Sumário — *Pai Rico, Pai Pobre*  \n" + //
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
                "");
        livro.setPreco(89.90);
        livro.setQuantidadePaginas(300);
        livro.setIsbn("978-0132350884");
        livro.setDataPublicacao(LocalDate.of(ano, 1, 8));
        livro.setAutor(autor);
        livro.setCategoria(categoria);
        livroRepository.save(livro);
    }

    @Test
    void deveDetalhesLivroComSucesso() throws Exception {
        criarLivroValido();

        mockMvc.perform(get("/api/v1/livros/detalhes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Pai Rico, Pai Pobre"))
                .andExpect(jsonPath("$.isbn").value("978-0132350884"));

    }

    @Test
    void deveRetornar404NaoExistemLivros() throws Exception {
        mockMvc.perform(get("/api/v1/livros/detalhes/1")).andExpect(status().isNotFound());
    }

}