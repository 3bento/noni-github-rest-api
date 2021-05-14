package com.noni.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.noni.rest.dto.GitHubRepoDTO;
import com.noni.rest.dto.GitHubRepoRequestDTO;
import com.noni.rest.dto.enm.ESortDirection;
import com.noni.rest.dto.enm.ESortField;
import com.noni.rest.service.GithbuService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("github")
public class GithubController {

	@Autowired
	private GithbuService githubService;
	
	@GetMapping("/u/repository")
	private Flux<GitHubRepoDTO> retrieveReposFromUsername(@RequestParam ESortField sortField, @RequestParam ESortDirection sortDirection) {
		return githubService.retrieveRepositories(sortField, sortDirection);
	}
	
	@PostMapping("/u/repository")
	private Mono<GitHubRepoDTO> createRepo(@Valid @RequestBody GitHubRepoRequestDTO gitHubRepoRequestDTO) {
		return githubService.createRepo(gitHubRepoRequestDTO);
	}

}
