package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Tablero {
	// Arreglo la clase tablero y hago uso de la clase StringBuilder.
	public final static int FILAS = 6;

	public final static int COLUMNAS = 7;

	public final static int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;

	private Casilla[][] casillas = new Casilla[FILAS][COLUMNAS];

	public Tablero() {
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				casillas[i][j] = new Casilla();
			}
		}
	}

	public boolean estaVacio() {

		for (int i = 0; i < COLUMNAS; i++) {
			if (!columnaVacia(i)) {
				i = COLUMNAS;
				return false;
			}
		}
		return true;
	}

	private boolean columnaVacia(int columna) {

		for (int i = 0; i < FILAS; i++) {
			if (casillas[i][columna].estaOcupada()) {
				return false;
			}
		}
		return true;
	}

	public boolean estaLleno() {

		for (int i = 0; i < COLUMNAS; i++) {
			if (!columnaLlena(i)) {
				i = COLUMNAS;
				return false;
			}
		}
		return true;
	}

	private boolean columnaLlena(int columna) {

		for (int i = 0; i < FILAS; i++) {
			if (!casillas[i][columna].estaOcupada()) {
				return false;
			}
		}
		return true;
	}

	public boolean introducirFicha(int columna, Ficha ficha)
			throws OperationNotSupportedException, IllegalArgumentException {

		comprobarFicha(ficha);

		comprobarColumna(columna);

		int filaVacia = getPrimeraFilaVacia(columna);

		casillas[filaVacia][columna].setFicha(ficha);

		return comprobarTirada(filaVacia, columna, ficha);
	}

	private void comprobarFicha(Ficha ficha) {
		if (ficha == null)
			throw new NullPointerException("ERROR: La ficha no puede ser nula.");
	}

	private void comprobarColumna(int columna) throws OperationNotSupportedException, IllegalArgumentException {
		if (columna < 0 || columna >= COLUMNAS)
			throw new IllegalArgumentException("ERROR: Columna incorrecta.");
		if (columnaLlena(columna))
			throw new OperationNotSupportedException("ERROR: Columna llena.");
	}

	private int getPrimeraFilaVacia(int columna) {
		for (int i = FILAS - 1; i >= 0; i--) {
			if (casillas[i][columna].getFicha() == null)
				return i;
		}
		return -1;
	}

	private boolean comprobarTirada(int fila, int columna, Ficha ficha) {
		return comprobarHorizontal(fila, ficha) || comprobarVertical(columna, ficha)
				|| comprobarDiagonalNE(fila, columna, ficha) || comprobarDiagonalNO(fila, columna, ficha);
	}

	private boolean objetivoAlcanzado(int consecutivas) {
		if (consecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS)
			return true;
		return false;
	}

	private boolean comprobarHorizontal(int fila, Ficha ficha) {

		int contadorConsecutivas = 0;
		
		for (int j = 0; j < COLUMNAS; j++) {

			if (casillas[fila][j].estaOcupada() && casillas[fila][j].getFicha().equals(ficha)) {

				contadorConsecutivas++;
			} else {

				contadorConsecutivas = 0;
			}
			if (objetivoAlcanzado(contadorConsecutivas)) {

				return true;
			}
		}

		return false;
	}

	private boolean comprobarVertical(int columna, Ficha ficha) {

		int contadorConsecutivas = 0;
	
		for (int i = 0; i < FILAS; i++) {

			if (casillas[i][columna].estaOcupada() && casillas[i][columna].getFicha().equals(ficha)) {

				contadorConsecutivas++;
				
			} else {

				contadorConsecutivas = 0;
			}
			if (objetivoAlcanzado(contadorConsecutivas)) {

			return true;
			}
		}

		return false;
	}
	private boolean comprobarDiagonalNE(int fila, int columna, Ficha ficha) {
		
		int contadorConsecutivas = 0;
		int desplazamientoInicial = menor(fila, columna);
		int filaInicial = fila - desplazamientoInicial;
		int columnaInicial = columna - desplazamientoInicial;
		
		for (int i = filaInicial, j = columnaInicial; i < FILAS && j < COLUMNAS; i++, j++) {

			if (casillas[i][j].estaOcupada() && casillas[i][j].getFicha().equals(ficha)) {

			contadorConsecutivas++;
				
			} else {

				contadorConsecutivas = 0;
			}
			
			if (objetivoAlcanzado(contadorConsecutivas)) {

				return  true;
			}
		}

		return false;
	}

	private boolean comprobarDiagonalNO(int fila, int columna, Ficha ficha) {

		int contadorConsecutivas = 0;
		int desplazamientoInicial = menor(fila, COLUMNAS - 1 - columna);
		int filaInicial = fila - desplazamientoInicial;
		int columnaInicial = columna + desplazamientoInicial;
	
		for (int i = filaInicial, j = columnaInicial; i < FILAS && j >= 0; i++, j--) {

			if (casillas[i][j].estaOcupada() && casillas[i][j].getFicha().equals(ficha)) {

				contadorConsecutivas++;

			} else {

				contadorConsecutivas = 0;
			}

			if (objetivoAlcanzado(contadorConsecutivas)) {

				return true;
			}
		}
		
		return false;
	}

	private int menor(int numero1, int numero2) {
		if (numero1 > numero2)
			return numero2;
		return numero1;
	}

	@Override

	public String toString() {
		StringBuilder strTablero = new StringBuilder();
		strTablero.append("");
		for (int i = 0; i < FILAS; i++) {
			strTablero.append("|");
			for (int j = 0; j < COLUMNAS; j++) {
				strTablero.append(casillas[i][j].toString());
			}
			strTablero.append("|\n");
		}
		strTablero.append(" -------\n");
		return strTablero.toString();
	}
}
