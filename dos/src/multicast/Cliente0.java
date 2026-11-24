package multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class Cliente0 {
    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket(12345)) {
            InetAddress grupo = InetAddress.getByName("230.0.0.1");

            // 1. Sintonizamos la emisora (unirse al grupo)
            socket.joinGroup(grupo);
            System.out.println("Suscrito al grupo. Esperando mensaje...");

            // 2. Escuchamos el paquete (bloqueante hasta que llegue algo)
            byte[] buffer = new byte[1024];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);

            // 3. Leemos el contenido
            String recibido = new String(paquete.getData(), 0, paquete.getLength(), StandardCharsets.UTF_8);
            System.out.println("Recibido: " + recibido);

            // 4. Abandonamos el grupo antes de cerrar
            socket.leaveGroup(grupo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}