<?php
header("Location:".'http://'.$_SERVER['HTTP_HOST'].preg_split("/index\.php/",$_SERVER['REQUEST_URI'])[0]."vista/vista.php");
?>