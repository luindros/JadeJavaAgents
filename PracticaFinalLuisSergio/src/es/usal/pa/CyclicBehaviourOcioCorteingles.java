package es.usal.pa;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CyclicBehaviourOcioCorteingles extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String [][] tablaOcio = { 
		{"Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela","Vela"},
		{"Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada","Escalada"},
		{"Open Tenis y Mercado Medieval","Open Tenis y Mercado Medieval","Open Tenis","Open Tenis","Open Tenis","Open Tenis","Open Tenis","Open Tenis","Open Tenis","Open Tenis","Open Tenis","Open Tenis","Open Tenis","Open Tenis","Open Tenis","Patinaje","Patinaje","Patinaje","Patinaje","Patinaje","Patinaje","Patinaje","Patinaje","Patinaje","Patinaje","Patinaje","Patinaje","Patinaje","Patinaje","Patinaje","Patinaje"}
	};

	@Override
	public void action() {
		// espera recibir la información de corte ingles (referente a la consulta sobre actividades que le ha realizado un usuario)
		ACLMessage msg = this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("ontologiaCorteingles")));
		Mensaje mensaje;
		try {
			mensaje = (Mensaje)msg.getContentObject();
			int ciudad = -1;
			int dia;
			int mes;
			int anno;
			boolean ciudad_inexistente = false;
			
			// comprobamos sobre qué ciudad hay que consultar
			if(mensaje.infoOcio.ciudadEvento.equalsIgnoreCase("Madrid")){
				ciudad = 2;
			}
			else if(mensaje.infoOcio.ciudadEvento.equalsIgnoreCase("Plasencia")){
				ciudad = 1;
			}
			else if(mensaje.infoOcio.ciudadEvento.equalsIgnoreCase("Vigo")){
				ciudad = 0;
			}
			else {
				ciudad_inexistente = true;
			}
			
			if(ciudad_inexistente == true) { // si no existe esa ciudad indicamos que no hay ninguna actividad
				mensaje.infoOcio.actividad = "ninguna";
			}
			else { // si existe la ciudad, comprobamos que actividades hay para esa fecha y rellenamos el campo actividad
				String[] cadena = mensaje.infoOcio.fechaEvento.split("/");
				dia = Integer.parseInt(cadena[0]); 
				mes = Integer.parseInt(cadena[1]);
				anno = Integer.parseInt(cadena[2]);
				if (mes != 5 || anno != 2015){
					mensaje.infoOcio.actividad = "ninguna";
				}
				mensaje.infoOcio.actividad = tablaOcio[ciudad][dia-1];
			}
			
			// enviamos la informacion al agente Corte Ingles que nos realizó la consulta
			Utils.enviarMensaje(this.myAgent,"mensajeriaCorteIngles", mensaje, "ontologiaOcio",ACLMessage.INFORM);
		
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
