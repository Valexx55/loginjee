package loginjee.servicio;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SeguimientoUsuario {
	
	
	 public static void registrarActividad (HttpServletRequest request)
	    {
	    	HttpSession sesion =  request.getSession(false);//MIRA SI LA PETICIÓN QUE VIENE TRAE SESIÓN y SI NO LA TRAE, NO LE CREES OTRA
			List<String> lista_actividad = (List<String>)sesion.getAttribute("LISTA_ACTIVIDAD");
			String actividad_nueva = request.getServletPath();
			lista_actividad.add(actividad_nueva);
			sesion.setAttribute("LISTA_ACTIVIDAD", lista_actividad);
	    }
	 
	 public static void mostrarActividad (HttpSession session)
	    {
			List<String> lista_actividad = (List<String>)session.getAttribute("LISTA_ACTIVIDAD");
			for (String actividad : lista_actividad)
			{
				System.out.println(actividad);
			}
	    }

}
