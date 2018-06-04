package es.usal.pa;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CyclicBehaviourAlojamientoCorteingles extends CyclicBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int [][] tablaHoteles = {
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	};

	@Override
	public void action() {
		
		// espera recibir la información de corte ingles (referente a la consulta sobre la reserva que le ha realizado un usuario)
		ACLMessage msg = this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("ontologiaCorteingles")));
		Mensaje mensaje;
		try {
			mensaje = (Mensaje)msg.getContentObject();
			
			String[] cadena_fechaIda = mensaje.infoViaje.fechaIda.split("/");
			String[] cadena_fechaVuelta = mensaje.infoViaje.fechaVuelta.split("/");
			int diaIda, mesIda, annoIda, diaVuelta, mesVuelta, annoVuelta;
			diaIda = Integer.parseInt(cadena_fechaIda[0]); 
			mesIda = Integer.parseInt(cadena_fechaIda[1]);
			annoIda = Integer.parseInt(cadena_fechaIda[2]);
			diaVuelta = Integer.parseInt(cadena_fechaVuelta[0]); 
			mesVuelta = Integer.parseInt(cadena_fechaVuelta[1]);
			annoVuelta = Integer.parseInt(cadena_fechaVuelta[2]);
			
			int ciudad = -1;
			int numHabVigo=2, numHabPlasencia=1, numHabMadridColon=2, numHabMadridBernabeu=1, numHabMadridCibeles=3;
			int i = -1, flag = -1;
			
			if (mesIda != 5 || annoIda != 2015 || mesVuelta != 5 || annoVuelta != 2015){
				mensaje.infoViaje.confirmacion = false;
				mensaje.infoViaje.hotel = "ninguno";
			}
			else {
				if(mensaje.infoViaje.destino.equalsIgnoreCase("Vigo")){ 
					ciudad = 0;
					mensaje.infoViaje.hotel = "Playa Samil";
					
					flag = 1; // suponenmos que se puede hacer la reserva
					i=diaIda;
					while((i<diaVuelta) && flag==1){ // no contamos el dia de vuelta
						if(tablaHoteles[ciudad][i] >= numHabVigo) {
							flag = 0; // no se puede hacer la reserva
						}
						i++;
					}
										
					if(flag == 1){
						for(i=diaIda; i<diaVuelta; i++){
							tablaHoteles[ciudad][i] +=1;
						}
						mensaje.infoViaje.confirmacion = true;
						
					}
					else {
						mensaje.infoViaje.confirmacion = false;
					}
				}
				
				else if(mensaje.infoViaje.destino.equalsIgnoreCase("Plasencia")){ 
					ciudad = 1;
					mensaje.infoViaje.hotel = "Parador";
					
					flag = 1; // suponenmos que se puede hacer la reserva
					i=diaIda;
					while((i<diaVuelta) && flag==1){ // no contamos el dia de vuelta
						if(tablaHoteles[ciudad][i] >= numHabPlasencia) {
							flag = 0; // no se puede hacer la reserva
						}
						i++;
					}
					
					if(flag == 1){
						for(i=diaIda; i<diaVuelta; i++){
							tablaHoteles[ciudad][i] +=1;
						}
						mensaje.infoViaje.confirmacion = true;
						
					}
					else {
						mensaje.infoViaje.confirmacion = false;
					}
				}
				
				else if(mensaje.infoViaje.destino.equalsIgnoreCase("Madrid")){
					ciudad = 2;
					// primero comprobamos si se puede hacer en el hotel Plaza Colón
					mensaje.infoViaje.hotel = "Plaza Colón";
					
					flag = 1; // suponenmos que se puede hacer la reserva
					i=diaIda;
					while((i<diaVuelta) && flag==1){ // no contamos el dia de vuelta
						if(tablaHoteles[ciudad][i] >= numHabMadridColon) {
							flag = 0; // no se puede hacer la reserva
						}
						i++;
					}
					if(flag == 1){ // se puede hacer la reserva en el hotel Plaza Colón
						for(i=diaIda; i<diaVuelta; i++){
							tablaHoteles[ciudad][i] +=1;
						}
						mensaje.infoViaje.confirmacion = true;
					}
					else {
						// como no se ha podido hacer la reserva, comprobamos si se puede hacer en el hotel Bernabeu
						ciudad = 3;
						mensaje.infoViaje.hotel = "Bernabeu";
						
						flag = 1; // suponenmos que se puede hacer la reserva
						i=diaIda;
						while((i<diaVuelta) && flag==1){ // no contamos el dia de vuelta
							if(tablaHoteles[ciudad][i] >= numHabMadridBernabeu) {
								flag = 0; // no se puede hacer la reserva
							}
							i++;
						}
						if(flag == 1) {
							for(i=diaIda; i<diaVuelta; i++){
								tablaHoteles[ciudad][i] +=1;
							}
							mensaje.infoViaje.confirmacion = true;
						}
						else {
							// como no se ha podido hacer la reserva, comprobamos si se puede hacer en el hotel Cibeles
							ciudad = 4;
							mensaje.infoViaje.hotel = "Cibeles";
							
							flag = 1; // suponenmos que se puede hacer la reserva
							i=diaIda;
							while((i<diaVuelta) && flag==1){ // no contamos el dia de vuelta
								if(tablaHoteles[ciudad][i] >= numHabMadridCibeles) {
									flag = 0; // no se puede hacer la reserva
								}
								i++;
							}
							if(flag == 1){
								for(i=diaIda; i<diaVuelta; i++){
									tablaHoteles[ciudad][i] +=1;
								}
								mensaje.infoViaje.confirmacion = true;
							}
							else { // no se ha podido hacer en ninguno de los tres hoteles de Madrid
								mensaje.infoViaje.hotel ="ninguno";
								mensaje.infoViaje.confirmacion = false;
							}
						}
						
					}
				}
				
				else { // la ciudad de destino no es ni Vigo, ni Plasencia ni Madrid
					mensaje.infoViaje.confirmacion = false;
					mensaje.infoViaje.hotel = "ninguno";
				}
			}
			
			// enviamos la informacion al agente Corte Ingles que nos realizó la consulta
			Utils.enviarMensaje(this.myAgent,"mensajeriaCorteIngles", mensaje, "ontologiaAlojamiento",ACLMessage.INFORM);
		
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
