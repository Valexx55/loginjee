/**
 * 
 */

//todo OBTENER LOS DATOS DEL FORMULARIO LOGIN Y PWD INTRODUCIDOS
//POR EL CLIENTE
//Y ENVIARLOS AL SERVIDOR, PARA COMPROBAR SI ESE USUARIO EXISTE
//O ESTÁ REGISTRADO
const URL_LOGIN = "http://localhost:8080/loginjee/Login"
const URL_LOGIN_RELATIVA = "/loginjee/Login"

//https://www.bing.com/search?q=realm+adrid&cvid=6da69e504f24467c997e6a1210719880&FORM=ANNTA1&PC=U531
//SERVICIO ES SINÓNIMO DE Servlet en JAVA
var xhr = new XMLHttpRequest();
function servidorlogin() {
	console.log("Entrando al servidor");
	var name = document.getElementById("nombre").value;
	var password = document.getElementById("pwd").value;

	var usuario = {
		nombre : name,
		pwd : password
	};

	var jsonusuario = JSON.stringify(usuario);
	console.log(jsonusuario);
	//si en vio info dsede el cliente al servidor debe usar post

	//TODO usar AJAX para enviar al servidor los datos
	xhr.open("POST", URL_LOGIN_RELATIVA);
	//deberíamos setear el content-type
	xhr.setRequestHeader("Content-type", "application/json;UTF-8");
	xhr.onreadystatechange = respuestaLogin;//programar el callback
	xhr.send(jsonusuario);
	//console.log ("la respuesta del soervidor ha sido ..");

}

function respuestaLogin() {
	//esta función será invocada cuando vuelva del servidor
	if (xhr.readyState == 4) {
		console.log("La respuesta del servidor ha llegado");
		if (xhr.status == 200) {
			alert("Logueado con éxito");
			location="menu.html";
		} else if (xhr.status == 204) {
			alert("Nombre incorrecto. No existe el usuario");
		} else if (xhr.status == 403) {
			alert("Password incorrecta.");
		} else if (xhr.status == 500) {
			alert("Ha habido un error. Intentelo más tarde");
		}
	}

}
