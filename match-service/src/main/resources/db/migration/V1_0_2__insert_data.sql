DELETE FROM matches;
DELETE FROM teams;

INSERT INTO teams(team_name) VALUES('Chelsea FC');
INSERT INTO teams(team_name) VALUES('Brighton & Hove Albion FC');
INSERT INTO teams(team_name) VALUES('Aston Villa FC');
INSERT INTO teams(team_name) VALUES('Leeds United');
INSERT INTO teams(team_name) VALUES('Southampton FC');
INSERT INTO teams(team_name) VALUES('Manchester United FC');
INSERT INTO teams(team_name) VALUES('West Ham United FC');

-- First Match
INSERT INTO matches(match_start_time,home_team_id,away_team_id,home_team_score,away_team_score)
VALUES(
'2023-03-22 22:00:00',
(SELECT team_id FROM teams WHERE team_name = 'Chelsea FC' LIMIT 1),
(SELECT team_id FROM teams WHERE team_name = 'Brighton & Hove Albion FC' LIMIT 1),
NULL,
NULL
);

-- Second Match
INSERT INTO matches(match_start_time,home_team_id,away_team_id,home_team_score,away_team_score)
VALUES(
'2023-03-23 22:00:00',
(SELECT team_id FROM teams WHERE team_name = 'Aston Villa FC' LIMIT 1),
(SELECT team_id FROM teams WHERE team_name = 'Leeds United' LIMIT 1),
NULL,
NULL
);

-- Third Match
INSERT INTO matches(match_start_time,home_team_id,away_team_id,home_team_score,away_team_score)
VALUES(
'2023-03-24 17:00:00',
(SELECT team_id FROM teams WHERE team_name = 'Southampton FC' LIMIT 1),
(SELECT team_id FROM teams WHERE team_name = 'Manchester United FC' LIMIT 1),
NULL,
NULL
);


-- Fourth Match
INSERT INTO matches(match_start_time,home_team_id,away_team_id,home_team_score,away_team_score)
VALUES(
'2022-03-25 23:00:00',
(SELECT team_id FROM teams WHERE team_name = 'Chelsea FC' LIMIT 1),
(SELECT team_id FROM teams WHERE team_name = 'Aston Villa FC' LIMIT 1),
0,
3
);


-- Fifth Match
INSERT INTO matches(match_start_time,home_team_id,away_team_id,home_team_score,away_team_score)
VALUES(
'2023-03-25 23:00:00',
(SELECT team_id FROM teams WHERE team_name = 'Southampton FC' LIMIT 1),
(SELECT team_id FROM teams WHERE team_name = 'Aston Villa FC' LIMIT 1),
NULL,
NULL
);

-- Sixth Match
INSERT INTO matches(match_start_time,home_team_id,away_team_id,home_team_score,away_team_score)
VALUES(
'2022-07-29 23:00:00',
(SELECT team_id FROM teams WHERE team_name = 'Southampton FC' LIMIT 1),
(SELECT team_id FROM teams WHERE team_name = 'Aston Villa FC' LIMIT 1),
0,
0
);

-- Seventh Match
INSERT INTO matches(match_start_time,home_team_id,away_team_id,home_team_score,away_team_score)
VALUES(
'2022-03-25 23:00:00',
(SELECT team_id FROM teams WHERE team_name = 'Brighton & Hove Albion FC' LIMIT 1),
(SELECT team_id FROM teams WHERE team_name = 'West Ham United FC' LIMIT 1),
1,
0
);

-- Eight Match
INSERT INTO matches(match_start_time,home_team_id,away_team_id,home_team_score,away_team_score)
VALUES(
'2023-08-29 19:00:00',
(SELECT team_id FROM teams WHERE team_name = 'Brighton & Hove Albion FC' LIMIT 1),
(SELECT team_id FROM teams WHERE team_name = 'Chelsea FC' LIMIT 1),
NULL,
NULL
);

-- Ninth Match
INSERT INTO matches(match_start_time,home_team_id,away_team_id,home_team_score,away_team_score)
VALUES(
'2022-04-24 17:00:00',
(SELECT team_id FROM teams WHERE team_name = 'Southampton FC' LIMIT 1),
(SELECT team_id FROM teams WHERE team_name = 'Manchester United FC' LIMIT 1),
2,
0
);
