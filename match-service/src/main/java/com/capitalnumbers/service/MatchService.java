package com.capitalnumbers.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capitalnumbers.SQLWrapper;
import com.capitalnumbers.CustomException;
import com.capitalnumbers.SQLScripts;
import com.capitalnumbers.entity.Match;
import com.capitalnumbers.entity.Team;

import models.AddMatchRequest;
import models.UpdateMatchRequest;


@Service
public class MatchService {

	@Autowired
	SQLWrapper sqlWrapper;
	
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<Team> getAllTeams() throws CustomException {
		String script = sqlWrapper.getScript(SQLScripts.selectAllTeams);		
		List<Team> teams = namedParameterJdbcTemplate.query(script, 
				(rs, rowNum) ->
			        new Team(
			                rs.getLong("team_id"),
			                rs.getString("team_name")
			        )
        );
		
		return teams;
	}
	
	public List<Match> getAllUpcomingMatchesByTeam(Integer team_id) throws CustomException {
		String script = sqlWrapper.getScript(SQLScripts.upcomingMatchesByTeam);
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("team_id", team_id);
		List<Match> upcomingMatches = namedParameterJdbcTemplate.query(script,
				paramSource,
				(rs, rowNum) ->
			        new Match(
			                rs.getString("match_start_time"),
			                rs.getString("home_team_name"),
			                rs.getString("away_team_name")
			        )
        );
		
		return upcomingMatches;
	}
	
	public List<Match> getAllMatchesByTeam(Integer team_id) throws CustomException {
		String script = sqlWrapper.getScript(SQLScripts.allMatchesByTeam);
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("team_id", team_id);
		List<Match> upcomingMatches = namedParameterJdbcTemplate.query(script,
				paramSource,
				(rs, rowNum) ->
			        new Match(
			                rs.getString("match_start_time"),
			                rs.getString("home_team_name"),
			                rs.getString("away_team_name"),
			                rs.getInt("home_team_score"),
			                rs.getInt("away_team_score")
			        )
        );
		
		return upcomingMatches;
	}
	
	public List<String> getAllWinners() throws CustomException {
		String script = sqlWrapper.getScript(SQLScripts.allWinners);
		List<String> winners = new ArrayList<String>();
		namedParameterJdbcTemplate.query(script, 
				(rs, rowNum) ->
		winners.add(rs.getString("team_name"))
        );
		
		return winners;
	}
	
	public int updateMatchTime(UpdateMatchRequest matchUpdateRequest) throws CustomException {
		String script = sqlWrapper.getScript(SQLScripts.updateMatchTime);
		Timestamp timestamp;		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("match_id", matchUpdateRequest.getId());
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		    Date parsedDate = dateFormat.parse(matchUpdateRequest.getStart_time());
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		}
		catch(ParseException e) {
			throw new CustomException("Invalid match start time!");
		}
		
		paramSource.addValue("match_time", timestamp);
		return namedParameterJdbcTemplate.update(script, paramSource);
	}
	
	public int updateTeamName(Team team) throws CustomException{
		String script = sqlWrapper.getScript(SQLScripts.updateTeamName);
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("team_id", team.getId());
		paramSource.addValue("team_name", team.getName());
		return namedParameterJdbcTemplate.update(script, paramSource);
	}
	
	public int deleteUpcomingMatch(int match_id) throws CustomException {
		String script = sqlWrapper.getScript(SQLScripts.deleteUpcomingMatch);
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("match_id", match_id);
		return namedParameterJdbcTemplate.update(script, paramSource);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public int createUpcomingMatch(AddMatchRequest addMatchRequest) throws CustomException {
		String script = sqlWrapper.getScript(SQLScripts.createUpcomingMatch);
		Timestamp timestamp;
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
	    try {
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		    Date parsedDate = dateFormat.parse(addMatchRequest.getStart_time());
		    timestamp = new java.sql.Timestamp(parsedDate.getTime());
		}
		catch(ParseException e) {
			throw new CustomException("Invalid match start time!");
		}
		
		if(addMatchRequest.getHome_team_name().equals(addMatchRequest.getAway_team_name())) {
			throw new CustomException("Both team cannot be the same!!");
		}
		
		if(timestamp.before(new Date())) {
			throw new CustomException("Upcoming match cannot be created in the past!");
		}
	    
	    List<Team> teams = this.getAllTeams();
	    Team homeTeam = teams.stream().filter(x -> x.getName().equals(addMatchRequest.getHome_team_name())).findFirst().orElse(null);
	    
	    // If home team is not already available in the list.. then first add this team
	    if(homeTeam == null) {
	    	this.addTeam(addMatchRequest.getHome_team_name());
	    	teams = this.getAllTeams();
	    	homeTeam = teams.stream().filter(x -> x.getName().equals(addMatchRequest.getHome_team_name())).findFirst().orElse(null);
	    }
	    
	    Team awayTeam = teams.stream().filter(x -> x.getName().equals(addMatchRequest.getAway_team_name())).findFirst().orElse(null);
	    
	    // If away team is not already available in the list.. then first add this team
	    if(awayTeam == null) {
	    	this.addTeam(addMatchRequest.getAway_team_name());
	    	teams = this.getAllTeams();
	    	awayTeam = teams.stream().filter(x -> x.getName().equals(addMatchRequest.getAway_team_name())).findFirst().orElse(null);
	    }
	    
		paramSource.addValue("match_start_time", timestamp);
		paramSource.addValue("home_team_id", homeTeam.getId());
		paramSource.addValue("away_team_id", awayTeam.getId());
		return namedParameterJdbcTemplate.update(script, paramSource);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void addTeam(String team_name)  throws CustomException {
		String script = sqlWrapper.getScript(SQLScripts.createTeam);
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("team_name", team_name);
		namedParameterJdbcTemplate.update(script, paramSource);
	}
}
