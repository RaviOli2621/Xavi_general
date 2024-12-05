function fechaMaxIMinData()
{
    let fechaAct = (new Date ()).toLocaleDateString();
    document.getElementById("fecha").min = fechaAct.split("/")[2] + "-" + fechaAct.split("/")[1] + "-" + fechaAct.split("/")[0];
    document.getElementById("fecha").value = fechaAct.split("/")[2] + "-" + fechaAct.split("/")[1] + "-" + fechaAct.split("/")[0];
    let ultimaFecha = (parseInt(fechaAct.split("/")[2])+10 + "-" + fechaAct.split("/")[1] + "-" + fechaAct.split("/")[0]);
    document.getElementById("fecha").max = ultimaFecha;

}
fechaMaxIMinData();