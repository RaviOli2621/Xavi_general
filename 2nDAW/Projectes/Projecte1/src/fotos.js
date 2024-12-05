let imagen = document.getElementById("fotoDesti");
let srcImg = document.getElementById("fotoDestiSRC");

function cambiarImagen(paisSeleccionat)
{
    let encuentraIm = false;

    for(let i = 0; i < oculto.value.split("|").length; i++)
        {
                if(oculto.value.split("|")[i].split("/")[0] == paisSeleccionat)
                {
                    imagen.setAttribute("src","../fotos/"+oculto.value.split("|")[i].split("/")[2]);
                    srcImg.value = "../fotos/"+oculto.value.split("|")[i].split("/")[2];
                    encuentraIm = true;
                }
        }
    if(!encuentraIm)
        {
            imagen.setAttribute("src","../fotos/Foto.png");
            srcImg.value = "../fotos/Foto.png";
        }
}
let imagenR = document.getElementsByName("TabCerc");