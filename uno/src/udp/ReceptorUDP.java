package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class ReceptorUDP {

    public static void main(String[] args) {
        int puertoEscucha = 12345;
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(puertoEscucha)) {
            System.out.println("Esperando datagrama en el puerto " + puertoEscucha + "...");

            DatagramPacket recibo = new DatagramPacket(buffer, buffer.length);
            socket.receive(recibo); // Se bloquea hasta recibir un paquete

            // 1. Obtener la longitud del mensaje recibido
            int bytesRec = recibo.getLength();

            // 2. Convertir solo los bytes útiles recibidos a String usando UTF-8
            String paquete = new String(recibo.getData(), 0, bytesRec, StandardCharsets.UTF_8);

            // 3. Imprimir los datos recibidos
            System.out.println("Mensaje recibido: " + paquete);
            System.out.println("NUmero bytes recibidos: " + bytesRec);
            System.out.println("Puerto local receptor del mensaje: " + socket.getLocalPort());
            System.out.println("Puerto origen del mensaje: " + recibo.getPort());
            System.out.println("IP de origen             : " + recibo.getAddress().getHostAddress());

            socket.close();
        } catch (IOException e) {
            System.err.println("Error al recibir el paquete UDP: " + e.getMessage());
            e.printStackTrace();
        }
    }
}