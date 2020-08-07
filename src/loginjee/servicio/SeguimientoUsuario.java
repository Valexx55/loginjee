package loginjee.servicio;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import loginjee.bean.SesionBean;

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
	 
	 public static void guardarActividad (HttpSession session) {
		 //aquí tendremos que llamar la capa de persistencia
		 
		 SesionBean sesionBean = (SesionBean)session.getAttribute("INFO_SESION");
		 sesionBean.setTfin(new Date());
		 //TODO insertar sesionBean en la tabla sesion de la BD
		 	//necesitarmos el id del usuario FK a partir del nombre
		//TODO GUARDAR la info de la acttividad en la tabla actividad (que está por hacer)
		 //CONCEPTO DE TRANSACCIÓN!
	 }

}
