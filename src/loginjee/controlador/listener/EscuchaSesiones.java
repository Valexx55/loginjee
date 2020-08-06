package loginjee.controlador.listener;

import java.util.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import loginjee.servicio.SeguimientoUsuario;

/**
 * Application Lifecycle Listener implementation class EscuchaSesiones
 *
 */
@WebListener
public class EscuchaSesiones implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public EscuchaSesiones() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent eventosesion)  { 
         // TODO Auto-generated method stub
    	System.out.println("CREADA UNA NUEVA SESIÓN");
    	System.out.println("IDSession = " + eventosesion.getSession().getId() + " " +new Date());
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent eventosesion)  { 
         // TODO Auto-generated method stub
    	System.out.println("DESTRUIDA LA SESIÓN");
    	System.out.println("IDSession = " + eventosesion.getSession().getId() + " " +new Date());
    	SeguimientoUsuario.mostrarActividad(eventosesion.getSession());//TODO
    	//registrar esta actividad en la base de datos
    	
    }
	
}
