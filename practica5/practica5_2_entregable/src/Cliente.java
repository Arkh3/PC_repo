import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
	
	private static int PORT = 1234;
	private static InetAddress HOST;
	private static ObjectOutputStream out;
	private static ObjectInputStream in;
	private static Socket socket;
	private static Usuario usuario = new Usuario();
	
	public static void main(String[] args) throws IOException {
		
		preguntarNombreYFicheros();

		crearSocketConServidor();
		
		crearOyenteServidor();
		
		enviarConexion();
		
		menuPrincipal();
    }
	
	private static void preguntarNombreYFicheros() {
			
		System.out.print("- Nombre de Usuario: ");
		Scanner teclado = new Scanner(System.in);
		usuario.setId(teclado.nextLine());
		
        System.out.print("- Introduce el numero de ficheros: ");
        String n = teclado.nextLine();
        int numero = Integer.parseInt(n);

        System.out.print("Introduce tus fichero: ");
        List<String> ficheros = new ArrayList<String>();

        while (numero > 0) {

            String fichero = teclado.nextLine();
            ficheros.add(fichero);

            numero--;
        }

        teclado.close();
		
		usuario.setInformacionCompartida(ficheros);
	}
	
	private static void crearSocketConServidor() throws IOException {
		
		HOST = InetAddress.getLocalHost();

		socket = new Socket(HOST, PORT);
		
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	private static void crearOyenteServidor() throws IOException {
		
		OyenteServidor oyente = new OyenteServidor(socket); // TODO pasarle los atributos necesarios
		
		oyente.start();
	}
	
	private static void enviarConexion() throws IOException {
		
		MensajeConexion m = new MensajeConexion(usuario.getId(), "Servidor", usuario);
		
		out.writeObject(m);
	}
	
	private static void menuPrincipal() {
		
		// TODO abrir un menú con 3 opciones: 1. consultar lista de usuarios 2. pedir ficher 3. salir
	}
}
