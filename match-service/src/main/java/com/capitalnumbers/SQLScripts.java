package com.capitalnumbers;

import org.springframework.stereotype.Component;

@Component
public class SQLScripts{ 
    
	public static final String selectAllTeams = "selectAllTeams";
	
	public static final String upcomingMatchesByTeam = "upcomingMatchesByTeam";
	
	public static final String allMatchesByTeam = "allMatchesByTeam";
	
	public static final String allWinners = "allWinners";
	
	public static final String updateTeamName = "updateTeamName";
	
	public static final String updateMatchTime = "updateMatchTime";
	
	public static final String deleteUpcomingMatch = "deleteUpcomingMatch";
	
	public static final String createUpcomingMatch = "createUpcomingMatch";
	
	public static final String createTeam = "createTeam";
}