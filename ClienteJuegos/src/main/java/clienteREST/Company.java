package clienteREST;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Company {

	    public int id;
	    public String nombre;
	    public String localizacion;
	    public ArrayList<Videojuego> videojuegos;
		public Company(int id, String nombre, String localizacion, ArrayList<Videojuego> videojuegos) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.localizacion = localizacion;
			this.videojuegos = videojuegos;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getLocalizacion() {
			return localizacion;
		}
		public void setLocalizacion(String localizacion) {
			this.localizacion = localizacion;
		}
		public ArrayList<Videojuego> getVideojuegos() {
			return videojuegos;
		}
		public void setVideojuegos(ArrayList<Videojuego> videojuegos) {
			this.videojuegos = videojuegos;
		}
		@Override
		public String toString() {
			return "Company: Id: " + id + ", Nombre: " + nombre + ", Localizacion: " + localizacion ;
		}
	
	
}