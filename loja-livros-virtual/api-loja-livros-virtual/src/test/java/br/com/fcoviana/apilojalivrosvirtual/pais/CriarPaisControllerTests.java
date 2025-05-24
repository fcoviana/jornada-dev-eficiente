package br.com.fcoviana.apilojalivrosvirtual.pais;

import br.com.fcoviana.apilojalivrosvirtual.core.repository.PaisRepository;
import br.com.fcoviana.apilojalivrosvirtual.pais.criar.CriarPaisRequest;
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
class CriarPaisControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PaisRepository paisRepository;

	@AfterEach
	void tearDown() {
		paisRepository.deleteAll();
	}

	private CriarPaisRequest mockPais() {
		var request = new CriarPaisRequest();
		request.setNome("Brasil");
		return request;
	}

	@Test
	void testSucesso() throws Exception {
		var input = mockPais();

		ResultActions response = mockMvc.perform(post("/api/v1/paises")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input)));

		response.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testPaisExistente() throws Exception {
		var input = mockPais();
		paisRepository.save(input.toModel());

		ResultActions response = mockMvc.perform(post("/api/v1/paises")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input)));

		response.andDo(print()).andExpect(status().isBadRequest());
	}
}
