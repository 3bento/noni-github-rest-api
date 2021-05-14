package com.noni.rest.service;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.noni.rest.dto.GitHubRepoDTO;
import com.noni.rest.dto.GitHubRepoRequestDTO;
import com.noni.rest.dto.enm.ESortDirection;
import com.noni.rest.dto.enm.ESortField;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GithbuService {

	@Autowired
	private WebClient webClient;

	public Flux<GitHubRepoDTO> retrieveRepositories(ESortField sortField, ESortDirection sortDirection) {
		return webClient.get() //
				.uri(uriBuilder -> uriBuilder.path("/user/repos")
						.queryParam("sort", sortField).queryParam("direction", sortDirection).build())
				.retrieve().bodyToFlux(GitHubRepoDTO.class);
	}

	/**
	 * Create new repository on github.
	 * <p>Alternatively, consider using the {@code retrieveRepositories(Object)} to get the value 
	 * {@link ocom.noni.rest.service.GithbuService GithbuService}. </p>
	 * @param body the value to write
	 * @return the repo after saved it.
	 * // TODO Improve the throws
	 * @throws IllegalArgumentException if {@code body} is a {@link Publisher} or an
	 * instance of a type supported by {@link ReactiveAdapterRegistry#getSharedInstance()},
	 * for which {@link #fromPublisher(Publisher, Class)} or
	 * {@link #fromProducer(Object, Class)} should be used.
	 * @see #retrieveRepositories(ESortField, ESortDirection)
	 */
	public Mono<GitHubRepoDTO> createRepo(GitHubRepoRequestDTO gitHubRepoRequestDTO) {
		return webClient.post().uri("/user/repos")
				//.body(Mono.just(gitHubRepoRequestDTO), GitHubRepoRequestDTO.class)
				.body(BodyInserters.fromValue(gitHubRepoRequestDTO))
				.retrieve()
				.bodyToMono(GitHubRepoDTO.class);
	}

}
