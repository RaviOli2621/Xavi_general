<?php
//Xavi Rubio Monje
    function buscarBD($consulta)//Funció que executa les cosultes i les retorna en forma de array
    {
        $consulta->execute();
        $result = $consulta->fetchAll();
        return $result;
    }
?>