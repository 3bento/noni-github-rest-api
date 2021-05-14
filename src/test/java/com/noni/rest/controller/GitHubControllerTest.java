package com.noni.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noni.rest.dto.GitHubRepoRequestDTO;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GithubController.class)
public class GitHubControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void whenInputIsInvalid_thenReturnsStatus400() throws Exception {
	    GitHubRepoRequestDTO input = invalidInput();
	    String body = objectMapper.writeValueAsString(input);

	    mvc.perform(post("/github/users/repos")
	            .contentType("application/json")
	            .content(body))
	            .andExpect(status().isBadRequest());
	  }
	
	private GitHubRepoRequestDTO invalidInput() {
		return GitHubRepoRequestDTO.builder().build();
	}

}
