package loginjee.controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import loginjee.bean.Usuario;
import loginjee.servicio.UsuarioService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private final static Logger log = Logger.getLogger("mylog");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private Usuario obtenerUsuario (HttpServletRequest request)
	{
		Usuario usuario = null;
		
		try {
			BufferedReader br = request.getReader();//accedo al cuerpo del mensaje HTTP 
			String string_cuerpo = br.readLine();//leo el cuerpo
			System.out.println("CUERPO RX = " + string_cuerpo);
			Gson gson = new Gson();
			usuario = gson.fromJson(string_cuerpo, Usuario.class);
			System.out.println(usuario);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		
		return usuario;
	}
	
	private void mostrarMapaUsuarios ()
	{
		ServletContext servletContext = this.getServletContext();
		Map<Integer, Usuario> mu = (Map<Integer, Usuario>) servletContext.getAttribute("er_mapa");
		Iterator<Integer> it = mu.keySet().iterator();
		Usuario usuario_aux = null;
		while (it.hasNext())
		{
			usuario_aux = mu.get(it.next());
			log.debug(usuario_aux.toString());
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("LLEGÓ PETICIÓN POST");
		int status = 0;

		mostrarMapaUsuarios ();
		
		Usuario usuario = obtenerUsuario(request);//si es !=null
		UsuarioService usuarioService = new UsuarioService();
		try {
			int num_dev = usuarioService.existeUsuario(usuario);
			switch (num_dev) {
			case 0: status = HttpURLConnection.HTTP_OK;//existe
			//TODO incrementar el numero de logins
			//acceder al contexto, OBTENER EL CONTADOR, sumarle uno
			/*ServletContext sc = this.getServletContext();//cojo la saca
			int nlogins = (int)sc.getAttribute("NUM_LOGINS");
			log.debug("Num logins = " + nlogins);
			nlogins = nlogins + 1;
			sc.setAttribute("NUM_LOGINS", nlogins);*/
			
				break;
			case 1: status = HttpURLConnection.HTTP_FORBIDDEN;//existe nombre pero pwd mal
				break;
			case 2: status = HttpURLConnection.HTTP_NO_CONTENT;//no existe nombre
				break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpURLConnection.HTTP_INTERNAL_ERROR;
		}
		log.debug("DEVOLVEMOS STATUS " + status);
		response.setStatus(status);
	}

}
