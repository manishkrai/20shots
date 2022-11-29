package com.capitalnumbers.entity;

import com.capitalnumbers.Views;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
	@Schema(description = "Unique identifier of the Match.", 
            example = "1")
	private Long id;	
	
	@Schema(description = "Match start time.", 
            example = "2022-11-22 11:00")
	@JsonView(Views.UpcomingMatchView.class)
	private String start_time;
	
	@Schema(description = "Name of the home team.", 
            example = "FC Chelsea")
	@JsonView(Views.UpcomingMatchView.class)
	private String home_team_name;
	
	@Schema(description = "Name of the away team.", 
            example = "Aston Villa FC")
	@JsonView(Views.UpcomingMatchView.class)
	private String away_team_name;
	
	@Schema(description = "Score of the home team.", 
            example = "2")
	private int home_score;
	
	@Schema(description = "Score of the away team.", 
            example = "0")
	private int away_score;
	
	public Match(String start_time, String home_team_name, String away_team_name){
		this.start_time = start_time;
		this.home_team_name = home_team_name;
		this.away_team_name = away_team_name;
	}
	
	public Match(String start_time, String home_team_name, String away_team_name, int home_score, int away_score){
		this.start_time = start_time;
		this.home_team_name = home_team_name;
		this.away_team_name = away_team_name;
		this.home_score = home_score;
		this.away_score = away_score;
	}
}
