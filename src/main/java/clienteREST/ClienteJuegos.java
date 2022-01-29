package clienteREST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
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
	public final static String Url2 = "http://localhost:8080/companies"; // URL
	public final static Gson gson = new Gson(); // Objeto Gson par transformar un objeto java a JSON y un objeto JSON a
	public static HttpGet get = new HttpGet(Url); // peticion
	public static HttpGet get2 = new HttpGet(Url2); // peticion
	public static HttpPost httpPost = new HttpPost("http://localhost:8080/juegos"); // peticion para añadir juego
	public static CloseableHttpResponse respuesta;
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
				AddJuego(cliente, cuerpo);
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

	private static void ObtenerCompanyLoc(CloseableHttpClient cliente, String cuerpo) throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		//PREGUNTAR SI PETA PORQUE LA URL NO ESTÁ IGUAL QUE EN COMPANY CONTROLLER
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

	private static void AddCompany(CloseableHttpClient cliente, String cuerpo) {
		// TODO Auto-generated method stub

	}

	private static void ObtenerCompanyPorId(CloseableHttpClient cliente, String cuerpo)
			throws IOException, ParseException {
		// TODO Auto-generated method stub
		respuesta = cliente.execute(get2);
		entidad = respuesta.getEntity();
		cuerpo = EntityUtils.toString(entidad);
		System.out.println("Introduce el id de la compañía que deseas buscar");

		int idCompany = sc.nextInt();

		boolean Idcomprobado = false;

		Company[] listaCompanies = gson.fromJson(cuerpo, Company[].class);

		for (int i = 0; i < listaCompanies.length; i++) {

			if (listaCompanies[i].getId() == idCompany) {

				System.out.println("id: " + listaCompanies[i].getId() + " Nombre: " + listaCompanies[i].getNombre()
						+ " Localización: " + listaCompanies[i].getLocalizacion());

				System.out.println();

				Idcomprobado = true;
			}
		}

		if (Idcomprobado == false) {
			System.out.println("La compañía que buscas no existe");
		}

		Menu(opcion, seguir);

	}

	private static void ObtenerTodasCompanies(CloseableHttpClient cliente, String cuerpo)
			throws IOException, ParseException {

		respuesta = cliente.execute(get2);
		// guardamos la respuesta en un objeto entidad, de donde podremos sacar el
		// String
		entidad = respuesta.getEntity();
		cuerpo = EntityUtils.toString(entidad);

		// imprime get masivo
		System.out.println(cuerpo);

		Menu(opcion, seguir);

	}

	private static void BorrarJuego(CloseableHttpClient cliente2, String cuerpo2) {
		// TODO Auto-generated method stub

	}

	private static void ActualizarJuego(CloseableHttpClient cliente2, String cuerpo2) {
		// TODO Auto-generated method stub

	}

	public static void AddJuego(CloseableHttpClient cliente, String cuerpo) throws IOException, ParseException {
		// Creamos el contenido del cuerpo, utilizando JSONObject

		respuesta = cliente.execute(get);
		entidad = respuesta.getEntity();
		cuerpo = EntityUtils.toString(entidad);
		Videojuego[] listaJuegos = gson.fromJson(cuerpo, Videojuego[].class);
		JSONObject objeto = new JSONObject();
		int idNuevoJuego;
		String tituloNuevoJuego;
		double precio;
		// FALTA GÉNERO Y PLATAFORMA
		boolean idRepetido = false;

		do {

			System.out.println("Introduce el id del nuevo Juego");
			idNuevoJuego = sc.nextInt();

			for (int i = 0; i < listaJuegos.length; i++) {

				if (listaJuegos[i].getId() == idNuevoJuego) {
					System.out.println("El id que has introducido ya existe, introduce otro");
					idRepetido = true;
				} else
					idRepetido = false;
			}

		} while (idRepetido == true);

		System.out.println("Introduce el título del nuevo Juego");
		tituloNuevoJuego = sc.next();

		System.out.println("Introduce el precio del nuevo Juego");
		precio = sc.nextInt();

		// Añadimos todos los atributos de nuestro objeto
		objeto.accumulate("idJuego", idNuevoJuego);
		objeto.accumulate("título", tituloNuevoJuego);
		objeto.accumulate("precio", precio);

		// Creamos nuestra entidad, la cual estará formada por el contenido
		// de nuestro JSONObject en String.

		StringEntity entidad = new StringEntity(objeto.toString(), StandardCharsets.UTF_8);
		httpPost.setEntity(entidad);

		// Asignamos headers a nuestra petición??????
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json; charset=UTF-8");

		CloseableHttpResponse response = cliente.execute(httpPost);
		System.out.println(response.getCode() + " mensaje: " + response.getReasonPhrase());
		cliente.close();

		Menu(opcion, seguir);
	}

	public static void ObtenerTodosJuegos(String cuerpo, HttpEntity entidad) throws IOException, ParseException {
		respuesta = cliente.execute(get);
		// guardamos la respuesta en un objeto entidad, de donde podremos sacar el
		// String
		entidad = respuesta.getEntity();
		cuerpo = EntityUtils.toString(entidad);

		// imprime get masivo
		System.out.println(cuerpo);

		Menu(opcion, seguir);
	}

	public static void ObtenerJuegoPorCompany(String cuerpo, HttpEntity entidad) throws ParseException, IOException {

		respuesta = cliente.execute(get);
		entidad = respuesta.getEntity();
		cuerpo = EntityUtils.toString(entidad);
		boolean companyComprobada = false;
		Videojuego[] listaJuegos = gson.fromJson(cuerpo, Videojuego[].class);

		ArrayList<Genero> listaGenero = new ArrayList<Genero>();
		ArrayList<Plataforma> listaPlataforma = new ArrayList<Plataforma>();

		String genero = "";
		String plataforma = "";
		System.out.println("Introduce la compañía de la que quieras buscar sus juegos");
		String company = sc.next();

		for (int i = 0; i < listaJuegos.length; i++) {
			listaGenero = listaJuegos[i].getGeneros();
			listaPlataforma = listaJuegos[i].getPlataformas();

			// PREGUNTAR POR QUE NO PETA CUANDO PONEMOS GENERO Y PLATAFORMA

			for (int j = 0; j < listaGenero.size(); j++) {

				if (listaJuegos[i].getGeneros().get(j).getIdGenero() == listaGenero.get(j).getIdGenero()) {

					genero = listaGenero.get(j).getTipo();

				}

			}

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
								+ " Género: " + genero + " Plataforma: " + plataforma);
			}

		}
		if (companyComprobada == false) {
			System.out.println("La plataforma que buscas no existe o no tiene videojuegos");
		}

		Menu(opcion, seguir);

	}

	public static void ObtenerJuegoPorId(String cuerpo, HttpEntity entidad) throws IOException, ParseException {

		respuesta = cliente.execute(get);
		entidad = respuesta.getEntity();
		cuerpo = EntityUtils.toString(entidad);
		System.out.println("Introduce el id del juego que deseas buscar");

		int idJuego = sc.nextInt();

		boolean Idcomprobado = false;

		Videojuego[] listaJuegos = gson.fromJson(cuerpo, Videojuego[].class);

		for (int i = 0; i < listaJuegos.length; i++) {

			if (listaJuegos[i].getId() == idJuego) {

				System.out.println(
						"id: " + listaJuegos[i].getId() + " Título: " + listaJuegos[i].getTitulo() + " Precio: "
								+ listaJuegos[i].getPrecio() + " Compañía: " + listaJuegos[i].getCompany().getNombre()
								+ " Género: " + listaJuegos[i].getGeneros().get(i).getTipo() + "Plataforma: "
								+ listaJuegos[i].getPlataformas().get(i).getNombre());

				System.out.println();

				Idcomprobado = true;
			}
		}

		if (Idcomprobado == false) {
			System.out.println("El juego que buscas no existe");
		}

		Menu(opcion, seguir);

	}

}
