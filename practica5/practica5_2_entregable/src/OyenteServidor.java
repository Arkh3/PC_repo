import java.io.*;
import java.net.Socket;

public class OyenteServidor extends Thread{
	
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public OyenteServidor(Socket socket, ObjectOutputStream out, ObjectInputStream in) throws IOException {
		
		this.socket = socket;
		this.in  = in;
		this.out  = out;
	}
	
	public void run() {
		
		Mensaje m;
		
		while(true) {
			
			m = leerMensaje();
			
			procesarMensaje(m);
		}
	}
	
	public Mensaje leerMensaje() {
		
		Mensaje m = null;
		
		try {
			m = (Mensaje) in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}
	
	
	// TODO hacer toda la parte de procesar mensaje 
	private void procesarMensaje(Mensaje m) {
		
		
		switch(m.getTipo()) {
		
		case 1:
			
			/*- MENSAJE_CONFIRMACION_CONEXION:*/
			
			System.out.println(m.getDestino() + ": conexión establecida");
			
			break;
	
		case 9:
			
		    /*- MENSAJE_CONFIRMACION_LISTA_USUARIOS
	        imprimir lista usuarios por standard output*/	
			
			
			
			break;
			
		case 2:
			
			/*- MENSAJE_EMITIR_FICHERO
			(nos llega nombre de cliente C1 e informacion pedida 3)
			enviar MENSAJE_PREPARADO_CLIENTESERVIDOR a mi oyente
			crear proceso EMISOR y esperar en accept la conexion*/	
			
			
			
			break;
			
		case 3:
			
			/*- MENSAJE_PREPARADO_SERVIDORCLIENTE
			(llega direccion Ip y puerto del propietario de fichero)
			crear proceso RECEPTOR*/	
			
			
			
			break;
			
		case 4:

			   /*- MENSAJE_CONFIRMACION_CERRAR_CONEXION
			   imprimir adios por standard output*/
			
			break;
			
		default:
			
			System.out.println("Oyente de servidor: Mensaje no valido (0 - 4). " + m.getTipo());
		}
	}
}
