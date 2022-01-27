package clienteREST;

public class Genero {
	
	private Integer idGenero;
	private String tipo;
	public Genero(Integer idGenero, String tipo) {
		
		super();
		this.idGenero = idGenero;
		this.tipo = tipo;
	}
	public Integer getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(Integer idGenero) {
		this.idGenero = idGenero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
