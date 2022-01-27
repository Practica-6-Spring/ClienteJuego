package clienteREST;

import java.util.HashSet;
import java.util.Set;


public class Company {

	private Integer id;
	private String nombre;
	private String localizacion;
	
	public Company(Integer id, String nombre, String localizacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.localizacion = localizacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

}
