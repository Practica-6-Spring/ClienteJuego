package clienteREST;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Videojuego {

	public int id;
	public Company company;
	public String titulo;
	public double precio;
	public ArrayList<Plataforma> plataformas;
	public ArrayList<Genero> generos;

	public Videojuego(int id, Company company, String titulo, double precio, ArrayList<Plataforma> plataformas,
			ArrayList<Genero> generos) {
		super();
		this.id = id;
		this.company = company;
		this.titulo = titulo;
		this.precio = precio;
		this.plataformas = plataformas;
		this.generos = generos;
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

	public ArrayList<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(ArrayList<Genero> generos) {
		this.generos = generos;
	}

}
