package br.com.fcoviana.apilojalivrosvirtual.autor;

import br.com.fcoviana.apilojalivrosvirtual.autor.criar.CriarAutorRequest;
import br.com.fcoviana.apilojalivrosvirtual.core.repository.AutorRepository;
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
class CriarAutorControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private AutorRepository autorRepository;

	@AfterEach
	void tearDown() {
		autorRepository.deleteAll();
	}

	private CriarAutorRequest mockAutor() {
		var request = new CriarAutorRequest();
		request.setNome("Pedro Paulo");
		request.setEmail("pedor.paulo@mail.com");
		request.setDescricao("Foi escritor; poeta, dramaturgo, linguista, tradutor e crítico. Formou-se em literatura inglesa pela Universidade de Manchester e morreu em 1993, aos 76 anos, deixando em seu legado uma grande e reconhecida obra, entre romances, biografias, peças de teatro, estudos literários, roteiros de cinema e TV");
		return request;
	}

	@Test
	void testSucesso() throws Exception {
		var input = mockAutor();

		ResultActions response = mockMvc.perform(post("/api/v1/autores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input)));

		response.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testAutorEmailExistente() throws Exception {
		var input = mockAutor();
		input.setEmail("email-duplicado@mail.com");
		autorRepository.save(input.toModel());

		ResultActions response = mockMvc.perform(post("/api/v1/autores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input)));

		response.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void testDescricaoMaxima400() throws Exception {
		var input = mockAutor();
		input.setDescricao("Foi escritor; poeta, dramaturgo, linguista, tradutor e crítico. Formou-se em literatura inglesa pela Universidade de Manchester e morreu em 1993, aos 76 anos, deixando em seu legado uma grande e reconhecida obra, entre romances, biografias, peças de teatro, estudos literários, roteiros de cinema e TV ...entre romances, biografias, peças de teatro, estudos literários, roteiros de cinema e TV .......");

		ResultActions response = mockMvc.perform(post("/api/v1/autores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input)));

		response.andDo(print()).andExpect(status().isBadRequest());
	}
}
