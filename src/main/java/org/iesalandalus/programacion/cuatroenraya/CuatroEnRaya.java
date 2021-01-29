package org.iesalandalus.programacion.cuatroenraya;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.cuatroenraya.modelo.Jugador;
import org.iesalandalus.programacion.cuatroenraya.modelo.Tablero;
import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

public class CuatroEnRaya {

	public final static int NUMERO_JUGADORES = 2;
	private Jugador[] jugadores;
	public Tablero tablero;

	public CuatroEnRaya(Jugador jugador1, Jugador jugador2) {
		tablero = new Tablero();
		jugadores = new Jugador[NUMERO_JUGADORES];
		jugadores[0] = jugador1;
		jugadores[1] = jugador2;
	}

	public void jugar() {

		boolean ganador = false;
		while (!tablero.estaLleno() && !ganador) {
			for (int i = 0; i < NUMERO_JUGADORES; i++) {
				ganador = tirar(jugadores[i]);
				if (ganador) {
					System.out.println("ENHORABUENA, " + jugadores[i].getNombre() + " has ganado!!!");
					break;
				} else if (tablero.estaLleno()) {
					System.out.println("El tablero está lleno");
					break;
				}
			}
		}

	}

	private boolean tirar(Jugador jugador) {
		boolean leido = false;
		boolean ganador = false;
		do {

			int columna = Consola.leerColumna(jugador);
			try {
				ganador = tablero.introducirFicha(columna, jugador.getColorFichas());
				leido = true;
			} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		} while (!leido);
		System.out.println(tablero);
		return ganador;
	}

}
