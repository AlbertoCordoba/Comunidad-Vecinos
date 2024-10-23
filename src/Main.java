import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {

		private static Scanner leer;
		private static String ficheroVecinos;


	//Metodo que crea una comunidad de vecinos mediante ficheros, pidiendo la ubicación de un archivo Vecinos.txt
		public static Comunidad crearMedianteFicherosPedir() {
			Comunidad comunidad=null;
			leer= new Scanner(System.in);
			System.out.print("Introduce la localizacion del fichero Vecinos.txt: ");
			ficheroVecinos=leer.nextLine();
			File file=new File(ficheroVecinos);
			
			if(file.exists()) {
				comunidad=new Comunidad(null,0,10);
		comunidad=anadirVecinosFicheros(comunidad,ficheroVecinos);
			}else {
				System.out.println("No se ha encontrado el archivo");
			}
			return comunidad;
		}

		//Metodp que agrega vecinos a una comunidad a partir de un archivo de texto, siempre que este exista.
		public static Comunidad anadirVecinosFicheros(Comunidad comunidad, String ruta) { 
		    try {
		        leer = new Scanner(new File(ruta));
		        char tipo;
		        String nombre;
		        String nif;
		        String numPiso;
		        int año;
		        double cantidad;
		        
		        while(leer.hasNext()) {
		            tipo = leer.next().charAt(0);
		            nombre = leer.next();
		            nif = leer.next();
		            numPiso = leer.next();
		            
		            switch(tipo) {
		                case 'p':
		                    año = leer.nextInt(); 
		                    comunidad.añadirVecino(new Propietario(tipo, nombre, nif, numPiso, año));
		                    break;
		                
		                case 'i':
		                    cantidad = leer.nextDouble();
		                    comunidad.añadirVecino(new Inquilino(tipo, nombre, nif, numPiso, cantidad));
		                    break;
		            }
		        }
		        leer.close();
		        System.out.println("Se han añadido los vecinos correctamente.");
		    } catch (FileNotFoundException e) {
		        System.out.println("Error, fichero Vecinos.txt no encontrado");
		    }
		    
		    return comunidad;
		}
		//Metodo que lee un entero entre A y B (incluidos) ingresado por el usuario.
		public static int leerEnteroEntreAB(int A, int B, String s) throws RangoException {
		    int entero = A - 1;

		    do {
		        leer = new Scanner(System.in);
		        System.out.print(s);
		        try {
		            entero = leer.nextInt();
		        } catch (InputMismatchException e) {
		            leer.reset();
		        }

		        if (entero < A || entero > B) {
		            throw new RangoException("Error al introducir el dato, debe estar entre " + A + " y " + B + ".");
		        }
		    } while (entero < A || entero > B);

		    return entero;
		}
		
		static void guardarVecinos(Vecino vecino) {
			FileWriter datos=null;
			try {
				datos= new FileWriter(ficheroVecinos,true);//true para que no sobreescriba
				datos.write("\n"+vecino.toFile().replace('.', ','));//metodo especial para sacar informacion a fichero
				
			}catch(IOException ex) {
				System.out.println("No se ha encontrado el fichero clientes");
			}finally {
				if (datos!=null) {
					try {
						datos.close();
					}catch(IOException ex) {
						System.out.println("Error al cerrar el fichero clientes");
					}
				}
			}
			
		}
		
	//Metodo que pide información sobre un vecino específico de la comunidad.
		public static void pedirInformacionVecino(Comunidad comunidad) {
		    leer = new Scanner(System.in);
		    String nif;
		    System.out.println("Introducir nif:");
		    nif = leer.next();
		    if (comunidad.vecinoYaRegistrado(nif)) {
		        System.out.println(comunidad.mostrarPeticionesUrgentesVecino(nif));
		    } else {
		        System.out.println("Vecino no encontrado");
		    }
		}

		
		//Este método se encarga de solicitar información de un vecino y registrar una petición de urgencia. 
		public static void pedirInformacionVecinoPeticion(Comunidad comunidad) throws Exception {
		    leer = new Scanner(System.in);
		    String nif;
		    String descripcion;
		    int tipo;
		    boolean seguirPidiendo;
		    
		    do {
		    	
		    System.out.println("Introducir nif:");
		    nif = leer.next();
		    leer.nextLine();

		    boolean nifValido = false;
	        while (!nifValido) {
	            try {
	                if (nif.matches("[0-9]{8}[A-Za-z]{1}")) {
	                    nifValido = true;
	                } else {
	                    throw new Exception("El NIF introducido no es válido. Introduce 8 números y 1 letra.");
	                }
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	                nif = leer.next();
	                leer.nextLine();
	            }
	        }
		    
		    if (!comunidad.vecinoYaRegistrado(nif)) {
		        char tipoVecino;
		        String nombre;
		        String numPiso;

		        System.out.println("El vecino no esta registrado. Por favor, introduzca los siguientes datos:");

		        System.out.println("Introducir tipo de vecino (i - inquilino, p - propietario):");
		        tipoVecino = ' ';
		        do {
		            tipoVecino = Character.toLowerCase(leer.next().charAt(0));
		            if (tipoVecino != 'i' && tipoVecino != 'p') {
		                System.out.println("El valor introducido no es válido. Introduzca i o p:");
		            }
		        } while (tipoVecino != 'i' && tipoVecino != 'p');

		        System.out.println("Introducir nombre:");
		        nombre = leer.next();
		        leer.nextLine();

		        System.out.println("Introducir numero de piso:");
		        numPiso = leer.next();
		        leer.nextLine();

		        if (tipoVecino == 'i') {
		            double cantidad=0;

		            do {
		                try {
		                    System.out.println("Introducir cantidad que paga:");
		                    cantidad = leer.nextDouble();
		                    leer.nextLine();
		                } catch (InputMismatchException e) {
		                    System.out.println("El valor introducido no es válido. Introduce un número:");
		                    leer.nextLine(); 
		                    cantidad = 0; 
		                }
		            } while (cantidad == 0);
		          

		            Inquilino vecino = new Inquilino(tipoVecino,nombre,nif,numPiso,cantidad);
		            guardarVecinos(vecino);
		            comunidad.añadirVecino(vecino);
		        } else {
		            int año;

		            do {
		                try {
		                    System.out.println("Introducir el año de compra:");
		                    año = leer.nextInt();
		                    leer.nextLine();
		                } catch (InputMismatchException e) {
		                    System.out.println("El valor introducido no es válido. Introduce un número:");
		                    leer.nextLine(); 
		                    año = 0; 
		                }
		            } while (año == 0);
		           

		            Propietario vecino=new Propietario(tipoVecino,nombre,nif,numPiso,año);
		            guardarVecinos(vecino);
		            comunidad.añadirVecino(vecino);
		           
		        }
		    }

		    System.out.println("Introducir descripcion:");
		    descripcion = leer.nextLine();
		    


		    System.out.println("Introducir tipo de urgencia (0 - no urgente, 1 - urgente, 2 - muy urgente):");
		    char tipo1 = ' ';
		    boolean tipoValido = false;
		    do {
		        String tipoStr = leer.next();
		        if (tipoStr.length() != 1 || (tipoStr.charAt(0) != '0' && tipoStr.charAt(0) != '1' && tipoStr.charAt(0) != '2')) {
		            System.out.println("El valor introducido no es válido. Introduce 0, 1 o 2:");
		        } else {
		            tipo1 = tipoStr.charAt(0);
		            tipoValido = true;
		        }
		    } while (!tipoValido);

		    System.out.println(comunidad.realizarPeticion(nif, descripcion, tipo1));
		    
		    System.out.println("¿Quiere realizar otra peticion? (s/n)"); 
		    Scanner leer = new Scanner(System.in);
		    String respuesta;
		    boolean primeraIteracion = true;
		    do {
		        if (!primeraIteracion) {
		            System.out.println("Respuesta no valida. Introduzca s o n:");
		        }
		        respuesta = leer.nextLine();
		        primeraIteracion = false;
		    } while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));

		    seguirPidiendo = respuesta.equalsIgnoreCase("s");
		    } while (seguirPidiendo);
		    
		    
		    
		    
		}

		//Este método es responsable de pedir información sobre el impuesto que debe pagar un vecino de la comunidad.
		public static void pedirInformacionImpuesto(Comunidad comunidad) {
		    leer=new Scanner (System.in);
		    String nif;
		    System.out.println("Introducir nif:");
		    nif=leer.next();
		    if(comunidad.vecinoYaRegistrado(nif)) {
		        double impuesto = comunidad.calcularImpuestoVecino(nif);
		        System.out.println("El impuesto que tiene que pagar el vecino es de:" + impuesto+"$");
		    }else {
		        System.out.println("El nif no pertenece a ningun vecino");
		    }
		}
		//El menu del programa
		public static void menu(Comunidad comunidad ) throws Exception {
			EmpresaLimpieza defecto= new EmpresaLimpieza("La Más Limpia","Calle toledo",10);
			Comunidad defecto1= new Comunidad(null,11,10);
			int seleccion;

			do {
				System.out.println("\nMenu de la comunidad:");
				try {
				seleccion= leerEnteroEntreAB(1, 9, "Selecciona una opcion del menu:\n1. Mostrar los vecinos de la comunnidad.\n2. Realizar peticion de mejora. \n3. Mostrar nombre del que paga mas impuestos.\n4. Mostrar informacion de peticiones urgentes.\n5. Consultar suma de impuestos.\n6. Mostrar cantidad de impuestos a pagar.\n7. Consultar cantidad a pagar por limpieza. \n8. Consultar media de impuestos de inquilinos y de propietarios .\n9. Salir");

				switch (seleccion) {
				case 1://Muestra la informacion de todos los vecinos
					System.out.println(comunidad.mostrarInformacionVecinos());
					break;
				case 2://Realiza la peticion de un vecino, y si el nif introducido no es de un vecino se registra en la comunidad y se realiza la peticion
					pedirInformacionVecinoPeticion(comunidad);
					break;
				case 3://Muestra los vecinos que mas impuestos pagan
					System.out.println("El nombre del vecino que mas impuestos paga es:"+comunidad.mostrarNombreVecinoMasImpuestos());
					break;
				case 4://Muestra las peticiones urgentes de un NIF en particular
				pedirInformacionVecino(comunidad);
					break;
				case 5://Muestra la cantidad de impuestos total que hay que pagar
					System.out.println("La suma de impuesto total a pagar en la comunidad es:"+comunidad.consultarSumaImpuestos()+"$");//pedirInformacionCliente(camp);
					
					break;
				case 6://Muestra la cantidad de impuesto a pagar de un NIF 
					pedirInformacionImpuesto(comunidad);
					break;
				case 7://Muestra la cantidad a pagar a la empresa de limpieza.
					System.out.println("La comunidad tiene que pagar un total de:"+comunidad.calcularCosteLimpieza(defecto)+"$");
					break;
				case 8:
					double mediaPropietarios = comunidad.calcularMediaImpuestos(Propietario.class);
					System.out.println("\nMedia de propietarios:"+mediaPropietarios);
					double mediaInquilinos = comunidad.calcularMediaImpuestos(Inquilino.class);
					System.out.println("\nMedia de inquilinos:"+mediaInquilinos);

					
				}
				} catch (RangoException e) {
			        System.out.println(e.getMessage());
			        seleccion = -1;  // Valor inválido para repetir el bucle
			    }
			}while (seleccion!=9);

		}
		public static void main(String[] args) throws Exception {
			System.out.println("\n******************\n*Programa Comunidad*\n******************\n");		
            Comunidad comunidad=crearMedianteFicherosPedir();
            if(comunidad!=null) {
           	menu(comunidad);
            }
			
			System.out.print("\n******************\n*Fin del programa*\n******************");
		}
            }
