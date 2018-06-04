package es.usal.pa;


import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CyclicBehaviourCorteinglesOcio extends CyclicBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void action() {
		// espera recibir la información de ocio (referente a las actividades que hay en esa fecha)
		ACLMessage msg = this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchOntology("ontologiaOcio")));
		Mensaje mensaje;
		try {
			mensaje = (Mensaje) msg.getContentObject();
				
			// enviamos la respuesta al usuario que nos solicitó la información 	 
		    Utils.enviarMensajeDirecto(this.myAgent, mensaje, "ontologiaCorteingles", mensaje.usuario,ACLMessage.INFORM);
		     	 
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
