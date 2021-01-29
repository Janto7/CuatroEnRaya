package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Casilla {
	// He sacado el atributo que sobraba, y también el NullPointerException de la cabecera del método.

	private Ficha ficha;

	public Casilla() {
		ficha = null;
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) throws OperationNotSupportedException {
		if (ficha == null) {

			throw new NullPointerException("ERROR: No se puede poner una ficha nula.");

		} else if (estaOcupada()) {

			throw new OperationNotSupportedException("ERROR: Ya contengo una ficha.");

		} else {

			this.ficha = ficha;
		}
	}

	public boolean estaOcupada() {

		
		if (ficha != null) {

			return true;
		}
		return false;
	}

	@Override
	public  String toString() {
		if (ficha != null) {
			if (ficha.name().equals("AZUL"))
				return "A";
			return "V";
		} else {
			return " ";
		}
	}

}
