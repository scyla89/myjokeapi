package com.example.myjokeapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.myjokeapi.config.IntegrationTestConfiguration;
import com.example.myjokeapi.controller.model.JokeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class MyJokeApiApplicationTest {
	private static final Logger LOG = LoggerFactory.getLogger(MyJokeApiApplicationTest.class);

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void healthTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/jokes/")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGetRandom() throws Exception {
		String uri = "/jokes/random";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		JokeDto jokeDto = mapFromJson(content, JokeDto.class);
		LOG.debug("Random Joke: " + jokeDto);
		assertNotNull(jokeDto);
	}

	@Test
	public void testFindById() throws Exception {
		String uri = "/jokes/R7UfaahVfFd";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		JokeDto jokeDto = mapFromJson(content, JokeDto.class);
		LOG.debug("Joke By Id: " + jokeDto);
		assertNotNull(jokeDto);
	}

	@Test
	public void testFindByTerm() throws Exception {
		String uri = "/jokes/?term=elephant";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		JokeDto[] jokeDtoList = mapFromJson(content, JokeDto[].class);
		LOG.debug("Joke By Term: " + jokeDtoList[0]);
		assertTrue(jokeDtoList.length > 0);
	}

	@Test
	public void testAddJoke() throws Exception {
		JokeDto jokeDto = new JokeDto("A conference call is the best way for a dozen people to say “bye” 300 times.", "officejoke");
		mockMvc.perform(post("/jokes/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(String.valueOf(mapToJson(jokeDto))))
				.andExpect(status().isCreated());
	}

	/**
	 * Mappers
	 */
	public <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	public String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
}
