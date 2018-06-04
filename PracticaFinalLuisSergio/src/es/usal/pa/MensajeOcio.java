package es.usal.pa;

import java.io.Serializable;

public class MensajeOcio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Atributos del mensaje
		String ciudadEvento;
		String fechaEvento;
		String actividad;
			
	// Constructor con argumentos
		public MensajeOcio(String _ciudadEvento, String _fechaEvento){
			ciudadEvento=_ciudadEvento;
			fechaEvento=_fechaEvento;
			actividad="";//pues inicialmente no sabemos que actividad o actividades hay
		}
		
	//Métodos de acceso

		public String getCiudadEvento() {
			return ciudadEvento;
		}

		public void setCiudadEvento(String ciudadEvento) {
			this.ciudadEvento = ciudadEvento;
		}

		public String getFechaEvento() {
			return fechaEvento;
		}

		public void setFechaEvento(String fechaEvento) {
			this.fechaEvento = fechaEvento;
		}

		public String getActividad() {
			return actividad;
		}

		public void setActividad(String actividad) {
			this.actividad = actividad;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
	
	
}
