
public class Peticion {
	// Atributos de la petición
	private String descripcion;// Descripción de la petición
	private char tipo;// Tipo de urgencia de la petición (0, 1 o 2)
	// Constructor
	public Peticion(String descripcion,char tipo) {
		this.descripcion=descripcion;
		this.tipo=tipo;
	}
	// Método getter para obtener la descripción de la petición
	public String getDescripcion() {
		return descripcion;
	}
	// Método getter para obtener el tipo de urgencia de la petición
	public int getTipo() {
		return tipo;
	}
	// Método setter para actualizar la descripción de la petición
	public void setDescripcion(String descripcion) {
		this.descripcion=descripcion;
	}
	// Método setter para actualizar el tipo de urgencia de la petición
	public void setTipo(char tipo) {
		this.tipo=tipo;
	}
	// Método toString que devuelve una cadena con la información de la petición
	public String toString() {
		return "Peticion:"+"\n"+"Descripcion:"+this.descripcion+"\nTipo de urgencia:"+this.tipo;
	}
}
