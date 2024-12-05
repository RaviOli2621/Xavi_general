<?php 

include  "../model/model.php";
    function continente(string $pais)
    {
        try
        {
            include "altres/conexio.php";
            if($pais == "")
            {
                $comprobar = $connexio->prepare("SELECT continente FROM destinos");                
            }else
            {
                $comprobar = $connexio->prepare("SELECT continente FROM destinos WHERE pais = :pais");
                $comprobar->bindParam(":pais",$pais);
            }

            $result = buscarBD($comprobar);
            //Hacer consulta
            $resTxt = "";
            $hayContenido = false;
            foreach ($result as $dada=>$valor){
                foreach ($valor as $dada2=>$valor2){
                    if($dada2 == "continente")
                    {
                        if($pais == "" && !str_contains($resTxt,'value="'.$valor2.'"'))
                        {
                            $resTxt .= "<option name='continente' value=\"$valor2\">$valor2</option>";
                            $resTxt .= "\n";
                        }else
                        {
                            //$resTxt .= $valor2 . "<br>";
                            //$resTxt .= "\n";
                        }
                        $hayContenido = true;
                    }
                }
            }
            if($hayContenido)return $resTxt;
            else return "<tr><td>No hi ha coincidencies</td></tr>";
            
        }catch (PDOException $e){ 
            // mostrarem els errors
            return "<tr><td id=\"ResM\">Error: " . $e->getMessage()."</td></tr>";
        } 
    }
    function pais(string $hidden)
    {
        try
        {
            include "altres/conexio.php";
            $comprobar = $connexio->prepare("SELECT pais,continente,imagen,precio FROM destinos");
      
            $result = buscarBD($comprobar);
            //Hacer consulta
            $resTxt = "";
            $hayContenido = false;
            foreach ($result as $dada=>$valor){
                $pais = "";
                $continentePais = "";
                $imagenPais = "";
                $precioPais = "";
                foreach ($valor as $dada2=>$valor2){
                    if($dada2 == "pais")
                    {
                        $pais = $valor2;

                        $hayContenido = true;
                    }
                    if($dada2 == "continente")
                    {
                        $continentePais = $valor2;
                    }
                    if($dada2 == "imagen")
                    {
                        $imagenPais = $valor2;
                    }
                    if($dada2 == "precio")
                    {
                        $precioPais = $valor2;
                    }
                }
                if($hidden == false)
                {
                    $resTxt .= "<option name='pais' id='".$pais."/".$continentePais."' value=\"$pais\">$pais</option>\n";                 
                }else
                {
                    $resTxt .= $pais."/".$continentePais."/".$imagenPais."/".$precioPais."|";
                }
            }
            if($hayContenido)
            {
                $resTxt = substr($resTxt, 0, -1);
                return $resTxt;

            }
            else return "<tr><td>No hi ha coincidencies</td></tr>";
            
        }catch (PDOException $e){ 
            // mostrarem els errors
            return "<tr><td id=\"ResM\">Error: " . $e->getMessage()."</td></tr>";
        } 
    }
    function descuento()
    {
        try
        {
            include "altres/conexio.php";
            $comprobar = $connexio->prepare("SELECT * FROM ofertas");
      
            $result = buscarBD($comprobar);
            //Hacer consulta
            $resTxt = "";
            $hayContenido = false;
            foreach ($result as $dada=>$valor){
                $pais = "";
                $min_personas = "";
                $descuento = "";
                foreach ($valor as $dada2=>$valor2){
                    if($dada2 == "pais")
                    {
                        $pais = $valor2;

                        $hayContenido = true;
                    }
                    if($dada2 == "min_personas")
                    {
                        $min_personas = $valor2;
                    }
                    if($dada2 == "descuento")
                    {
                        $descuento = $valor2;
                    }
                }

                $resTxt .= $pais."/".$min_personas."/".$descuento."|";
                
            }
            if($hayContenido)
            {
                $resTxt = substr($resTxt, 0, -1);
                return $resTxt;

            }
            else return "<tr><td>No hi ha coincidencies</td></tr>";
            
        }catch (PDOException $e){ 
            // mostrarem els errors
            return "<tr><td id=\"ResM\">Error: " . $e->getMessage()."</td></tr>";
        } 
    }
    function inserir($titol,$cos)//Funció per inserir dades a la BD
    {
        try
        {
            include "altres/conexio.php";
            $insertar = $connexio->prepare("INSERT INTO articles (titol,cos) VALUES(:titol, :cos)");
            $comprobar = $connexio->prepare("SELECT titol, cos FROM articles WHERE titol = :titulo");
                        
            $insertar->bindParam(":titol",$titol);
            $insertar->bindParam(":cos",$cos);
            $comprobar->bindParam(":titulo",$titol);
            $result = buscarBD($comprobar);
            if((empty($result)))
            {
                $result = buscarBD($insertar);
                return "<tr><td id=\"Res\">Operació exitosa</td></tr>";
            }
            return "<tr><td id=\"ResM\">Titol ja existent (si ha recarregat la pagina un cop la feina ja s'ha portat a terme pot sortir aquest misatge)</td></tr>";
        }catch (PDOException $e){ //
            // mostrarem els errors
            return "<tr><td id=\"ResM\">Error: " . $e->getMessage()."</td></tr>";
        }
        
    }
    function eliminar($titol)//Funció per eliminar dades a la BD
    {
        try
        {
            include "altres/conexio.php";
            $eliminar = $connexio->prepare("DELETE FROM articles WHERE(titol = :titol)");
            $comprobar = $connexio->prepare("SELECT titol, cos FROM articles WHERE titol = :titulo");
                        
            $eliminar->bindParam(":titol",$titol);
            $comprobar->bindParam(":titulo",$titol);
            $result = buscarBD($comprobar);
            if((!empty($result)))
            {
                $result = buscarBD($eliminar);
                return "<tr><td id=\"Res\">Operació exitosa</td></tr>";
            }
            return "<tr><td id=\"ResM\">Titol no existent (si ha recarregat la pagina un cop la feina ja s'ha portat a terme pot sortir aquest misatge)</td></tr>";
        }catch (PDOException $e){ //
            // mostrarem els errors
            return "<tr><td id=\"ResM\">Error: " . $e->getMessage()."</td></tr>";
        }
        
    }
    
    function modificar($titolOr,$titol,$cos)//Funció per modificar dades a la BD
    {
        try
        {
            include "altres/conexio.php";
            $actualizar = $connexio->prepare("UPDATE articles SET titol = :titol, cos = :cos WHERE titol = :titolOr");
            $comprobar = $connexio->prepare("SELECT titol, cos FROM articles WHERE titol = :titulo");
                        
            $actualizar->bindParam(":titol",$titol);
            $actualizar->bindParam(":titolOr",$titolOr);
            $actualizar->bindParam(":cos",$cos);
            $comprobar->bindParam(":titulo",$titolOr);
            $result = buscarBD($comprobar);
            if((!empty($result)))
            {
                $comprobar->bindParam(":titulo",$titol);
                $result = buscarBD($comprobar);
                if((empty($result)) || $titolOr == $titol)
                {
                    $result = buscarBD($actualizar);
                    return "<tr><td id=\"Res\">Operació exitosa</td></tr>";
                }
                return "<tr><td id=\"ResM\">Nou titol ja existent (si ha recarregat la pagina un cop la feina ja s'ha portat a terme pot sortir aquest misatge)</td></tr>";
            }
            return "<tr><td id=\"ResM\">Titol no existent (si ha recarregat la pagina un cop la feina ja s'ha portat a terme pot sortir aquest misatge)</td></tr>";
        }catch (PDOException $e){ //
            // mostrarem els errors
            return "<tr><td id=\"ResM\">Error: " . $e->getMessage()."</td></tr>";
        }
        
    }
    function buscar($titol,$cos)//Funció per cercar dades a la BD
    { 
        try
        {
            include "altres/conexio.php";
            if($titol != "")
            {
                if($cos != "")
                {
                    $comprobar = $connexio->prepare("SELECT titol, cos FROM articles WHERE titol = :titulo AND cos = :cuerpo");
                    $comprobar->bindParam(":titulo",$titol);
                    $comprobar->bindParam(":cuerpo",$cos);
                }else
                {
                    $comprobar = $connexio->prepare("SELECT titol, cos FROM articles WHERE titol = :titulo");
                    $comprobar->bindParam(":titulo",$titol);
                } 
            }else if($cos != "")
            {
                $comprobar = $connexio->prepare("SELECT titol, cos FROM articles WHERE cos = :cuerpo");
                $comprobar->bindParam(":cuerpo",$cos);
            }else
            {
                $comprobar = $connexio->prepare("SELECT titol, cos FROM articles");
            }
            

            $result = buscarBD($comprobar);
            return $result;
        }catch (PDOException $e){ 
            // mostrarem els errors
            return "<tr><td id=\"ResM\">Error: " . $e->getMessage()."</td></tr>";
        } 
    }
?>