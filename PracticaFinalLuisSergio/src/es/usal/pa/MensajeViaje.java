package es.usal.pa;

import java.io.Serializable;

public class MensajeViaje implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Atributos del mensaje
		String origen;
		String destino;
		String fechaIda;
		String fechaVuelta;
		String hotel;
		boolean confirmacion;
		
	// Constructor con argumentos
		public MensajeViaje(String _origen, String _destino, String _fechaIda, String _fechaVuelta){
			origen=_origen;
			destino=_destino;
			fechaIda=_fechaIda;
			fechaVuelta=_fechaVuelta;
			hotel="";//pues esa información se rellenará posteriormente
			confirmacion=false;//pues inicialmente no sabemos si se puede hacer la reserva del viaje
		}

	// Métodos de acceso 
		
		public String getOrigen() {
			return origen;
		}

		public void setOrigen(String origen) {
			this.origen = origen;
		}

		public String getDestino() {
			return destino;
		}

		public void setDestino(String destino) {
			this.destino = destino;
		}

		public String getFechaIda() {
			return fechaIda;
		}

		public void setFechaIda(String fechaIda) {
			this.fechaIda = fechaIda;
		}

		public String getFechaVuelta() {
			return fechaVuelta;
		}

		public void setFechaVuelta(String fechaVuelta) {
			this.fechaVuelta = fechaVuelta;
		}

		public boolean isConfirmacion() {
			return confirmacion;
		}

		public void setConfirmacion(boolean confirmacion) {
			this.confirmacion = confirmacion;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public String getHotel() {
			return hotel;
		}

		public void setHotel(String hotel) {
			this.hotel = hotel;
		}
	
	
}
