package br.com.fcoviana.apilojalivrosvirtual.categoria;

import br.com.fcoviana.apilojalivrosvirtual.categoria.criar.CriarCategoriaRequest;
import br.com.fcoviana.apilojalivrosvirtual.core.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CriarCategoriaControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@AfterEach
	void tearDown() {
		categoriaRepository.deleteAll();
	}

	private CriarCategoriaRequest mockAutor() {
		var request = new CriarCategoriaRequest();
		request.setNome("Informática");
		return request;
	}

	@Test
	void testSucesso() throws Exception {
		var input = mockAutor();

		ResultActions response = mockMvc.perform(post("/api/v1/categorias")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input)));

		response.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testAutorCategoriaExistente() throws Exception {
		var input = mockAutor();
		input.setNome("Informatica");
		categoriaRepository.save(input.toModel());

		ResultActions response = mockMvc.perform(post("/api/v1/categorias")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input)));

		response.andDo(print()).andExpect(status().isBadRequest());
	}
}
