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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ListarLivrosControllerTests {
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
        livro.setSumario("Capítulo 1 - Introdução, Capítulo 2 - Clean Code, Pai Rico, Pai Pobre é um livro de finanças pessoais que se tornou um best-seller mundial. O autor, Robert Kiyosaki, conta a história de dois pais");
        livro.setPreco(89.90);
        livro.setQuantidadePaginas(300);
        livro.setIsbn("978-0132350884");
        livro.setDataLancamento(LocalDate.of(ano, 1, 8));
        livro.setAutor(autor);
        livro.setCategoria(categoria);
        livroRepository.save(livro);
    }

    @Test
    void deveListarLivrosComSucesso() throws Exception {
        criarLivroValido();

        mockMvc.perform(get("/api/v1/livros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].titulo").value("Pai Rico, Pai Pobre"));
    }

    @Test
    void deveriaRetornarListaVaziaQuandoNaoExistemLivros() throws Exception {

        mockMvc.perform(get("/api/v1/livros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

}