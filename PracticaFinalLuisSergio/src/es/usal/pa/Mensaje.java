package es.usal.pa;

import jade.core.AID;

import java.io.Serializable;

public class Mensaje implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Atributos del mensaje
		int id;
		AID usuario;
		MensajeViaje infoViaje;
		MensajeOcio infoOcio;
				
	// Constructores con argumentos
		public Mensaje(AID _usuario, String _origen, String _destino, String _fechaIda, String _fechaVuelta){
			id = 1; //pues va a tener solo informacion sobre el viaje
			usuario=_usuario;
			infoViaje = new MensajeViaje(_origen,_destino,_fechaIda,_fechaVuelta);
			infoOcio = null;
		}
		
		public Mensaje(AID _usuario, String _ciudadEvento, String _fechaEvento){
			id = 2; // pues va a tener solo informacion sobre la consulta de ocio
			usuario=_usuario;
			infoViaje = null;
			infoOcio = new MensajeOcio(_ciudadEvento,_fechaEvento);
		}
		
	//Métodos de acceso
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public AID getUsuario() {
			return usuario;
		}

		public void setUsuario(AID usuario) {
			this.usuario = usuario;
		}

		public MensajeViaje getInfoViaje() {
			return infoViaje;
		}

		public void setInfoViaje(MensajeViaje infoViaje) {
			this.infoViaje = infoViaje;
		}

		public MensajeOcio getInfoOcio() {
			return infoOcio;
		}

		public void setInfoOcio(MensajeOcio infoOcio) {
			this.infoOcio = infoOcio;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
			
	
}
