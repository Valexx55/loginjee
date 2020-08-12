/**
 * 
 */

//window.onload = iniciaestilo;


function iniciaestilo ()
{
	console.log ("estilo iniciado");
	let anchoinput = document.getElementById("nombrebuscar").getBoundingClientRect().width;
	console.log ("ancho = " + anchoinput);
	document.getElementById("listasugerencias").width = anchoinput;
}

const URL_CONSULTAR_USUARIOS_PATRON_NOMBRE = "/loginjee/BusquedaPorPatron"

	//TODO PETICION A SERVIDOR PARA QUE NOS DEVUELVA LA LISTA DE NOMBRES COINCIDENTES
var xhr = new XMLHttpRequest();
function buscarXnombre() 
{
	console.log ("INTRO NOMBRE");
	let nombre = document.getElementById("nombrebuscar").value;
	console.log ("nombre = " + nombre);
	if (nombre.length > 0)
		{
		let url = URL_CONSULTAR_USUARIOS_PATRON_NOMBRE + "?patron="+nombre;
		console.log ("url = " + url);
		xhr.open("GET", url);
		xhr.onreadystatechange = pintarListaNombres;//programar el callback
		xhr.send(null);
		}
	
}

function mostrarNombre (nombre)
{
	let elemento_li_nuevo = document.createElement("li");//creamos el elemento nuevo
	elemento_li_nuevo.innerHTML = nombre;//le metemos el nombre
	let elemento_ul = document.getElementById ("listasugerencias");//cojo el padre
	elemento_ul.appendChild (elemento_li_nuevo);//y le agregamos un nuevo hijo
}

function mostrarListaNombres (lista_nombres)
{
	
	let lista_nombres_js = JSON.parse (lista_nombres);
	console.log (lista_nombres.length);
	console.log (lista_nombres_js.length);
	for (let i=0; i<lista_nombres_js.length; i++)
		{
		mostrarNombre (lista_nombres_js[i]);
		}
	
}

function pintarListaNombres ()
{
	if (xhr.readyState == 4) {
		console.log("La respuesta del servidor ha llegado");
		if (xhr.status == 200) {
			console.log("200 - Lista usuarios recibida");
			//TODO mostrar la lista en la paǵina
			console.log (xhr.responseText);
			mostrarListaNombres (xhr.responseText);
		} else if (xhr.status == 204) {
			console.log("204 - Lista usuarios vacia");
			}
		else if (xhr.status == 500) {
			console.log("500 -Ha habido un error. Intentelo más tarde");
		}
	}
}
