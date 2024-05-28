/* Eliminem la base de dades si existeix */
DROP DATABASE IF EXISTS NBAData;

/* Crear la base de dades 1*/
CREATE DATABASE NBAData;

USE NBAData;

CREATE TABLE `equips` (
  `equip_id` int UNSIGNED NOT NULL,
  `ciutat` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `acronim` char(3) NOT NULL,
  `divisio` varchar(50) NOT NULL,
  `guanyades` tinyint UNSIGNED NOT NULL DEFAULT '0',
  `perdudes` tinyint UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `estadistiques_jugadors` (
  `jugador_id` int UNSIGNED NOT NULL,
  `partit_id` int UNSIGNED NOT NULL,
  `minuts_jugats` decimal(5,3) UNSIGNED NOT NULL,
  `punts` tinyint UNSIGNED NOT NULL,
  `tirs_anotats` tinyint UNSIGNED NOT NULL,
  `tirs_tirats` tinyint UNSIGNED NOT NULL,
  `tirs_triples_anotats` tinyint UNSIGNED NOT NULL,
  `tirs_triples_tirats` tinyint UNSIGNED NOT NULL,
  `tirs_lliures_anotats` tinyint UNSIGNED NOT NULL,
  `tirs_lliures_tirats` tinyint UNSIGNED NOT NULL,
  `rebots_ofensius` tinyint UNSIGNED NOT NULL,
  `rebots_defensius` tinyint UNSIGNED NOT NULL,
  `assistencies` tinyint UNSIGNED NOT NULL,
  `robades` tinyint UNSIGNED NOT NULL,
  `bloqueigs` tinyint UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `jugadors` (
  `jugador_id` int UNSIGNED NOT NULL,
  `nom` varchar(150) NOT NULL,
  `cognom` varchar(150) NOT NULL,
  `data_naixement` date DEFAULT NULL,
  `alcada` decimal(5,2) UNSIGNED DEFAULT NULL,
  `pes` decimal(5,2) UNSIGNED DEFAULT NULL,
  `dorsal` char(2) NOT NULL,
  `posicio` varchar(25) NOT NULL,
  `equip_id` int UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `partits` (
  `partit_id` int UNSIGNED NOT NULL,
  `equip_id` int UNSIGNED NOT NULL,
  `data_partit` date NOT NULL,
  `matx` char(12) NOT NULL,
  `resultat` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `historic`(
  `nom` varchar(70) NOT NULL,
  `jugador_id` int UNSIGNED NOT NULL,
  `ultim_equip_id` int UNSIGNED NOT NULL,
  `tot_min_jugats` decimal(5,3) UNSIGNED NOT NULL,
  `punts_tot` int UNSIGNED NOT NULL,
  `tirs_anotats` int UNSIGNED NOT NULL,
  `tirs_tirats` int UNSIGNED NOT NULL,
  `tirs_triples_anotats` int UNSIGNED NOT NULL,
  `tirs_triples_tirats` int UNSIGNED NOT NULL,
  `tirs_lliures_anotats` int UNSIGNED NOT NULL,
  `tirs_lliures_tirats` int UNSIGNED NOT NULL,
  `rebots_ofensius` int UNSIGNED NOT NULL,
  `rebots_defensius` int UNSIGNED NOT NULL,
  `assistencies` int UNSIGNED NOT NULL,
  `robades` int UNSIGNED NOT NULL,
  `bloqueigs` int UNSIGNED NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

alter table estadistiques_jugadors
add equip_id int unsigned not null default 0;

SELECT avg(rebots_defensius) FROM estadistiques_jugadors WHERE jugador_id = 200782;