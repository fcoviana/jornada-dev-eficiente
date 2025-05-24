package br.com.fcoviana.apilojalivrosvirtual.estado;

import br.com.fcoviana.apilojalivrosvirtual.core.model.Pais;
import br.com.fcoviana.apilojalivrosvirtual.core.repository.EstadoRepository;
import br.com.fcoviana.apilojalivrosvirtual.core.repository.PaisRepository;
import br.com.fcoviana.apilojalivrosvirtual.estado.criar.CriarEstadoRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
class CriarEstadoControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@PersistenceContext
  private EntityManager entityManager;

	@AfterEach
	void tearDown() {
		estadoRepository.deleteAll();
		paisRepository.deleteAll();
	}

	private CriarEstadoRequest mockEstado() {
		var pais = new Pais("Brasil");
		paisRepository.save(pais);

		var request = new CriarEstadoRequest();
		request.setNome("Ceará");
		request.setPaisId(pais.getId());
		return request;
	}

	@Test
	void testSucesso() throws Exception {
		var input = mockEstado();

		ResultActions response = mockMvc.perform(post("/api/v1/paises/estados")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input)));

		response.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testPaisExistente() throws Exception {
		var input = mockEstado();
		estadoRepository.save(input.toModel(entityManager));

		ResultActions response = mockMvc.perform(post("/api/v1/paises/estados")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(input)));

		response.andDo(print()).andExpect(status().isBadRequest());
	}
}
