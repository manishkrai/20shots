DROP TABLE IF EXISTS matches;
DROP TABLE IF EXISTS teams;

CREATE TABLE teams(
   team_id INT PRIMARY KEY  NOT NULL generated always as identity,
   team_name text NOT NULL UNIQUE
);

CREATE TABLE matches(
   match_id INT PRIMARY KEY  NOT NULL generated always as identity,
   match_start_time timestamp NOT NULL,
   home_team_id INT NOT NULL,
   away_team_id INT NOT NULL,
   home_team_score INT NULL,
   away_team_score INT NULL,
   CONSTRAINT fk_home_team
      FOREIGN KEY(home_team_id) 
      REFERENCES teams(team_id),
   CONSTRAINT fk_away_team
      FOREIGN KEY(away_team_id) 
      REFERENCES teams(team_id)   	  
);