package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class EmisorUDP {

	public static void main(String[] args) {

		int puerto = 12345;
		try (DatagramSocket socket = new DatagramSocket()) {
			
			InetAddress destino = InetAddress.getLocalHost();
			String saludo = "Enviando Saludos desde cliente !!";

			// Convertimos la cadena a bytes especificando la codificación UTF-8
			byte[] mensaje = saludo.getBytes(StandardCharsets.UTF_8);

			// Construimos el datagrama indicando datos, longitud, IP y puerto
			DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, puerto);

			// Enviamos el paquete por el socket UDP (USO EL socket con SEND)
			socket.send(envio);
			System.out.println("Mensaje enviado correctamente a " + destino + ":" + puerto);

		} catch (IOException e) {
			System.err.println("Error al enviar el paquete UDP: " + e.getMessage());
			e.printStackTrace();
		}
	}
}