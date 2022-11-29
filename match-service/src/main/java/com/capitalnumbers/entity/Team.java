package com.capitalnumbers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.capitalnumbers.Views;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {	
	@Schema(description = "Unique identifier of the Team.", 
            example = "1")
	private Long id;
	@Schema(description = "Name of the team.", 
            example = "FC Chelsea")
	private String name;
	
	public Team(String name) {
		this.name = name;
	}
}
