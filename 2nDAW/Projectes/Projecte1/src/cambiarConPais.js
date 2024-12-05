let selectCont = document.getElementById("continent");
let selectPais = document.getElementById("country");
let oculto = document.getElementById("hiddPais");
//Falta lo mismo por continente y que cuando seleccionas ninguna elección se restauren todas
selectCont.addEventListener("change", cambiarPais)
function cambiarPais(){
    selectPais.options.length = 1;   

    for(let i = 0; i < oculto.value.split("|").length; i++)
        {
                if((selectCont.options[selectCont.selectedIndex].value == "continentDef" && i != selectPais.options.length) || oculto.value.split("|")[i].split("/")[1] == selectCont.options[selectCont.selectedIndex].value)
                {
                    let opcion = new Option(oculto.value.split("|")[i].split("/")[0],oculto.value.split("|")[i].split("/")[0]);
                    selectPais.append(opcion);
                }
        }
    cambiarImagen("");
}
selectPais.addEventListener("change", function(e)
{
    
    let paisSeleccionat = selectPais.options[selectPais.selectedIndex].value;

    cambiarPrecio();

    for(let i = 0; i < selectPais.options.length-1; i++)
        {
                if(oculto.value.split("|")[i].split("/")[0] == paisSeleccionat)
                {
                    let debug = oculto.value.split("|")[i].split("/")[1];
                    for(let j = 0; j < selectCont.length; j++)
                        {
                            if(selectCont.options[j].value == debug)
                                {
                                    selectCont.options.item(j).selected = 'selected';
                                }
                        }
                }
        }
    cambiarPais();
    selectPais.options.item(paisSeleccionat).selected = 'selected';
    //tornar a seleccionar el pais
    tornarSeleccionarPais(paisSeleccionat);
    cambiarImagen(paisSeleccionat);
})
function tornarSeleccionarPais(paisSeleccionat)
{
    for(let i = 0; i < selectPais.options.length; i++)
        {
                if(selectPais.options.item(i).value == paisSeleccionat)
                {
                    selectPais.options.item(i).selected = 'selected';
                }
        }
}
