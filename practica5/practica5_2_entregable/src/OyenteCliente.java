import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OyenteCliente extends Thread{

	Socket socket;
	private static ObjectOutputStream out;
	private static ObjectInputStream in;
	
	public OyenteCliente(Socket socket) throws IOException {
		
		this.socket = socket;
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	// TODO ver toda esta parte que es un ctrl c ctrl v de OyenteServidor
	
	public void run() {
		
		Mensaje m;
		
		while(true) {
			
			m = leerMensaje();
			
			try {
				procesarMensaje(m);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Mensaje leerMensaje() {
		
		Mensaje m = null;
		
		try {
			m = (Mensaje) in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("no lee el mensaje" + m);
			e.printStackTrace();
		}
		
		return m;
	}
	
	
	// TODO hacer toda la parte de procesar mensaje 
	private void procesarMensaje(Mensaje m) throws IOException {
		
		switch(m.getTipo()) {
		
		case 0:
		    /*- MENSAJE_CONEXION:
		    * guardar informacion del usuario (en las tablas)
		 	* envio mensaje confirmacion conexion fout	*/
			
			
			MensajeConfirmaionConexion confirmacion = new MensajeConfirmaionConexion("Servidor", m.getOrigen());
			out.writeObject(confirmacion);
			
			break;
		

		default:
			
			System.out.println("Oyente de cliente: Mensaje no valido (0 - ). " + m.getTipo());
		}
	}
}
