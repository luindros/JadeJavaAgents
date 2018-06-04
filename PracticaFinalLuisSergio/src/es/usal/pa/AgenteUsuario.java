package es.usal.pa;

import jade.core.Agent;

public class AgenteUsuario extends Agent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected CyclicBehaviourUsuarioViajesOcio cyclicBehaviourUsuarioViajesOcio;
	
	public void setup() {
		// El establecimiento de servicios.
			// El Agente usuario no define ningún servicio.
			
		// Definición de comportamientos.
			cyclicBehaviourUsuarioViajesOcio=new CyclicBehaviourUsuarioViajesOcio();
			
			//añadimos el comportamiento
			this.addBehaviour(cyclicBehaviourUsuarioViajesOcio);
		
	}
}
