
public class Propietario extends Vecino{
	// Atributos
	private int añoCompra;// Año en que el propietario compró el piso

	// Constructor
	public Propietario(char tipo,String nombre,String nif,String numPiso,int año) {
		super(tipo,nombre,nif,numPiso);
		this.añoCompra=año;
	}
	// Método getter para obtener el año de compra del piso
	public int getAñoCompra() {
		return añoCompra;
	}
	// Método para calcular el impuesto anual que debe pagar el propietario //A la interfaz los datos constantes.
	public double calcularImpuestoAnual() {
		double impuestoAnual;
		if(this.añoCompra<2006) {
			impuestoAnual=IMPUESTO_ANUAL_PROPIETARIOS+(CARGO_ALTO_PROPIETARIO*IMPUESTO_ANUAL_PROPIETARIOS);
		}else {
			impuestoAnual=IMPUESTO_ANUAL_PROPIETARIOS+(CARGO_BAJO_PROPIETARIO*IMPUESTO_ANUAL_PROPIETARIOS);
		}
		return impuestoAnual;
	}
	// Método setter para actualizar el año de compra del piso
	public void setAñoCompra(int año) {
		this.añoCompra=año;
	}
	// Método toString que devuelve una cadena con la información del propietario
	public String toString() {
		return super.toString()+"\nAño de compra del piso:"+this.añoCompra;
	}
	public String toFile() {
		return this.tipo+" "+this.nombreVecino+" "+this.nif+" "+this.numeroPiso+this.añoCompra;
	}
}
