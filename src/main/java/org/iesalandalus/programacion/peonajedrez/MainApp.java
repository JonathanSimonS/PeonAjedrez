package org.iesalandalus.programacion.peonajedrez;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {

	private static Peon peon;

	public static void main(String[] args) {
		System.out.println("Programa para aprender a colocar y mover un peón en el tablero de ajedrez");
		System.out.println("-------------------------------------------------------------------------");
		int opcion;
		do {
			mostrarMenu();
			opcion = elegirOpcion();
			ejecutarOpcion(opcion);
			if (opcion != 5)
				mostrarPeon();
		} while (opcion != 5);
		System.out.println("Muchas gracias por su tiempo.");
	}

	private static void mostrarPeon() {
		if (peon == null)
			System.out.println("ERROR: Antes de mostrar el peón, debes crearlo.");
		System.out.println("Peón: " + peon);
	}

	private static void mostrarMenu() {
		System.out.println("\nMENÚ DE OPCIONES:");
		System.out.println("-----------------:");
		System.out.println("1. Crear peón por defecto");
		System.out.println("2. Crear peón de un color");
		System.out.println("3. Crear peón de un color en columna dada");
		System.out.println("4. Mover peón");
		System.out.println("5. Salir");
	}

	private static int elegirOpcion() {
		int opcion = 0;
		do {
			System.out.print("Elige una opción (1-5):");
			opcion = Entrada.entero();
		} while (opcion < 1 || opcion > 5);
		return opcion;
	}

	private static Color elegirColor() {
		int opcion = 0;
		do {
			System.out.print("Elige un color (1. Negro - 2. Blanco)");
			opcion = Entrada.entero();
		} while (opcion < 1 || opcion > 2);
		if (opcion == 1)
			return Color.NEGRO;
		return Color.BLANCO;
	}

	private static char elegirColumnaInicial() {
		char columna;
		do {
			System.out.print("Elige la columna inicial (a - h): ");
			columna = Entrada.caracter();
		} while (columna < 'a' || columna > 'h');
		return columna;
	}

	private static void mostrarMenuMovimientos() {
		System.out.println("- MOVIMIENTOS -");
		System.out.println("1. Avanzar peón un paso.");
		System.out.println("2. Avanzar peón dos pasos.");
		System.out.println("3. Avanzar peón hacia la izquierda.");
		System.out.println("4. Avanzar peón hacia la derecha.");
		System.out.println("5. Salir.");

	}

	private static void realizarMovimiento(int movimiento) throws OperationNotSupportedException {
		switch (movimiento) {
		case 1:
			peon.mover(1);
			break;
		case 2:
			peon.mover(2);
			break;
		case 3:
			peon.mover(Direccion.IZQUIERDA);
			break;
		case 4:
			peon.mover(Direccion.DERECHA);
			break;
		case 5:
			break;
		default:
			break;
		}
	}

	private static void crearPeonDefecto() {
		peon = new Peon();
	}

	private static void crearPeonColor() {
		Color color = elegirColor();
		peon = new Peon(color);
	}

	private static void crearPeonColorColumna() {
		Color color = elegirColor();
		char colum = elegirColumnaInicial();

		peon = new Peon(color, colum);
	}

	private static void mover() {
		mostrarMenuMovimientos();
		int opcion = elegirOpcion();
		try {
			realizarMovimiento(opcion);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void ejecutarOpcion(int opcion) {
		switch (opcion) {
		case 1:
			crearPeonDefecto();
			break;
		case 2:
			crearPeonColor();
			break;
		case 3:
			crearPeonColorColumna();
			break;
		case 4:
			if (peon != null) {
				mover();
			} else {
				System.out.println("Primero debe crear el peón.");
			}
		case 5:
			break;
		default:
			break;
		}
	}

}
