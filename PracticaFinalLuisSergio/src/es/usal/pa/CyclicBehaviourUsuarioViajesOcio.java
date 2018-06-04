package es.usal.pa;

import java.util.Scanner;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CyclicBehaviourUsuarioViajesOcio extends CyclicBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void action() {
		//Reservas de viajes
		System.out.println("Bienvenido a la agencia de viajes y eventos del Corte Ingles.");
		System.out.printf("--------------------------------------------------------------\n\n");
		System.out.println("¿Desea hacer un viaje?(s/n)");
		Scanner sc=new Scanner(System.in);
		String textoViaje=sc.nextLine();
		if(textoViaje.equalsIgnoreCase("s")){
			
			//solicitud de la información 
			System.out.println("Introduzca la siguiente informacion:");
			System.out.printf("\tCiudad de origen: ");
			String origen=sc.nextLine();
			System.out.printf("\tCiudad de destino: ");
			String destino=sc.nextLine();
			System.out.printf("\tFecha de ida(del 1/05/2015 al 30/05/2015): ");
			String fechaIda=sc.nextLine();
			System.out.printf("\tFecha de vuelta(del 2/05/2015 al 31/05/2015): ");
			String fechaVuelta=sc.nextLine();
			System.out.printf("\n");
			
			AID nombre_usuario = this.myAgent.getAID();
			
			//envio de la información a Corteingles que es quien tendrá el servicio mensajeriaCorteingles
			Mensaje mensajeTipoViaje = new Mensaje(nombre_usuario,origen,destino,fechaIda,fechaVuelta);
			Utils.enviarMensaje(this.myAgent, "mensajeriaCorteIngles", mensajeTipoViaje,"ontologiaUsuario",ACLMessage.REQUEST);
									
			//recepción de la respuesta 
			ACLMessage msg = this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchOntology("ontologiaCorteingles")));
			try {
				mensajeTipoViaje = (Mensaje)msg.getContentObject();
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(mensajeTipoViaje.infoViaje.confirmacion==true){
				System.out.println("Su viaje se ha reservado correctamente para la fecha del "+mensajeTipoViaje.infoViaje.fechaIda+" al "+mensajeTipoViaje.infoViaje.fechaVuelta+" en la ciudad "+mensajeTipoViaje.infoViaje.destino+" y en el hotel "+mensajeTipoViaje.infoViaje.hotel+".");
			}
			else{
				System.out.println("Lo sentimos,en su reserva para la estancia del "+mensajeTipoViaje.infoViaje.fechaIda+" al "+mensajeTipoViaje.infoViaje.fechaVuelta+" en la ciudad "+mensajeTipoViaje.infoViaje.destino+" y en el hotel "+mensajeTipoViaje.infoViaje.hotel+" no hay plazas suficientes.");
			}
			
		}
		//Consultas Ocio
		System.out.printf("\n");
		System.out.println("¿Desea ver actividades de ocio?(s/n)");
		String textoOcio=sc.nextLine();
		if(textoOcio.equalsIgnoreCase("s")){
			System.out.println("Introduzca la siguiente informacion:");
			System.out.printf("\tCiudad: ");
			String ciudadEvento=sc.nextLine();
			System.out.printf("\tFecha(del 1/05/2015 al 31/05/2015): ");
			String fechaEvento=sc.nextLine();
			System.out.printf("\n");
			
			AID nombre_usuario = this.myAgent.getAID();
			
			//envio de la información a Corteingles que es quien tendrá el servicio mensajeriaCorteingles
			Mensaje mensajeTipoOcio = new Mensaje(nombre_usuario,ciudadEvento, fechaEvento);
			Utils.enviarMensaje(this.myAgent, "mensajeriaCorteIngles", mensajeTipoOcio,"ontologiaUsuario",ACLMessage.REQUEST);
			
			//recepción de la respuesta 
			ACLMessage msg = this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM), MessageTemplate.MatchOntology("ontologiaCorteingles")));
			try {
				mensajeTipoOcio = (Mensaje)msg.getContentObject();
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Las actividades en la ciudad "+mensajeTipoOcio.infoOcio.ciudadEvento+" para la fecha "+mensajeTipoOcio.infoOcio.fechaEvento+" son: "+mensajeTipoOcio.infoOcio.actividad+".");
			
		}
		
		System.out.println("Gracias por su visita.");
		System.out.printf("\n");
		
	}
		
}
