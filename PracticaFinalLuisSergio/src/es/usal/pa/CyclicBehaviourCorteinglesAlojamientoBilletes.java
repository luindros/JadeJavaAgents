package es.usal.pa;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CyclicBehaviourCorteinglesAlojamientoBilletes extends CyclicBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void action() {
		// espera recibir la información de alojamiento (referente a si se ha podido llevar a cabo o no la reserva)
		ACLMessage msg = this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchOntology("ontologiaAlojamiento")));
	    Mensaje mensaje;
		try {
			mensaje = (Mensaje)msg.getContentObject();
			
		    if(mensaje.infoViaje.confirmacion==true) { //se ha podido hacer la reserva y se le envia el mensaje tanto a Billetes como al usuario
		    	Utils.enviarMensaje(this.myAgent, "mensajeriaBilletes", mensaje,"ontologiaCorteingles",ACLMessage.INFORM);
		    }
		    
		    // si la confirmación es false solo se le enviará el mensaje al usuario
		    // enviamos la respuesta al usuario que nos solicitó la información 	 
			Utils.enviarMensajeDirecto(this.myAgent, mensaje, "ontologiaCorteingles", mensaje.usuario,ACLMessage.INFORM);
					    
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
