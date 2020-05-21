package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import org.json.JSONObject;
import org.json.JSONArray;
/**
 * Servlet implementation class AsignaturaServlet
 */
public class AsignaturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    boolean mostrarLog = true;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsignaturaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		//Cojo el dni necesario para la petici�n rest�		
		StringBuilder res = null;
		String laputa = "JODEEEEEER";
		PrintWriter writer = response.getWriter();
		
		HttpSession sesion = request.getSession(false);
		String dni = sesion.getAttribute("dni").toString();
		String cookie = sesion.getAttribute("cookie").toString();
		
		
		//JSONArray listaAsig = new JSONArray();
		
		//Creo la llamada HTTP para el REST
		URL url = new URL ("http://dew-algrlo-1920.dsic.cloud:9090/CentroEducativo/profesores/"+dni+"/asignaturas");
		//URL url = new URL ("http://dew-swe-1920.dsic.cloud:9090/CentroEducativo/login");

		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Set-Cookie",cookie);
		con.setDoOutput(false);

		int responseCode = con.getResponseCode();
		response.getWriter().write("El cod res es: "+responseCode);
		
		InputStream inputStream ;
		/*try { 
			//Aqui recoger�a lo que ha devuelto el GET. Habr�a que crear un json para ello
			 if (200 <= responseCode && responseCode <= 299) {
			        inputStream = con.getInputStream();
			    } else {
			        inputStream = con.getErrorStream();
			    }

			    BufferedReader in = new BufferedReader(
			        new InputStreamReader(
			            inputStream));
			    
			    res = new StringBuilder();
			    String currentLine;

			    while ((currentLine = in.readLine()) != null) {
			    	laputa += res.append(currentLine).toString();
			    	
			    }
			    //listaAsig = (String) res;
			    //Aqui habr�a que pasar el string a un json para devolverlo
			    in.close();	
			
		} catch (Exception e) { System.out.print(e.getStackTrace()); }*/
		
		try {
			BufferedReader in = new BufferedReader(
					  new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			int i = 0;
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			    response.getWriter().write("Esto es:" +i);
			    laputa += content;
			}
			in.close();
			con.disconnect();
		}catch(Exception e) {
			System.out.print(e.getStackTrace());
		}
		
		System.out.println("Respuesta del POST:  " + responseCode);
		//writer.print(res);
		response.getWriter().write(laputa);
		response.getWriter().write("PON ALGO EN LA PUTA RESPUESTA JODER");
		response.getWriter().write(res.toString());
		
		/*ServletContext sc = this.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/gato.html"); //TODO cambiar por el html de la lista de asignaturas 
        response.setContentType("text/html");
        rd.include(request, response);*/
			
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}	
	
}
