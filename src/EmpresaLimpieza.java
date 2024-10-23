
// Clase EmpresaLimpieza que representa una empresa de limpieza de edificios
public class EmpresaLimpieza {
	// Atributos de la empresa
	private String nombreEmpresa;// Nombre de la empresa de limpieza
	private String direccion;// Dirección de la empresa de limpieza
	private int cobroLimpiezaEscalera;// Precio que cobra la empresa por limpiar la escalera de un edificio
	
	// Constructor
	public EmpresaLimpieza(String nombre,String direccion,int cobroLimp) {
		this.nombreEmpresa=nombre;
		this.direccion=direccion;
		this.cobroLimpiezaEscalera=cobroLimp;
	}
	// Métodos getter para obtener el nombre, la dirección y el precio de limpieza de escalera de la empresa
	public String getNombre() {
		return nombreEmpresa;
	}
	public String getDireccion() {
		return direccion;
	}
	public int CobroLimpiezaEscalera() {
		return cobroLimpiezaEscalera;
	}
	// Métodos setter para actualizar el nombre, la dirección y el precio de limpieza de escalera de la empresa
	public void setNombreEmpresa(String nuevo) {
		this.nombreEmpresa=nuevo;
	}
	public void setDireccion(String nueva) {
		this.direccion=nueva;
	}
	public void setCobroLimpiezaEscalera(int nueva) {
		this.cobroLimpiezaEscalera=nueva;
	}
	// Método toString que devuelve una cadena con la información de la empresa
	public String toString() {
		return "EmpresaLimpieza"+"\n-----------"+"\nNombre:"+this.nombreEmpresa+"\nDireccion:"+this.direccion+"\nPrecio por esclera:"+this.cobroLimpiezaEscalera;
	}
}
