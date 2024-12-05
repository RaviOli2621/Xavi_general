<?php 

//include  "../model/model.php";
    function inserirReserves($telefon,$usuari,$data,$pais,$num_persones,$preu_final,$imagen)
    {
        try
        {
            include "altres/conexio.php";
            $insertar = $connexio->prepare("INSERT INTO reservas (telefon,usuari,data,pais,num_persones,preu_final,imagen) VALUES(:telefon,:usuari,:data,:pais,:num_persones,:preu_final,:imagen)");
                        
            $insertar->bindParam(":telefon",$telefon);
            $insertar->bindParam(":usuari",$usuari);
            $insertar->bindParam(":data",$data);
            $insertar->bindParam(":pais",$pais);
            $insertar->bindParam(":num_persones",$num_persones);
            $insertar->bindParam(":preu_final",$preu_final);    
            $insertar->bindParam(":imagen",$imagen);    
            $result = buscarBD($insertar);
            return "<tr><td id=\"Res\">Operació exitosa</td></tr>";
        }catch (PDOException $e){ //
            // mostrarem els errors
            return "<tr><td id=\"ResM\">Error: " . $e->getMessage()."</td></tr>";
        }
    }
    function eliminarReserves($id)
    {
        try
        {
            include "altres/conexio.php";
            
            $eliminar = $connexio->prepare("DELETE FROM reservas WHERE(id = :id)");
                        
            $eliminar->bindParam(":id",$id);;    
            $result = buscarBD($eliminar);
            return "<tr><td id=\"Res\">Operació exitosa</td></tr>";
        }catch (PDOException $e){ //
            // mostrarem els errors
            return "<tr><td id=\"ResM\">Error: " . $e->getMessage()."</td></tr>";
        }
    }
    function mostrarReserves()
    {
        try
        {
            include "altres/conexio.php";
            $comprobar = $connexio->prepare("SELECT * FROM reservas");
      
            $result = buscarBD($comprobar);
            //Hacer consulta
            $resTxt = "";
            $hayContenido = false;
            foreach ($result as $dada=>$valor){
                $telefon = "";
                $usuari = "";
                $data = "";
                $pais = "";
                $num_persones = "";
                $preu_final = "";
                $imagen = "";
                $id = "";

                foreach ($valor as $dada2=>$valor2){
                    if($dada2 == "telefon")
                    {
                        $telefon = $valor2;
                        $hayContenido = true;
                    }
                    if($dada2 == "usuari")
                    {
                        $usuari = $valor2;
                    }
                    if($dada2 == "data")
                    {
                        $data = $valor2;
                    }
                    if($dada2 == "pais")
                    {
                        $pais = $valor2;
                    }
                    if($dada2 == "num_persones")
                    {
                        $num_persones = $valor2;
                    }
                    if($dada2 == "preu_final")
                    {
                        $preu_final = $valor2;
                    }
                    if($dada2 == "imagen")
                    {
                        $imagen = $valor2;
                    }
                    if($dada2 == "id")
                    {
                        $id = $valor2;
                    }
                }

                $resTxt .= $telefon."\\".$usuari."\\".$data."\\".$pais."\\".$num_persones."\\".$preu_final."\\".$imagen."\\".$id."|";
            
            }
            if($hayContenido)
            {
                $resTxt = substr($resTxt, 0, -1);
                return $resTxt;

            }
            else return "<tr><td>No hi ha coincidencies</td></tr>";
        }catch (PDOException $e){ //
            // mostrarem els errors
            return "<tr><td id=\"ResM\">Error: " . $e->getMessage()."</td></tr>";
        }
    }
?>