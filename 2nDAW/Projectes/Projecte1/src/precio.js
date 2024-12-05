let precio = document.getElementById("basePrice");
let numPers = document.getElementById("numPers");
//ocult esta declarado en cambiarConPais y es el hidden con toda la info de la tabla destinos 

function cambiarPrecio()
{
    let paisSeleccionat = selectPais.options[selectPais.selectedIndex].value;
    if(paisSeleccionat != "paisDef")
        {
            selectPais.options.item(paisSeleccionat).selected = 'selected';
            //tornar a seleccionar el pais
            for(let i = 0; i < oculto.value.split("|").length; i++)
                {
                        if(oculto.value.split("|")[i].split("/")[0] == paisSeleccionat)
                        {
                            let preu = oculto.value.split("|")[i].split("/")[3];
                            calcularPrecio(preu,numPers.value,paisSeleccionat);
                        }
                }
        }else
        {
            precio.value = "";
            let textDesc = document.getElementById("textDescompte");
            textDesc.innerHTML = "? persones per a ?% descompte: ";
        }
   
}
numPers.value = 1;
numPers.addEventListener("change", function(e)
{
    let paisSeleccionat = selectPais.options[selectPais.selectedIndex].value;
    if(document.getElementById("numPers").value < 1)document.getElementById("numPers").value = 1
    cambiarPrecio();            
    tornarSeleccionarPais(paisSeleccionat);

});

function calcularPrecio(preu,numPers,paisSeleccionat)
{
    let desc = document.getElementById("hiddDesc");
    let precioFinal = preu*numPers;


    for(let i = 0; i < desc.value.split("|").length; i++)
        {
                let textDesc = document.getElementById("textDescompte");
                textDesc.innerHTML = "? persones per a ?% descompte: ";
                if(desc.value.split("|")[i].split("/")[0] == paisSeleccionat)
                {
                    let descuento = desc.value.split("|")[i].split("/")[2];
                    let personasMin = desc.value.split("|")[i].split("/")[1];
                    let descompteCheck = document.getElementById("descompte");
                    descompteCheck.checked = false; 
                    if(numPers - personasMin >= 0)
                        {
                            precioFinal = precioFinal - precioFinal*(descuento/100);
                            descompteCheck.checked = true; 
                        }
                    textDesc.innerHTML = personasMin + " persones per a " + descuento + "% descompte: ";
                    i = 1000;
                }
        }
    precio.value = precioFinal;

}
