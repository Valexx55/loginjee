package loginjee.controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.net.ssl.HttpsURLConnection;

import loginjee.Usuario;
import loginjee.persistencia.BaseDeDatos;

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
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int status = 0;
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
		//String instruccion_consulta_usuario = "SELECT * FROM hedima.usuarios WHERE (nombre = ? AND password = ?);";
		String instruccion_consulta_usuario = "SELECT * FROM hedima.usuarios WHERE (nombre = ?);";
		//JDBC: CONNECTION, PREPARED STATEMENT, RESULSET
		//EJECUTO LA CONSULTA Y VEO SI TENGO RESULTADOS
		//si tengo resultados --> el usuario está registrado
		//si no, no
		try {
			 connection = BaseDeDatos.getConnection();
			 ps = connection.prepareStatement(instruccion_consulta_usuario);
			 ps.setString(1, usuario.getNombre());
			// ps.setString(2, usuario.getPwd());
			 rs = ps.executeQuery();
			if (rs.next())
			{
				System.out.println("hay un usuario que coincide por el nombre");
				//a partir del resulset, voy a hacer un objeto usuario
				Usuario usuario2 = new Usuario(rs);
				System.out.println("USUARIO RECUPERADO DE LA BD " + usuario2);
				if (usuario2.getPwd().equals(usuario.getPwd()))
				{
					System.out.println("hay un usuario que coincide por el nombre y por la contraseña");
					status = HttpURLConnection.HTTP_OK;//200
				} else 
				{
					System.out.println("hay un usuario que coincide por el nombre pero NO por la contraseña");
					status = HttpURLConnection.HTTP_FORBIDDEN;//403
				}
				
				//status = HttpsURLConnection.HTTP_OK;//is deprecated DEPRECADO
			} else 
			{
				System.out.println("El usuario NO existe CON ESE NOMBRE");
				status = HttpURLConnection.HTTP_NO_CONTENT;//204
			}
			//response.setStatus(status);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//500 ERROR
			status = HttpURLConnection.HTTP_INTERNAL_ERROR;
			//response.setStatus(status);
		}
		finally {
			response.setStatus(status);
			BaseDeDatos.liberarRecursos(connection, ps, rs);
			
		}
		
		
		
		
		
	}

}
