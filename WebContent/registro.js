//EL NOMBRE DEL USUARIO TIENE QUE SER MINIMO DE 2
//LA PWD DEL USUARIO TIENE QUE SER DE AL MENOS 2
//TODO controlar que cuando el nombre NO esté disponible, el botón esté deshabilitado

const ULR_COMPROBAR_NOMBRE_DISPONIBLE = "/loginjee/ExisteNombre"
var xhr = new XMLHttpRequest();

function comprobarNombreDisponible() {
	console.log ("Comprobando nombre ...");
	let nuevo_nombre = document.getElementById("nombre").value;
	if (nombreCorrecto (nuevo_nombre))
		{
		let url = ULR_COMPROBAR_NOMBRE_DISPONIBLE + "?nombre=" +nuevo_nombre;
		console.log ("get sobre " + url);
		//LLAMAR AL SERVIDOR
		xhr.open("GET", url);
		//deberíamos setear el content-type
		xhr.onreadystatechange = respuestaNombreDisponible;//programar el callback
		xhr.send(null);
		}
}
function respuestaNombreDisponible ()
{
	if (xhr.readyState == 4) {
		console.log("La respuesta del servidor ha llegado");
		if (xhr.status == 200) {
			//esta disponible
			console.log ("disponible");
			let elemento_error = document.getElementById("errornombre");
			elemento_error.innerHTML = "";
		} else if (xhr.status == 409) {
			//NO esta disponible
			console.log (" NO disponible");
			let elemento_error = document.getElementById("errornombre");
			elemento_error.innerHTML = "¡Nombre no disponible!";
		} else if (xhr.status == 500) {
			console.log ("ERROR");
		}
	}
}

function nombreCorrecto (nombre)
{
	let correcto = false;
		
		correcto = (nombre.length >=2);
	
	return correcto;
}

function pwdCorrecto (pwd)
{
	let correcto = false;
		
		correcto = (pwd.length >=2);
	
	return correcto;
}

function activarBotonEnvio()
{
	let boton = document.getElementById("botonalta");
	boton.disabled = false;
}

function desactivarBotonEnvio()
{
	let boton = document.getElementById("botonalta");
	boton.disabled = true;
}

function modificadoFormulario() 
{
	let nuevo_nombre = document.getElementById("nombre").value;
	let nuevo_pwd = document.getElementById("pwd").value;
	
	if (nombreCorrecto(nuevo_nombre)&&(pwdCorrecto(nuevo_pwd)))
		{
			activarBotonEnvio();
		} else {
			desactivarBotonEnvio();
		}
	
	//mostrando errores personalizados
	/*let nombre_correcto =  false;
	let pwd_correcto =  false;
	
	if (nombreCorrecto(nuevo_nombre))
		{
			nombre_correcto = true;
		} else {
			mostrarErrorNombre();
		}
	if (pwdCorrecto (nuevo_pwd))
	{
		pwd_correcto = true;
	} else {
		mostrarErrorPwd();
	}
	 
	if (nombre_correcto&&ppwd_correcto)
		{
		activarBotonEnvio();
		} else {
			desactivarBotonEnvio();
		}
	*/
}

