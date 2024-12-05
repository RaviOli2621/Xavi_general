<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Wonderfull Travels</title>
    <link rel="stylesheet" href="estils.css">
    <style>
		div.datahora{font-size:1.2em; font-weight:bold; color:white; text-align:center; border-radius:0.9em; background-image:linear-gradient(#04376a, #4578ab); width:30em; padding:1.4em 0;}
		iframe{border:1px solid black; width:1000px; height: 500px;}
    </style>
	<script defer src="../src/codigoReloj.js"></script>
	<script defer src="../src/dataMin.js"></script>
	<script defer src="../src/cambiarConPais.js"></script>
	<script defer src="../src/precio.js"></script>
	<script defer src="../src/fotos.js"></script>
</head>
<body>
    <h1>Wonderfull Travels</h1>
	<!--<div id="data" class="datahora"></div><br>-->
    <div>
        <div class = "reloj">
                            <div class = "hora">
                                <div class = "hr" id="hr"></div>
                            </div>
                            <div class = "min">
                                <div class = "mn" id="mn"></div>
                            </div>
                            <div class = "seg">
                                <div class = "sc" id="sc"></div>
                    </div>  
        </div>
    <form method="post" action="<?php echo htmlentities($_SERVER["PHP_SELF"]);?>">
        <div class="dadesDiv">
            <div class="dadesDivChilld">
                <ul><!--Data-->
                    <label for="startTxt">Data:</label>  
                    <input type="date" id="fecha" name="fecha" value="2024-01-01" min="2024-01-01" max="2034/12/31" />
                </ul>
                <ul><!--Viatges-->
                    <div class="SetInLine">
                        <div style="width: 30%;">
                            <label for="lang">Destí:    </label>
                        </div>
                        <select name="Continent" id="continent">
                        <option name="ContinentDef" value="continentDef" selected>Continent</option>
                        <?php
                            include_once "../controlador/controladorCuest.php";
                            echo continente("");
                        ?>
                        </select>
                        
                        <select name="Pais" id="country">
                        <option name="paisDef" value="paisDef" selected>País</option>
                        <?php
                            echo pais(false);
                        ?>
                        </select>
                        <input type="hidden" id="hiddPais" value="<?php echo pais(true)?>">
                    </div>
                </ul>

                <ul> <!--Preu-->
                    <div class="SetInLineSmall">
                        <div style="width: 15%;">
                            <label for="preu">Preu: </label>  
                        </div>
                        <input class="readOnly" type="number" name="basePrice" id="basePrice" readonly/>
                        <label for="€">€</label>  
                    </div>
                </ul>
                <ul> <!--Nom-->
                    <div class="SetInLineSmall">
                        <div style="width: 15%;">   
                            <label for="nom">Nom: </label>  
                        </div>
                        <input type="text" name="nom" id="nom"/>
                    </div>
                </ul>
                <ul> <!--Telefon-->
                    <div class="SetInLineSmall">
                        <div style="width: 15%;">
                            <label for="telefon">Telefon: </label>  
                        </div>
                        <input type="text" id="phone" name="phone" placeholder="+34123456789" pattern="(+[0-9]{2} )?[0-9]{3}[0-9]{2}[0-9]{2}[0-9]{2}"/>
                    </div>
                </ul>
                <ul> <!--Persones-->
                    <div class="SetInLineSmall">
                        <div style="width: 15%;">
                            <label  for="persones">Num persones: </label>  
                        </div>
                        <input type="number" name="numPers" min="1" id="numPers"/>
                    </div>
                </ul>
                <ul> <!--Descompte-->
                    <div class="SetInLineSmall">
                        <div>
                            <label id="textDescompte" for="descompte">? persones per a ?% descompte: </label>  
                        </div>
                        <input type="checkbox" name="descompte" id="descompte" onclick="return false;"/> <!--Habra que cambiar el "onclick" para poder modificarla-->
                    </div>
                    <input type="hidden" id="hiddDesc" readonly value="<?php echo descuento()?>">
                </ul>
                <ul>
                    <input id="enviar" class="enviar" type="submit" name="inserir" value="Inserir" />
                </ul>
                <ul>
                    <input type="hidden" id="fotoDestiSRC" name="fotoDestiSRC" value="">
                </ul>
            </div>
            <div class="dadesDivChilld">
                <img id="fotoDesti" name="fotoDesti" class="fotoDesti" src="../fotos/Foto.png"/>
            </div>
        </div>
    </form>
    <form method="post" id="BorrarReserva" action="<?php echo htmlentities($_SERVER["PHP_SELF"]);?>">
    <div class="resultado"><!--Funció encarregada de inserir les dades indicades i retornar si s'ha pogut fer la acció-->
                    <?php
                        include_once "../controlador/controladorReservas.php";
                        if(isset($_POST['inserir']))
                        {
                            $t = $_POST['phone'];
                            $u = $_POST['nom'];
                            $v = $_POST['fecha'];
                            $p = $_POST['Pais'];
                            $n = $_POST['numPers'];
                            $pf = $_POST['basePrice'];
                            $i = $_POST['fotoDestiSRC'];//aquiiiiiiiiiiiiiiiiiiiiiii
                            if(strlen(preg_replace("/\s+/","",$u)) < 1)
                            {
                                print_r("<table style='margin-left: auto;margin-right: auto;'><tr><td id=\"ResM\">El nom no pot ser ni buit ni només espais</td></tr></table><br>");
                            }else
                            {
                                $result = inserirReserves($t ?? "",$u,$v,$p,$n,$pf,$i);
                                $resTxt = "";
                                $hayContenido = false;
                                print_r($result . ". Reserva hecha con exito<br>");    
                            }
                        }elseif(isset($_POST["IdABorrar"]))
                        {
                            $id=$_POST['IdABorrar'];
                            $result = eliminarReserves($id ?? "");
                            print_r($result . ". Reserva borrada con exito<br>");    
                        }
                    ?>
                    <?php
                        $result = explode("|",mostrarReserves());
						$resTxt = "";
						$hayContenido = false;
						foreach ($result as $dada=>$valor){//imprimir datos
                            $resTxt .= "<div id=\"TabCerc\" name=\"\" style=\"background-image: url('".explode("\\",$valor)[6]."'); background-repeat: no-repeat;  background-size: 100% 100%;; \">";//cada tabla es un articulo. Estructura: (div(div(datos)div(Icono basura))div(foto))
							$resTxt .= "<div id='datos'>".
                            "<div id='datosChild'>
                            <tr id=\"DadCerc\"><td><input id=\"text2\" type=\"text\" placeholder=\"Buit\" value=\"".explode("\\",$valor)[2]."\" readonly/></td></tr>
                            <tr id=\"DadCerc\"><td><input id=\"text2\" type=\"text\" placeholder=\"Buit\" value=\"".explode("\\",$valor)[3]."\" readonly/></td></tr>
                            <tr id=\"DadCerc\"><td><input id=\"text2\" type=\"text\" placeholder=\"Buit\" value=\"".explode("\\",$valor)[0]."\" readonly/></td></tr>
                            <tr id=\"DadCerc\"><td><input id=\"text2\" type=\"text\" placeholder=\"Buit\" value=\"".explode("\\",$valor)[1]."\" readonly/></td></tr>
                            <tr id=\"DadCerc\"><td><input id=\"text2\" type=\"text\" placeholder=\"Buit\" value=\"".explode("\\",$valor)[4]." persones\" readonly/></td></tr>
                            <tr id=\"DadCerc\"><td><input id=\"idDest".explode("\\",$valor)[7]."\" type=\"hidden\" placeholder=\"Buit\" value=\"".explode("\\",$valor)[7]."\" readonly/></td></tr>
                            <tr id=\"DadCerc\"><td><input id=\"text2\" type=\"text\" placeholder=\"Buit\" value=\"".explode("\\",$valor)[5]."€\" readonly/></td></tr></div>";                 
							$hayContenido = true;
                            
                            $resTxt .= "<div id='datosChild'><tr id=\"DadCerc\"><td>
                                <button name='IdABorrar' value='".explode("\\",$valor)[7]."' style=\"border:0px solid black; background-color: transparent;   \" >
                                <img id='basura' src='../fotos/basura.png' alt='basura'></button></td></tr></div>";
							$resTxt .= "</div></div>";
                        }
						if($hayContenido)echo $resTxt;
                    ?>
    </div>
    </form>
</div>
    
</body>
</html>  