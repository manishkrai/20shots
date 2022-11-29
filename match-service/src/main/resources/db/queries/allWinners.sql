SELECT
DISTINCT
CASE WHEN home_team_score > away_team_score THEN home_teams.team_name
ELSE away_teams.team_name END AS team_name
FROM matches
INNER JOIN teams AS home_teams ON home_team_id = home_teams.team_id
INNER JOIN teams AS away_teams ON away_team_id = away_teams.team_id
WHERE home_team_score IS NOT NULL AND away_team_score IS NOT NULL
AND home_team_score - away_team_score <> 0