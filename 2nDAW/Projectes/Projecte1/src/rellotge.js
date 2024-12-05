function hora(ara){
	let moment = "AM";
	let hora = ara.getHours() + "";
	if(ara.getHours() >= 12) 
		{
			if(ara.getHours() > 12) hora = (ara.getHours()-12) + "";
			moment = "PM";
		}
	if(hora.length < 2) hora = "0" + hora;
	let minut = ara.getMinutes() + "";
	if(minut.length < 2) minut = "0" + minut;
	let segon = ara.getSeconds() + "";
	if(segon.length < 2) segon = "0" + segon;
	return hora + ":" + minut + ":" + segon + " " + moment;
}
function dies(ara)
{
	let dies = ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"];
	return dies[ara.getDay()	];
}
function dataDia(ara)
{
	let dia = ara.getDate() + "";
	let mes = (ara.getMonth()+1) + "";
	let any = ara.getFullYear() + "";
	return dia + " / " + mes + " / " + any;
}
function rellotge(ara) {
	let txt = hora(ara); 
	txt += "<br>" + dies(ara);
	txt += "<br>" + dataDia(ara);
	return txt;
}

function clock() {
	document.getElementById('data').innerHTML = rellotge(new Date());
}

function init() {
	clock();
	setInterval(clock, 1000);
}
