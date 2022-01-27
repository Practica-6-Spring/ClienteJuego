package clienteREST;

public class Plataforma {

	private Integer idPlataforma;
	private String nombre;
	
	public Plataforma(Integer idPlataforma, String nombre) {
		super();
		this.idPlataforma = idPlataforma;
		this.nombre = nombre;
	}

	public Integer getIdPlataforma() {
		return idPlataforma;
	}

	public void setIdPlataforma(Integer idPlataforma) {
		this.idPlataforma = idPlataforma;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
