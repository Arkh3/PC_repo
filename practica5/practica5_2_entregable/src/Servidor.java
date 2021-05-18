import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	private static int PORT = 1234;
	private static InetAddress HOST = null;
	private static ServerSocket serverSocket;
	// tabla usuarios --- (id usuario, fin, fout)
	// tabla informacion --- (id usuario, [f1,f2,....])
	
	
	public static void main(String[] args) throws IOException {
		
		HOST = InetAddress.getLocalHost();
		
		serverSocket = new ServerSocket(PORT);
			
		while(true) {
			
			System.out.println("Servidor: Esperando cliente.");
			Socket client = serverSocket.accept(); 
			System.out.println("Servidor: Cliente aceptado.");
			
			// Creamos un hilo para delegar la gestión del cliente
			OyenteCliente oyente = new OyenteCliente(client);
			oyente.start();

		}
	}
}
