SELECT
DISTINCT
	match_start_time,
	home_teams.team_name AS home_team_name,
	away_teams.team_name AS away_team_name
FROM matches
INNER JOIN teams AS home_teams ON home_team_id = home_teams.team_id
INNER JOIN teams AS away_teams ON away_team_id = away_teams.team_id
WHERE (home_team_id = :team_id OR away_team_id = :team_id)
AND home_team_score IS NULL AND away_team_score IS NULL