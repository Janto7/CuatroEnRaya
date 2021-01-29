package org.iesalandalus.programacion.cuatroenraya.modelo;



public class Jugador {

	//Arreglo la clase jugador haciendo las comprobaciones en los metodos set.
	private String nombre;
	private Ficha colorFichas;

	public Jugador(String nombre, Ficha colorFichas)   {
		setColorFichas(colorFichas);
		setNombre(nombre);
	}

	public Ficha getColorFichas() {
		return colorFichas;
	}

	private void setColorFichas(Ficha colorFichas) {
		if (colorFichas == null) {

			throw new NullPointerException("ERROR: El color de las fichas no puede ser nulo.");

		} else {

			this.colorFichas = colorFichas;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if (nombre.trim().length() == 0) {
			throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
		}

		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre + " (" + colorFichas.name() + ")";
	}

}
