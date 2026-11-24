package multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class Servidor0 {
    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket()) {
            // 1. Definimos la "frecuencia" (IP de clase D + puerto)
            InetAddress grupo = InetAddress.getByName("230.0.0.1");
            int puerto = 12345;

            // 2. Preparamos el mensaje en bytes
            String mensaje = "Hola a todos los conectados al grupo";
            byte[] buffer = mensaje.getBytes(StandardCharsets.UTF_8);

            // 3. Enviamos el paquete al grupo entero de una sola vez
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, grupo, puerto);
            socket.send(paquete);

            System.out.println("Mensaje emitido al grupo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}