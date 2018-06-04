package es.usal.pa;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AgenteAlojamiento extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected CyclicBehaviourAlojamientoCorteingles cyclicBehaviourAlojamientoCorteingles;
	
	public void setup(){
		// El establecimiento de servicios.
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(this.getAID());
		
			ServiceDescription sd = new ServiceDescription();
			sd.setName("MensajeriaAlojamiento");
			sd.setType("mensajeriaAlojamiento");
			sd.addOntologies("ontologia");
			sd.addLanguages(new SLCodec().getName());
			// a�adimos el servicio 
			dfd.addServices(sd); 
			
			try
			{
				DFService.register(this, dfd); // para que el agente registre esos servicios
			}
			catch(FIPAException e)
			{
				System.err.println("Error: "+this.getLocalName()+": "+e.getMessage());
			}
		// Definici�n de comportamientos.
			cyclicBehaviourAlojamientoCorteingles=new CyclicBehaviourAlojamientoCorteingles();
		//a�adimos el comportamiento
			this.addBehaviour(cyclicBehaviourAlojamientoCorteingles);
	}
}

