package org.iesalandalus.programacion.peonajedrez;

import javax.naming.OperationNotSupportedException;

public class Peon {

	private Color color;
	private Posicion posicion;

	public Peon() {
		setColor(Color.NEGRO);
		setPosicion(new Posicion(7, 'd'));
	}

	public Peon(Color color) {
		setColor(color);

		if (color.equals(Color.BLANCO))
			setPosicion(new Posicion(2, 'd'));
		setPosicion(new Posicion(7, 'd'));
	}

	public Peon(Color color, char columna) {
		if (columna < 'a' || columna > 'h')
			throw new IllegalArgumentException("ERROR: La columna es incorrecta.");

		setColor(color);
		if (color.equals(Color.BLANCO))
			setPosicion(new Posicion(2, columna));
		setPosicion(new Posicion(7, columna));
	}

	public Color getColor() {
		return color;
	}

	private void setColor(Color color) {
		if (color == null)
			throw new NullPointerException("ERROR: El color no puede ser nulo");
		this.color = color;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	private void setPosicion(Posicion posicion) {
		if (posicion == null)
			throw new NullPointerException("ERROR: La posición no puede ser nula");
		this.posicion = new Posicion(posicion);
	}

	public void mover(Direccion direccion) throws OperationNotSupportedException {
		if (direccion == null)
			throw new NullPointerException("ERROR: Movimiento no permitido.");

		try {
			int fila = posicion.getFila();
			if (color == Color.BLANCO)
				fila += 1;
			fila -= 1;
			if (direccion == Direccion.IZQUIERDA) {
				setPosicion(new Posicion(fila, (char) (posicion.getColumna() - 1)));
			} else {
				setPosicion(new Posicion(fila, (char) (posicion.getColumna() + 1)));
			}
		} catch (IllegalArgumentException e) {
			throw new OperationNotSupportedException("ERROR: El movimiento no es válido.");
		}
	}

	public void mover(int pasos) throws OperationNotSupportedException {
		int fila = posicion.getFila();

		if (pasos < 1 || pasos > 2) {
			throw new OperationNotSupportedException("ERROR: El peón puede mover sólo 1 o 2 pasos.");
		} else if (pasos == 2 && ((fila != 2 && color == Color.BLANCO) || (fila != 7 && color == Color.NEGRO))) {
			throw new OperationNotSupportedException(
					"ERROR: El peón sólo se puede mover 2 pasos cuando se encuentra en la casilla inicial.");
		}
		try {
			if (color == Color.BLANCO)
				fila += pasos;
			fila -= pasos;
			setPosicion(new Posicion(fila, posicion.getColumna()));
		} catch (IllegalArgumentException e) {
			throw new OperationNotSupportedException("ERROR: El movimiento no es válido.");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((posicion == null) ? 0 : posicion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Peon other = (Peon) obj;
		if (color != other.color)
			return false;
		if (posicion == null) {
			if (other.posicion != null)
				return false;
		} else if (!posicion.equals(other.posicion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("color=%s, posicion=%s", color, posicion);
	}

}
