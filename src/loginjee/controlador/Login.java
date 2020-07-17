package loginjee.controlador;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import loginjee.Usuario;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("LLEGÓ PETICIÓN POST");
		
		//1ACCEDER A LA INFORMACIÓN DEL CUERPO
		BufferedReader br = request.getReader();//accedo al cuerpo del mensaje HTTP 
		String string_cuerpo = br.readLine();//leo el cuerpo
		System.out.println("CUERPO RX = " + string_cuerpo);
		//2PASAR DE JSON A USUARIO
		//tengo que usar gson--> CONSEGUIRLA DE MAVEN
		Gson gson = new Gson();
		Usuario usuario = gson.fromJson(string_cuerpo, Usuario.class);
		System.out.println(usuario);
		//3 VER SI EL USUARIO ESTÁ EN LA BASE DE DATOS
		//consulta select a la base de datos --recordad comentar el asterisco
		String instruccion_consulta_usuario = "SELECT * FROM hedima.usuarios WHERE (nombre = ? AND password = ?);";
		//JDBC: CONNECTION, PREPARED STATEMENT, RESULSET
		//EJECUTO LA CONSULTA Y VEO SI TENGO RESULTADOS
		
		
		
		
	}

}
