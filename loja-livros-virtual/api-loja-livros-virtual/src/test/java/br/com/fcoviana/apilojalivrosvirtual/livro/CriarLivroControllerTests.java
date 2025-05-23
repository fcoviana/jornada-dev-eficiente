package br.com.fcoviana.apilojalivrosvirtual.livro;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Autor;
import br.com.fcoviana.apilojalivrosvirtual.core.model.Categoria;
import br.com.fcoviana.apilojalivrosvirtual.core.repository.AutorRepository;
import br.com.fcoviana.apilojalivrosvirtual.core.repository.CategoriaRepository;
import br.com.fcoviana.apilojalivrosvirtual.core.repository.LivroRepository;
import br.com.fcoviana.apilojalivrosvirtual.livro.criar.CriarLivroRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.DateFormat;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CriarLivroControllerTests {
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

    private CriarLivroRequest criarLivroRequestValido() {
        var categoria = new Categoria("Informática");
        categoriaRepository.save(categoria);

        var autor = new Autor("Chico", "chico@mail.com", "Descricao");
        autorRepository.save(autor);

        var request = new CriarLivroRequest();
        request.setTitulo("Pai Rico, Pai Pobre");
        request.setResumo("Pai Rico, Pai Pobre é um livro de finanças pessoais que se tornou um best-seller mundial. O autor, Robert Kiyosaki, conta a história de dois pais");
        request.setSumario("Capítulo 1 - Introdução, Capítulo 2 - Clean Code, Pai Rico, Pai Pobre é um livro de finanças pessoais que se tornou um best-seller mundial. O autor, Robert Kiyosaki, conta a história de dois pais");
        request.setPreco(89.90);
        request.setQuantidadePaginas(300);
        request.setIsbn("978-0132350884");
        request.setDataLancamento(LocalDate.of(2028, 1, 8));
        request.setCategoriaId(categoria.getId());
        request.setAutorId(autor.getId());
        
        return request;
    }

    @Test
    void deveCriarLivroComSucesso() throws Exception {
        var input = criarLivroRequestValido();

        ResultActions response = mockMvc.perform(post("/api/v1/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)));

        response.andDo(print()).andExpect(status().isOk());
    }

    @Test
    void naoDeveCriarLivroQuandoPayloadInvalido() throws Exception {
        var request = new CriarLivroRequest(); // Payload vazio

        ResultActions response = mockMvc.perform(post("/api/v1/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        response.andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void naoDeveCriarLivroQuandoCategoriaInexistente() throws Exception {
        var request = criarLivroRequestValido();
        request.setCategoriaId(999L); // ID inexistente

        ResultActions response = mockMvc.perform(post("/api/v1/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        response.andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void naoDeveCriarLivroComIsbnDuplicado() throws Exception {
        var request = criarLivroRequestValido();
        mockMvc.perform(post("/api/v1/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        ResultActions response = mockMvc.perform(post("/api/v1/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        response.andDo(print()).andExpect(status().isBadRequest());
    }
}