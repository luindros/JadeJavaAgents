package es.usal.pa;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CyclicBehaviourCorteinglesUsuario extends CyclicBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void action() {
		//espera recibir la información del usuario (referente a viajes o a consultas de ocio)
		ACLMessage msg = this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("ontologiaUsuario")));
	    Mensaje mensaje;
		try {
			mensaje = (Mensaje)msg.getContentObject();
			if(mensaje.id==1){ // es un mensaje de tipo mensajeViaje
				Utils.enviarMensaje(this.myAgent, "mensajeriaAlojamiento", mensaje,"ontologiaCorteingles",ACLMessage.REQUEST);
							}
			else { // es un mensaje de tipo mensajeOcio
				Utils.enviarMensaje(this.myAgent, "mensajeriaOcio", mensaje,"ontologiaCorteingles",ACLMessage.REQUEST);
							}
		   
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
