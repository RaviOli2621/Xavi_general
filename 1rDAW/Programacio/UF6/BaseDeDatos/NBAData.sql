/* Eliminem la base de dades si existeix */
DROP DATABASE IF EXISTS NBAData;

/* Crear la base de dades 1*/
CREATE DATABASE NBAData;

USE NBAData;

CREATE TABLE teams
(
	id VARCHAR(10) NOT NULL,
    full_name VARCHAR(65) NOT NULL,
    abbreviation VARCHAR(10) NOT NULL,
    nickname VARCHAR(30),
	city VARCHAR(30),
    state VARCHAR(30),
    is_active BOOLEAN,
    CONSTRAINT pk_teams PRIMARY KEY(id)
);

CREATE TABLE players
(
	id VARCHAR(10) NOT NULL,
    team_id VARCHAR(10) NOT NULL,
    full_name VARCHAR(65) GENERATED ALWAYS AS(concat(first_name," ", last_name)),
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    is_active BOOLEAN,
    CONSTRAINT pk_players PRIMARY KEY(id),
    CONSTRAINT fk_player_teams FOREIGN KEY (team_id)
		REFERENCES teams(id)
);

CREATE TABLE matchupsrollup
(
	LeagueID VARCHAR(10) NOT NULL,
    Season DECIMAL NOT NULL,
    SeasonType VARCHAR(30) NOT NULL,
	OffTeamID VARCHAR(30),
    OffPlayerID VARCHAR(30),
    DefTeamID VARCHAR(30),
	DefPlayerID VARCHAR(30)
);

INSERT INTO teams (id, full_name, abbreviation, nickname, city, state, is_active) VALUE 
	(1,"Los Juanes", "LJ", "Siuuuu", "Barcelona", "BarcelonaState", true),
    (2,"Los Pepes", "LP", "Nouuuuu", "Madrid", "MadridState", true),
    (3,"Los Mindundis", "LM", "NoHaceNada", "Valladolid", "ValladolidState", true);
INSERT INTO players (id, team_id,first_name, last_name, is_active) VALUE
	(1,1,"Mohamed", "Ali", true),
    (2,2, "Pedro", "Sanchez", false),
    (3,3,"Michael", "Jordan", true);
INSERT INTO matchupsrollup (LeagueId, Season, SeasonType, OffTeamID, OffPlayerID, DefTeamID, DefPlayerID) VALUE 
	(1, 1, "Siu", 1, 1, 2,2),
    (2, 2, "Siu", 2, 2, 3,3),
    (3, 3, "Siu", 1, 1, 3,3	);


SELECT * FROM teams;