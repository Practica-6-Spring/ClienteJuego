package clienteREST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;

import com.google.gson.Gson;

public class ClienteJuegos {

//primero se crea el cliente, después hacemos la petición, que devolverá una respuesta, y la almacenaremos en una entidad para después 
// obtener el cuerpo e imprimirlo por pantlla

	public final static CloseableHttpClient cliente = HttpClients.createDefault(); // Cliente
	public final static String Url = "http://localhost:8080/juegos"; // URL
	public final static String Url2 = "http://localhost:8080/company"; // URL
	public final static String Url3 = "http://localhost:8080/companies"; // URL
	public final static Gson gson = new Gson(); // Objeto Gson par transformar un objeto java a JSON y un objeto JSON a
	public static HttpGet get = new HttpGet(Url); // peticion
	public static HttpGet get2 = new HttpGet(Url2); // peticion
	public static HttpPost httpPost = new HttpPost(Url);
	public static HttpPost httpPost2 = new HttpPost(Url2);// peticion para añadir juego
	public static CloseableHttpResponse respuesta;
	public static HttpDelete httpDelete = new HttpDelete(Url);
	public static Scanner sc = new Scanner(System.in);// objeto java
	public static String cuerpo;
	public static HttpEntity entidad;
	public static int opcion = 0;
	public static boolean seguir = true;

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		try {
			Menu(opcion, seguir);
			EntityUtils.consume(entidad);
			respuesta.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void Menu(int opcion, boolean seguir) throws ParseException, IOException {
		// TODO Auto-generated method stub
		do {

			System.out.println("1. Obtener todos los juegos");
			System.out.println("2. Obtener juegos por compañía");
			System.out.println("3. Obtener juegos por id");
			System.out.println("4. Añadir un juego");
			System.out.println("5. Actualizar juego");
			System.out.println("6. Borrar juego ");
			System.out.println("7. Obtener todas las compañías");
			System.out.println("8. Obtener compañía por id");
			System.out.println("9. Obtener compañía por localización");
			System.out.println("10. Añadir compañía");
			System.out.println("11. Actualizar compañía");
			System.out.println("12. Borrar compañía");

			switch (opcion = sc.nextInt()) {
			case 1:
				seguir = false;
				ObtenerTodosJuegos(cuerpo, entidad);

				break;
			case 2:
				seguir = false;
				ObtenerJuegoPorCompany(cuerpo, entidad);
				break;
			case 3:
				seguir = false;
				ObtenerJuegoPorId(cuerpo, entidad);
				break;
			case 4:
				seguir = false;
				// AddJuego(cliente, cuerpo);
				break;

			case 5:
				seguir = false;
				ActualizarJuego(cliente, cuerpo);
				break;

			case 6:
				seguir = false;
				BorrarJuego(cliente, cuerpo);
				break;

			case 7:
				seguir = false;
				ObtenerTodasCompanies(cliente, cuerpo);
				break;

			case 8:
				seguir = false;
				ObtenerCompanyPorId(cliente, cuerpo);
				break;

			case 9:
				seguir = false;
				AddCompany(cliente, cuerpo);
				break;

			case 10:
				seguir = false;
				ActualizarCompany(cliente, cuerpo);
				break;

			case 11:
				seguir = false;
				BorrarCompany(cliente, cuerpo);
				break;

			case 12:
				seguir = false;
				ObtenerCompanyLoc(cliente, cuerpo);
				break;

			default:
				System.out.println("Opcion incorrecta");
				seguir = true;
			}

		} while (seguir);

	}

	private static void ObtenerCompanyLoc(CloseableHttpClient cliente, String cuerpo)
			throws IOException, ParseException {
		// TODO Auto-generated method stub

		// PREGUNTAR SI PETA PORQUE LA URL NO ESTÁ IGUAL QUE EN COMPANY CONTROLLER
		respuesta = cliente.execute(get2);
		entidad = respuesta.getEntity();
		cuerpo = EntityUtils.toString(entidad);
		boolean companyComprobada = false;
		Company[] listaCompanies = gson.fromJson(cuerpo, Company[].class);
		System.out.println("Introduce la localización de la que quieras buscar sus compañías");
		String localizacion = sc.next();

		for (int i = 0; i < listaCompanies.length; i++) {

			if (listaCompanies[i].getLocalizacion().equalsIgnoreCase(localizacion)) {

				System.out.println("id: " + listaCompanies[i].getId() + " Nombre: " + listaCompanies[i].getNombre()
						+ " Localización: " + listaCompanies[i].getLocalizacion());

				System.out.println();

				companyComprobada = true;
			}
		}

		if (companyComprobada == false) {
			System.out.println("La localización que buscas no existe o no tiene compañías");
		}

		Menu(opcion, seguir);
	}

	private static void BorrarCompany(CloseableHttpClient cliente, String cuerpo) {
		// TODO Auto-generated method stub

	}

	private static void ActualizarCompany(CloseableHttpClient cliente, String cuerpo) {
		// TODO Auto-generated method stub

	}

	private static void AddCompany(CloseableHttpClient cliente, String cuerpo) throws IOException, ParseException {
		// TODO Auto-generated method stub
		  respuesta = cliente.execute(httpPost2);
		  entidad = respuesta.getEntity(); 
		  cuerpo = EntityUtils.toString(entidad); 
		  
		  int idCompany;
		  String nombreCompany;
		  String locCompany;
		  ArrayList<Videojuego> listaJuegoNewCompany = new ArrayList<Videojuego>();
		  
		  System.out.println("Introduce el id de la nueva Compañía"); 
		  idCompany = sc.nextInt();
			 
		  System.out.println("Introduce el precio del nuevo Compañía");
		  nombreCompany =sc.nextLine();
			  
		  System.out.println("Introduce el título del nuevo Compañía");
		  locCompany = sc.next();
		  
		  Company nuevaCompa = new Company(idCompany, nombreCompany, locCompany, listaJuegoNewCompany);
		  String nuevaCompany = gson.toJson(nuevaCompa);
		  StringEntity entidad = new StringEntity(nuevaCompa.toString(),
				  StandardCharsets.UTF_8);
		  httpPost2.setEntity(entidad);
				  
				  // Asignamos headers a nuestra petición??????
				  httpPost2.setHeader("Accept","application/json"); httpPost2.setHeader("Content-type",
				  "application/json; charset=UTF-8");
				  
				  CloseableHttpResponse response = cliente.execute(httpPost2);
				  System.out.println(response.getCode() + " mensaje: " +
				  response.getReasonPhrase());
				  System.out.println(nuevaCompa.toString());
	}

	private static void ObtenerCompanyPorId(CloseableHttpClient cliente, String cuerpo)
			throws IOException, ParseException {
		// FUCNIONA PERFECTO
		System.out.println("Introduzca el id de la compañía que desea buscar: ");
		int idCompa = sc.nextInt();
		HttpGet getIdCom = new HttpGet(Url2 + "/" + idCompa);
		respuesta = cliente.execute(getIdCom);
		entidad = respuesta.getEntity();
		cuerpo = EntityUtils.toString(entidad);

		Company Companies = gson.fromJson(cuerpo, Company.class);

		System.out.println("id: " + Companies.getId() + " Nombre: " + Companies.getNombre() + " Localización: "
				+ Companies.getLocalizacion());

		System.out.println();

		Menu(opcion, seguir);

	}

	private static void ObtenerTodasCompanies(CloseableHttpClient cliente, String cuerpo)
			throws IOException, ParseException {

		// FUNCIONA A LA PERFECCIÓN
		HttpGet get3 = new HttpGet(Url3);
		respuesta = cliente.execute(get3);
		// guardamos la respuesta en un objeto entidad, de donde podremos sacar el
		// String
		entidad = respuesta.getEntity();
		cuerpo = EntityUtils.toString(entidad);
		Company[] listaCompanies = gson.fromJson(cuerpo, Company[].class);
		// imprime get masivo
		for (Company company : listaCompanies) {
            System.out.println(company.toString());
            System.out.println();
        }

		Menu(opcion, seguir);

	}

	private static void BorrarJuego(CloseableHttpClient cliente, String cuerpo) throws ParseException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Introduce el id del juego que deseas borrar");
		int idJuegoDelete = sc.nextInt();
		HttpDelete deleteId = new HttpDelete(Url + "/" + idJuegoDelete);
		respuesta = cliente.execute(deleteId);
		entidad = respuesta.getEntity();
		cuerpo = EntityUtils.toString(entidad);	
	

		Videojuego Juego = gson.fromJson(cuerpo, Videojuego.class);
		
	
		Menu(opcion, seguir);


	}

	private static void ActualizarJuego(CloseableHttpClient cliente, String cuerpo) {
		// TODO Auto-generated method stub

	}

	/*public static void AddJuego(CloseableHttpClient cliente, String cuerpo)
	  throws IOException, ParseException {
	 
	 //Primero declaramos todas las varables que vamos a meter en nuestro objeto
	 // Videojuego, después creamos un //objeto videojuego con los datos introducidos
	  //y pasamos ese objeto a JSON con el toJson. Después ejecutamos las peticiones.
	  //para sacar los datos de las companies dependiendo del id que meta el
	  //usuario, utilizamos el metodo getPorId, //para plataforma y género se puede
	  //hacer a mano o creando otro controller y un método getporId para cada uno
	  
	  respuesta = cliente.execute(get);
	  entidad = respuesta.getEntity(); 
	  cuerpo = EntityUtils.toString(entidad); //Añadir compañia, plataforma y genro
	  //creamos objeto juego y al final lo pasamos a json
	 
	 
	 
	  String tituloNuevoJuego;
	  double precio; // FALTA GÉNERO Y PLATAFORMA
	  int idGenero; 
	  int idPlataforma; 
	  int idCompany; 
	  String tipoGenero; 
	  String nombreCompany;
	  String locCompany;
	  
	 System.out.println("Introduce el título del nuevo Juego"); 
	 tituloNuevoJuego = sc.next();
	 
	  System.out.println("Introduce el precio del nuevo Juego");
	  precio =sc.nextInt();
	  
	  System.out.println("Introduce el título del nuevo Juego");
	  tituloNuevoJuego = sc.next();
	  
	 System.out.println("Introduce el precio del nuevo Juego");
	 precio =sc.nextInt();
	  
	  System.out.println("Introduce el título del nuevo Juego");
	  tituloNuevoJuego = sc.next();
	  
	  
	  
	  // Añadimos todos los atributos de nuestro objeto Videojuego nuevoJuego = new
	  Videojuego(new Company(idCompany, locCompany, Compa ), new Genero(idGenero,tipoGenero), tituloNuevoJuego, precio, idPlataforma );
	  Videojuego Juego = gson.toJson(cuerpo, Videojuego.class); // Creamos nuestra entidad, la cual estará formada por el contenido 
	  // de nuestro JSONObject en String.
	  
	  StringEntity entidad = new StringEntity(nuevoJuego.toString(),
	  StandardCharsets.UTF_8); httpPost.setEntity(entidad);
	  
	  // Asignamos headers a nuestra petición??????
	  httpPost.setHeader("Accept","application/json"); httpPost.setHeader("Content-type",
	  "application/json; charset=UTF-8");
	  
	  CloseableHttpResponse response = cliente.execute(httpPost);
	  System.out.println(response.getCode() + " mensaje: " +
	  response.getReasonPhrase()); System.out.println(nuevoJuego);
	  
	  
	  cliente.close();
	  
	  Menu(opcion, seguir); 
	  }
*/
	public static void ObtenerTodosJuegos(String cuerpo, HttpEntity entidad) throws IOException, ParseException {

		// FUNCIONA A LA PERFECCIÓN
		respuesta = cliente.execute(get);
		// guardamos la respuesta en un objeto entidad, de donde podremos sacar el
		// String
		entidad = respuesta.getEntity();
		cuerpo = EntityUtils.toString(entidad);
		Videojuego[] listaJuegos = gson.fromJson(cuerpo, Videojuego[].class);
		// imprime get masivo
		for (Videojuego videojuego : listaJuegos) {
            System.out.println(videojuego.toString());
            System.out.println();
        }

		Menu(opcion, seguir);
	}

	public static void ObtenerJuegoPorCompany(String cuerpo, HttpEntity entidad) throws ParseException, IOException {

		respuesta = cliente.execute(get);
		entidad = respuesta.getEntity();
		cuerpo = EntityUtils.toString(entidad); // retocar toString
		boolean companyComprobada = false;
		Videojuego[] listaJuegos = gson.fromJson(cuerpo, Videojuego[].class);

		ArrayList<Plataforma> listaPlataforma = new ArrayList<Plataforma>();

		String plataforma = "";
		System.out.println("Introduce la compañía de la que quieras buscar sus juegos");
		String company = sc.next();

		for (int i = 0; i < listaJuegos.length; i++) {
			listaPlataforma = listaJuegos[i].getPlataformas();

			for (int j = 0; j < listaPlataforma.size(); j++) {

				if (listaJuegos[i].getPlataformas().get(j).getIdPlataforma() == listaPlataforma.get(j)
						.getIdPlataforma()) {

					plataforma = listaPlataforma.get(j).getNombre();

				}

			}
			companyComprobada = true;
			if (listaJuegos[i].getCompany().getNombre().equalsIgnoreCase(company)) {
				System.out.println(
						"id: " + listaJuegos[i].getId() + " Título: " + listaJuegos[i].getTitulo() + " Precio: "
								+ listaJuegos[i].getPrecio() + " Compañía: " + listaJuegos[i].getCompany().getNombre()
								+ " Género: " + listaJuegos[i].getGenero().getTipo() + " Plataforma: " + plataforma);
			}

		}
		if (companyComprobada == false) {
			System.out.println("La plataforma que buscas no existe o no tiene videojuegos");
		}

		Menu(opcion, seguir);

	}

	public static void ObtenerJuegoPorId(String cuerpo, HttpEntity entidad) throws IOException, ParseException {

		System.out.println("Introduce el id del juego que deseas buscar");

		int idJuego = sc.nextInt();
		HttpGet getId = new HttpGet(Url + "/" + idJuego);
		respuesta = cliente.execute(getId);
		entidad = respuesta.getEntity();
		cuerpo = EntityUtils.toString(entidad);
	

		Videojuego Juego = gson.fromJson(cuerpo, Videojuego.class);

		System.out.println("id: " + Juego.getId() + " Título: " + Juego.getTitulo() + " Precio: " + Juego.getPrecio()
				+ " Compañía: " + Juego.getCompany().getNombre() + " Género: " + Juego.getGenero().getTipo()
				+ "Plataforma: " + Juego.getPlataformas());

		System.out.println();

		Menu(opcion, seguir);

	}

}
