
public class Inquilino extends Vecino{
	// Atributos del inquilino
	private double cantidadPago;// Cantidad que paga el inquilino mensualmente
	// Constructor
	public Inquilino(char tipo,String nombre,String nif,String numPiso,double cantidad) {
		super(tipo,nombre,nif,numPiso);
		this.cantidadPago=cantidad;
	}
	// Método getter para obtener la cantidad a pagar del inquilino
	public double getCantidadPago() {
		return cantidadPago;
	}// Método que calcula el impuesto anual que debe pagar el inquilino según su cantidad a pagar mensual //Llevar a una interfaz.
	public double calcularImpuestoAnual() {
		double impuestoAnual;
		if(this.cantidadPago>500) {
			impuestoAnual=IMPUESTO_ANUAL_INQUILINOS+(CARGO_ALTO_PROPIETARIO*IMPUESTO_ANUAL_INQUILINOS);
		}else {
			impuestoAnual=IMPUESTO_ANUAL_INQUILINOS+(CARGO_BAJO_INQUILINO*IMPUESTO_ANUAL_INQUILINOS);
		}
		return impuestoAnual;
	}
	// Método toString que devuelve una cadena con la información del inquilino
	public String toString() {
		return super.toString()+"\nCantidad a pagar:"+this.cantidadPago;
	}
	public String toFile() {
		return this.tipo+" "+this.nombreVecino+" "+this.nif+" "+this.numeroPiso+" "+this.cantidadPago;
	}

	}
