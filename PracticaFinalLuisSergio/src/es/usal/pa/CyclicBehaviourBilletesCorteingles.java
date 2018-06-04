package es.usal.pa;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CyclicBehaviourBilletesCorteingles extends CyclicBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void action() {
		// espera recibir la información de corte ingles 
		ACLMessage msg = this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchOntology("ontologiaCorteingles")));
		Mensaje mensajeTipoViaje;
		try {
			mensajeTipoViaje = (Mensaje) msg.getContentObject();
			// imprime que se ha realizado correctamente la reserva de billetes
			System.out.println("Reserva de billetes realizada exitosamente para la fecha del "+mensajeTipoViaje.infoViaje.fechaIda+" al "+mensajeTipoViaje.infoViaje.fechaVuelta+".");
			
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
