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
	//alert("Entrando en listarUsuariosServidor");
	xhr.open("GET", URL_LISTAR_USUARIOS_JSON);
	//deberíamos setear el content-type
	xhr.onreadystatechange = respuestaListaUsuariosJson;//programar el callback
	xhr.send(null);
	//console.log ("la respuesta del soervidor ha sido ..");

}
function obtenerDetalle ()
{
	console.log ("tenemos que obtener detalle de nombre");
	//usar el this
	console.log ("Ha tocado el id = " + this.id);
	//TODO 1.ir al servidor y pedirle la info de un usuario dándole el id
	//2. recibir el json del usuario del servidor y mostrar su detalle
}

//{"id":1,"nombre":"vale","pwd":"valeri"}
function crearTRUsuario (usuario)
{
	let fila_usuario = document.createElement ("tr");
	//let columna_id = document.createElement ("td");
	let columna_nombre = document.createElement ("td");
	//let columna_pwd = document.createElement ("td");
	
	//columna_id.innerHTML = usuario.id;
	columna_nombre.innerHTML = usuario.nombre;
	//columna_pwd.innerHTML = usuario.pwd;
	
	//columna_id.setAttribute ("hidden", true);
	columna_nombre.id = usuario.id;
	columna_nombre.addEventListener("click", obtenerDetalle);
	
	//fila_usuario.appendChild (columna_id);
	fila_usuario.appendChild (columna_nombre);
	//fila_usuario.appendChild (columna_pwd);
	
	console.log ("ID USUARIO = " + usuario.id);
	console.log ("NOMBRE USUARIO = " + usuario.nombre);
	console.log ("ID USUARIO = " + usuario.pwd);
	
	return fila_usuario;
}

function mostrarUsuarios (listausuarios)
{
	let lu = JSON.parse(listausuarios);//de texto a variable js
	
	let tabla_usuarios = document.createElement ("table");
	let div_usuarios = document.getElementById ("divusuuarios");
	div_usuarios.appendChild (tabla_usuarios);
	let fila_nueva;
	
	for (let i = 0; i < lu.length; i++)
		{
			fila_nueva = crearTRUsuario (lu[i]);
			tabla_usuarios.appendChild (fila_nueva);
		}
	
}

function respuestaListaUsuariosJson() {
	//esta función será invocada cuando vuelva del servidor
	if (xhr.readyState == 4) {
		console.log("La respuesta del servidor ha llegado");
		if (xhr.status == 200) {
			alert("Lista de usuarios recibida");
			//TODO mostrar la lista en la paǵina
			console.log (xhr.responseText);
			mostrarUsuarios (xhr.responseText);
		} else if (xhr.status == 204) {
			alert("La lista está vacía");
			}
		else if (xhr.status == 500) {
			alert("Ha habido un error. Intentelo más tarde");
		}
	}

}
