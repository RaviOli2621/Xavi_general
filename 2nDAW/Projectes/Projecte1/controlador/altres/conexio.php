<?php
	require "../.env.php";
	$usuarioBD = USUARIOBD;
	$contrasenyaBD = CONTRASENYABD;
	$host = HOST;
	$baseDatos = BASEDATOS; 
	$connexio = new PDO('mysql:host='.$host.';dbname='.$baseDatos.';charset=utf8mb4', $usuarioBD, $contrasenyaBD);
?>