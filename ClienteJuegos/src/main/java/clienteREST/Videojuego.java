package clienteREST;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Videojuego {

	public int id;
	public Company company;
	public Genero genero;
	public String titulo;
	public double precio;
	public ArrayList<Plataforma> plataformas;
	
	public Videojuego(int id, Company company, Genero genero, String titulo, double precio,
			ArrayList<Plataforma> plataformas) {
		super();
		this.id = id;
		this.company = company;
		this.genero = genero;
		this.titulo = titulo;
		this.precio = precio;
		this.plataformas = plataformas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public ArrayList<Plataforma> getPlataformas() {
		return plataformas;
	}

	public void setPlataformas(ArrayList<Plataforma> plataformas) {
		this.plataformas = plataformas;
	}



	@Override
	public String toString() {
		return "Videojuego: Id: " + id + ", Company: " + company.toString() + ", Género: " + genero.toString() + ", Título: " + titulo
				+ ", Precio: " + precio + ", Plataformas: " + plataformas.toString();
	}
	
	
}

	