package es.usal.pa;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AgenteCorteingles extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected CyclicBehaviourCorteinglesUsuario cyclicBehaviourCorteinglesUsuario;
	protected CyclicBehaviourCorteinglesAlojamientoBilletes cyclicBehaviourCorteinglesAlojamientoBilletes;
	protected CyclicBehaviourCorteinglesOcio cyclicBehaviourCorteinglesOcio;
	protected ParallelBehaviour parallelBehaviour;
	
	public void setup(){
		// El establecimiento de servicios.
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(this.getAID());
		
			ServiceDescription sd = new ServiceDescription();
			sd.setName("MensajeriaCorteIngles");
			sd.setType("mensajeriaCorteIngles");
			sd.addOntologies("ontologia");
			sd.addLanguages(new SLCodec().getName());
			// añadimos el servicio 
			dfd.addServices(sd); 
			
			try
			{
				DFService.register(this, dfd); // para que el agente registre esos servicios
			}
			catch(FIPAException e)
			{
				System.err.println("Error: "+this.getLocalName()+": "+e.getMessage());
			}
		
		// Definición de comportamientos.
			
			cyclicBehaviourCorteinglesUsuario = new CyclicBehaviourCorteinglesUsuario();
			cyclicBehaviourCorteinglesAlojamientoBilletes = new CyclicBehaviourCorteinglesAlojamientoBilletes();
			cyclicBehaviourCorteinglesOcio = new CyclicBehaviourCorteinglesOcio();
			parallelBehaviour = new ParallelBehaviour();
			
			//añadimos los tres sub-comportamientos paralelos
			ThreadedBehaviourFactory threadedBehaviourFactory;
			
			threadedBehaviourFactory = new ThreadedBehaviourFactory();
			parallelBehaviour.addSubBehaviour(threadedBehaviourFactory.wrap(cyclicBehaviourCorteinglesUsuario));
			
			threadedBehaviourFactory = new ThreadedBehaviourFactory();
			parallelBehaviour.addSubBehaviour(threadedBehaviourFactory.wrap(cyclicBehaviourCorteinglesAlojamientoBilletes));
			
			threadedBehaviourFactory = new ThreadedBehaviourFactory();
			parallelBehaviour.addSubBehaviour(threadedBehaviourFactory.wrap(cyclicBehaviourCorteinglesOcio));
						
			//añadimos su comportamiento
			addBehaviour(parallelBehaviour);
	}

}
