package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.modelo.Tablero;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
 
	private Consola() {

	}

	public static String leerNombre() {
		
		String nombre;
		boolean valido = false;
		System.out.print("Introduce el nombre del jugador: ");
		do {
			if (valido) {
				System.out.print("ERROR: El nombre no puede estar vacío. Introduce de nuevo el nombre.  ");

			} else {
				valido = true;
			}
			nombre = Entrada.cadena();
		} while (nombre.trim().length() == 0);
		return nombre;
	}

	public static Ficha elegirColorFichas() {
		
		int color1;
		Ficha ficha1 = null;
		boolean valido = false;
		System.out.print("Elige el color de tus fichas: (0-AZUL, 1-VERDE): ");
		do {
			if (valido) {
				System.out.print("Elige un color valido, (0-AZUL, 1-VERDE): ");

			} else {
				valido = true;
			}
			color1 = Entrada.entero();
		} while (color1 != 0 && color1 != 1);
		if (color1 == 0) {
			ficha1 = Ficha.AZUL;

		} else if (color1 == 1) {
			ficha1 = Ficha.VERDE;

		}
		return ficha1;
	}

	public static Jugador leerJugador() {

		System.out.println("Introduce los datos del PRIMER jugador");
		String nombre = leerNombre();
		Ficha ficha = elegirColorFichas();
		Jugador jugador = new Jugador(nombre, ficha);
		return jugador;
	}

	public static Jugador leerJugador(Ficha ficha) {

		System.out.println("Introduce los datos del SEGUNDO jugador");
		String nombre = leerNombre();
		Jugador jugador = new Jugador(nombre, ficha);
		return jugador;
	}

	public static int leerColumna(Jugador jugador) {
		
		int columna;
		boolean valido = false;
		System.out.print(jugador.getNombre() + ", introduce la columna en la que deseas introducir la ficha (0-6): ");
		do {
			if (valido) {
				System.out.print(jugador.getNombre() + ", introduce una columna valida: ");
			} else {
				valido = true;
			}
			columna = Entrada.entero();
		} while (columna < 0 || columna >= Tablero.COLUMNAS);
		return columna;
	}

}