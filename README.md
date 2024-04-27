# Xavi_general. Explicacion de las funcionses y variables:

## static ArrayList<Productes> carrito
Variable encargada de almacenar todos los objetos de tipo Productes

## static String prefixRutas
Variable encargada de guardar una parte de la ruta que solo es utilizada si el proyecto se encuentra en mi repositorio (Xavi_general)

## main
Lo primero que se ejecuta es la comprobacion si el proyecto se esta ejecutando desde el repositorio de github para poder ajustar las rutas de las carpetas, después crea los documentos

## estoyEnMiRepositorio
Funcion encargada de adaptar las rutas segun si estoy en mi repositorio de github o no

## EditarDocumentos
Primero guarda el contenido del documento en un String (ya que el PrintStream se carga el contenido del documento y quiero que los errores y datos mas recientes salgan arriba), después
inserta el mensaje(ya sea el codigo de error o un codigo de barras y su precio). Al final ponemos el contenido que ya tenia el documento de vuelta

## Textil
Para comprobar que no hay codigos de barras repetidos y que los precios van acorde con los codigos de barras se utilizan las funciones "noRepTextil" y "preuTextil"

## preuTextil
Esta funcion recorre el documento UpdateTextilPrices.dat para encontrar coincidencias en el codigo de barras. Si el codigo de barras del nuevo producto se encuentra en el documento se actualiza al que estaba especificado.
Si no se encuentra en el documento se añade el nuevo codigo de barras y el precio en el documento
EL formato del documento es: codigo//precio
## noRepTextil
Mira la array de carrito y si encunetra un objeto de instancia Textil que tenga su mismo codigo de barras pues no añade el producto al carro

## OrdenarMostrarCarret
Primero ordenamos carrito segun que classe es el objeto y luego los textiles segun la composicion

## PasarPerCaixa
Crea un LinkedHashMap donde las keys son los codigos de barras + el precio unitario (String) y los valores son el nombre + el producto (String)
Si la key esta repetida aumenta la cantidad en el valor
Para poder acceder al nombre y a la cantidad en el valor transformo el String del valor en una array con el split como separador //

## MostrarCarret
Crea un LinkedHashMap donde las keys son los codigos de barras (String) y los valores son el nombre + el producto (String[])
Si la key esta repetida aumenta la cantidad en el valor
