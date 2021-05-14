package com.noni.rest.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GitHubRepoRequestDTO {

    @NotBlank
    private String name;

    private String description;

    @JsonProperty("private")
    private Boolean isPrivate;
	
}
