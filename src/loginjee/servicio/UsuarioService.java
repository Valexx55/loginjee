package loginjee.servicio;

import java.util.List;

import loginjee.Usuario;
import loginjee.persistencia.UsuarioDAO;

public class UsuarioService {
	//TENDEREMOS QUE TENER TODOS LOS MÉTODOS RELATIVOS AL TRABAJO CON USUARIOS
	//ESTA CLASE SERÁ INVOCADA POR EL SERVLET
	//Y ESTA CLASE, INVOCARÁ A LA PERSISTENCIA (EN CASO DE SER NECESARIO)
	
	
	public List<Usuario> listarUsuarios ()
	{
		List<Usuario> lUsuarios = null;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
			lUsuarios = usuarioDAO.obtenerTodos();
		
		return lUsuarios;
	}

}
