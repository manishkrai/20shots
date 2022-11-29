package com.capitalnumbers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capitalnumbers.CustomException;
import com.capitalnumbers.MatchControllerConfig;
import com.capitalnumbers.entity.Match;
import com.capitalnumbers.entity.Team;
import com.capitalnumbers.service.MatchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import models.AddMatchRequest;
import models.UpdateMatchRequest;

@RestController
@RequestMapping(MatchControllerConfig.mainRoute)
@Tag(name = "match", description = "the match API")
public class MatchController {

	@Autowired
	private MatchService matchService;
	
	@Operation(summary = "Get all playing teams", description = "This method returns all teams", tags = { "match" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))) })	
	@GetMapping(value = MatchControllerConfig.getAllTeams, produces = { "application/json"})
	public ResponseEntity<List<Team>> getAllTeams() {
		HttpStatus status = HttpStatus.OK;
		List<Team> teams = null;
		try {
			teams = matchService.getAllTeams();
		} catch (CustomException e) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<>(teams, status);
	}
	
	@Operation(summary = "Get all upcoming matches", description = "This method returns all upcoming matches for a team", tags = { "match" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Match.class)))) })	
	@GetMapping(value = "/upcomingMatchesByTeam/{team_id}", produces = { "application/json"})
	public ResponseEntity<List<Match>> getUpcomingMatches(
			@Parameter(description="Team id for which upcoming matches to return") 
			@PathVariable("team_id") Integer team_id) {
		HttpStatus status = HttpStatus.OK;
		List<Match> upcomingMatches = null;
		try {
			upcomingMatches = matchService.getAllUpcomingMatchesByTeam(team_id);
		} catch (CustomException e) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<>(upcomingMatches, status);
	}
	
	@Operation(summary = "Get all matches", description = "This method returns all matches for a team", tags = { "match" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Match.class)))) })	
	@GetMapping(value = "/allMatchesByTeam/{team_id}", produces = { "application/json"})
	public ResponseEntity<List<Match>> getAllMatchesByTeam(
			@Parameter(description="Team id for which matches to return")
			@PathVariable("team_id") Integer team_id) {
		HttpStatus status = HttpStatus.OK;
		List<Match> allMatches = null;
		try {
			allMatches = matchService.getAllMatchesByTeam(team_id);
		} catch (CustomException e) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<>(allMatches, status);
	}
	
	@Operation(summary = "Get winners", description = "This method returns winner list", tags = { "match" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class)))) })	
	@GetMapping(value = "/winners", produces = { "application/json"})
	public ResponseEntity<List<String>> getAllWinners() {
		HttpStatus status = HttpStatus.OK;
		List<String> allWinners = null;
		
		try {
			allWinners = matchService.getAllWinners();
		} catch (CustomException e) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<>(allWinners, status);
	}
	
	@Operation(summary = "Update match time", description = "This method update a match time", tags = { "match" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Integer.class)))) })	
	@PostMapping(value = "/updateMatchTime", produces = { "application/json"})
	public ResponseEntity<Integer> updateMatch(
			@Parameter(description="Match details. Cannot null or empty.", 
            required=true, schema=@Schema(implementation = UpdateMatchRequest.class))
			@RequestBody UpdateMatchRequest matchUpdateRequest) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		int updated = 0;
		try
		{
			updated = matchService.updateMatchTime(matchUpdateRequest);
			if(updated > 0) {
				status = HttpStatus.OK;
			}
		}
		catch(Exception e) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<>(updated, status);
	}
	
	@Operation(summary = "Update team name", description = "This method update a team name", tags = { "match" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Integer.class)))) })	
	@PostMapping(value = "/updateTeamName", produces = { "application/json"})
	public ResponseEntity<Integer> updateTeamName(
			@Parameter(description="Team details. Cannot null or empty.", 
            required=true, schema=@Schema(implementation = Team.class))
			@RequestBody Team team) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		int updated = 0;
		try
		{
			updated = matchService.updateTeamName(team);
			if(updated > 0) {
				status = HttpStatus.OK;
			}
		}
		catch(Exception e) {
			status = HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<>(updated, status);
	}
	
	@Operation(summary = "Delete upcoming match", description = "This method delete a upcoming match", tags = { "match" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Integer.class)))) })	
	@DeleteMapping(value = "/deleteUpcomingMatch/{match_id}", produces = { "application/json"})
	public ResponseEntity<Integer> deleteUpcomingMatch(
			@Parameter(description="Match id that need to be deleted") 
			@PathVariable("match_id") Integer match_id) {
		HttpStatus status = HttpStatus.NO_CONTENT;
		int updated = 0;
		try
		{
			updated = matchService.deleteUpcomingMatch(match_id);
			if(updated > 0) {
				status = HttpStatus.OK;
			}
		}
		catch(Exception e) {
			status = HttpStatus.NO_CONTENT;
		}
		
		return new ResponseEntity<>(updated, status);
	}
	
	@Operation(summary = "Add upcoming match", description = "This method add a upcoming match", tags = { "match" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Integer.class)))) })	
	@PostMapping(value = "/addUpcomingMatch", produces = { "application/json"})
	public ResponseEntity<Integer> createUpcomingMatch(
			@Parameter(description="Match details. Cannot null or empty.", 
            required=true, schema=@Schema(implementation = AddMatchRequest.class))
			@RequestBody AddMatchRequest addMatchRequest) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		int added = 0;
		try {
			added = matchService.createUpcomingMatch(addMatchRequest);
			if(added > 0) {
				status = HttpStatus.CREATED;
			}
		} catch (CustomException e) {
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<>(added, status);
	}
}
