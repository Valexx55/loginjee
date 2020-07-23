/**
 * 
 */

//todo OBTENER LOS DATOS DEL FORMULARIO LOGIN Y PWD INTRODUCIDOS
//POR EL CLIENTE
//Y ENVIARLOS AL SERVIDOR, PARA COMPROBAR SI ESE USUARIO EXISTE
//O ESTÁ REGISTRADO
const URL_LISTAR_USUARIOS_JSON = "/loginjee/ListarUsuariosJSON"

//https://www.bing.com/search?q=realm+adrid&cvid=6da69e504f24467c997e6a1210719880&FORM=ANNTA1&PC=U531
//SERVICIO ES SINÓNIMO DE Servlet en JAVA
var xhr = new XMLHttpRequest();
function listarUsuariosServidor() {
	alert("Entrando en listarUsuariosServidor");
	xhr.open("GET", URL_LISTAR_USUARIOS_JSON);
	//deberíamos setear el content-type
	xhr.onreadystatechange = respuestaListaUsuariosJson;//programar el callback
	xhr.send(null);
	//console.log ("la respuesta del soervidor ha sido ..");

}

function respuestaListaUsuariosJson() {
	//esta función será invocada cuando vuelva del servidor
	if (xhr.readyState == 4) {
		console.log("La respuesta del servidor ha llegado");
		if (xhr.status == 200) {
			alert("Lista de usuarios recibida");
			console.log (xhr.responseText);
		} else if (xhr.status == 204) {
			alert("La lista está vacía");
			}
		else if (xhr.status == 500) {
			alert("Ha habido un error. Intentelo más tarde");
		}
	}

}
