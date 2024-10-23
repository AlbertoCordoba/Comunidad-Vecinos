
public class Comunidad{
	// Atributos
	Vecino[] vecino=new Vecino[20];// Array de vecinos
	private int numVecinos;// Número de vecinos registrados en la comunidad
	private int numEscaleras; // Número de escaleras en la comunidad

	// Constructor
	public Comunidad(Vecino[] vecino,int numVecinos,int numEscaleras) {
		this.numVecinos=numVecinos;
		this.numEscaleras=numEscaleras;
	}
	// Métodos

	// Método que devuelve la información de los vecinos registrados en la comunidad
	public String mostrarInformacionVecinos() {
		String cadena="";
		cadena="***Vecinos***";
		for(int i=0;i<numVecinos;i++) {
			cadena=cadena+"\n"+this.vecino[i].toString();
		}
		return cadena;
	}
	// Método que devuelve el número de vecinos registrados en la comunidad
	public int getNumVecinos() {
		return numVecinos;
	}
	// Método que devuelve el número de escaleras en la comunidad
	public int getNumEscaleras() {
		return numEscaleras;
	}
	// Método que comprueba si un vecino con un NIF dado ya está registrado en la comunidad
	public boolean vecinoYaRegistrado(String nif) {  
		boolean encontrado = false;
    for(int i=0;i<numVecinos;i++) {
        if(vecino[i].getNif().equalsIgnoreCase(nif)) {
            encontrado = true;
        }
    }
    return encontrado;
}	// Método que añade un vecino a la comunidad, siempre y cuando no esté ya registrado 
	public boolean añadirVecino(Vecino registrarVecino) {
	    boolean vecinoAñadido = false;
	    if(!vecinoYaRegistrado(registrarVecino.getNif())) {
	        vecino[numVecinos] = registrarVecino;
	        numVecinos++;
	        vecinoAñadido = true;
	    }
	    return vecinoAñadido;
	}

	// Método que realiza una petición a un vecino con un NIF dado, con una descripción y un tipo de petición dados
	public String realizarPeticion(String nif, String descripcion, char tipo1) throws Exception {
	    Vecino v = null;
	    boolean vecinoEncontrado = false;
	    int i = 0;
	    while (i < numVecinos) {
	        if (vecino[i].getNif().equalsIgnoreCase(nif)) {
	            v = vecino[i];
	            vecinoEncontrado = true;
	        }
	        i++;
	    }
	 // Si no se ha encontrado el vecino, devuelve un mensaje de error
	    String resultado = "Vecino no encontrado";
	    if (vecinoEncontrado) {
	    	try {
	        boolean peticionRealizada = v.peticion(descripcion, tipo1);
	        resultado = peticionRealizada ? "Peticion realizada con exito" : "Maximo de peticiones alcanzado";
	    	}catch(MaximoPeticionesException e) {
	    		resultado="No se pueden añadir mas peticiones,ya hay 10 activas";
	    	}
	    }
	    return resultado;
	}
	// Método que devuelve el nombre del vecino o vecinos con el impuesto anual más alto
	public String mostrarNombreVecinoMasImpuestos() {
	    String vecinoMasImpuestos = null;
	    double impuestosMasAltos = 0;
	    for (int i = 0; i < numVecinos; i++) {
	        double impuestos = vecino[i].calcularImpuestoAnual();
	        if (impuestos > impuestosMasAltos) {
	            impuestosMasAltos = impuestos;
	            vecinoMasImpuestos = vecino[i].getNombreVecino();
	        } else if (impuestos == impuestosMasAltos) {
	            vecinoMasImpuestos += ", " + vecino[i].getNombreVecino();
	        }
	    }
	    return vecinoMasImpuestos;
	}
    //Calcula el coste de limpieza de las escaleras para el edificio utilizando una empresa de limpieza dada.
	public double calcularCosteLimpieza(EmpresaLimpieza empresa) {
		
		return (empresa.CobroLimpiezaEscalera()*numEscaleras)*12;
	}
	// Calcula el impuesto anual total de un vecino dado su NIF.
	public double calcularImpuestoVecino(String nif) {
	    double ImpuestoTotal = 0;
	    for (int i = 0; i < numVecinos; i++) {
	        if (vecino[i].getNif().equalsIgnoreCase(nif)) {
	            	ImpuestoTotal += vecino[i].calcularImpuestoAnual();
	            }
	        }
		return ImpuestoTotal;
	}
	//Muestra las peticiones urgentes de un vecino con su nif.
	public String mostrarPeticionesUrgentesVecino(String nif) {
	    String peticiones = "";
	    for (int i = 0; i < numVecinos; i++) {
	        if (vecino[i].getNif().equalsIgnoreCase(nif)) {
	            peticiones = vecino[i].mostrarPeticionesUrgentes();
	        }
	    }
	    return peticiones;
	}

	// Consulta la suma total de impuestos anuales de todos los vecinos del edificio.
	public double consultarSumaImpuestos() {
	    double ImpuestoTotal=0.0;
	    for(int i=0;i<numVecinos;i++) {
	    	ImpuestoTotal += vecino[i].calcularImpuestoAnual();
        }
        return ImpuestoTotal;
    }
	public double calcularMediaImpuestos(Class<? extends Vecino> tipoVecino) {
        double totalImpuestos = 0.0;
        int numVecinos = 0;

        for (Vecino vecino : vecino) {
            if (tipoVecino.isInstance(vecino)) {
                totalImpuestos += vecino.calcularImpuestoAnual();
                numVecinos++;
            }
        }

        if (numVecinos > 0) {
            return totalImpuestos / numVecinos;
        } else {
            return 0.0;
        }
    }
}
