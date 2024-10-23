public abstract class Vecino implements ImpuestoAnual {
    //Atributos
	protected char tipo;// tipo de vecino, puede ser 'P' (propietario) o 'I' (inquilino)
	protected String nombreVecino;// nombre del vecino
	protected String nif;// número de identificación fiscal del vecino
	protected String numeroPiso;// número de piso del vecino
	protected Peticion[] peticiones=new Peticion[10]; // array de peticiones del vecino (máximo 10)
	protected int numPeticionesActivas=0;// número de peticiones activas del vecino

	// constructor
	public Vecino(char tipo,String nombre,String nif,String numPiso) {
		this.tipo=tipo;
		this.nombreVecino=nombre;
		this.nif=nif;
		this.numeroPiso=numPiso;
	}
	// Método para agregar una petición
	public boolean peticion(String descripcion, char tipo) throws MaximoPeticionesException {
	    if (numPeticionesActivas < 10) {
	        this.peticiones[numPeticionesActivas] = new Peticion(descripcion, tipo);
	        numPeticionesActivas++;
	        return true;
	    } else {
	        throw new MaximoPeticionesException("No se pueden añadir más peticiones, ya hay 10 activas");
	    }
	}
	// Método para mostrar las peticiones urgentes
	 public String mostrarPeticionesUrgentes() {
	        String devolver = "No hay peticiones urgentes de este vecino";
	        for (int i = 0; i < numPeticionesActivas; i++) {
	            if (peticiones[i].getTipo() == '1') {
	                devolver = "\n" + peticiones[i].toString();
	            }
	        }
	        return devolver;
	    }

	// Método para mostrar las peticiones activas
	public String mostrarPeticionesActivas() {
		String devolver="";
		if(numPeticionesActivas==0)
			devolver="No hay peticiones activas";
		else {
			for(int i=0;i<numPeticionesActivas;i++) {
				devolver+="\n"+(i+1)+""+peticiones[i].toString();
			}
		}
		return devolver;
	}
	public abstract double calcularImpuestoAnual();
	// Métodos getters y setters
	public Peticion[] getPeticiones() {
		return peticiones;
	}
	public int getPeticionesActivas() {
		return numPeticionesActivas;
	}
	public char getTipo() {
		return tipo;
	}
	
	public String getNombreVecino() {
		return nombreVecino;
	}
	public String getNif() {
		return nif;
	}
	public String getNumeroPiso() {
		return numeroPiso;
	}
	public void setPeticionesActivas(int numPeticionesActivas) {
		this.numPeticionesActivas=numPeticionesActivas;
	}
	public void setTipo(char tipo) {
		this.tipo=tipo;
	}
	public void setNombreVecino(String nombre) {
		this.nombreVecino=nombre;
	}
	public void setNif(String nif) {
		this.nif=nif;
	}
	public void setNumeroPiso(String numPiso) {
		this.numeroPiso=numPiso;
	}
	
	// Método toString que devuelve una cadena con la información del vecino
	public String toString() {
		return "\nVecino"+"\n-----------"+"\nTipo:"+this.tipo+"\nNombre:"+this.nombreVecino+"\nNIF:"+this.nif+"\nNumero de piso:"+this.numeroPiso+"\nNumero de peticiones:"+this.numPeticionesActivas+"\n";
	}
	public String toFile() {
		return this.tipo+" "+this.nombreVecino+" "+this.nif+" "+this.numeroPiso;
	}
}