package multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;

public class Cliente1 {

    public static void main(String[] args) {
        String ipGrupo = "230.0.0.1";
        int puerto = 12345;
        byte[] buffer = new byte[1024];

        try (MulticastSocket socket = new MulticastSocket(puerto)) {
            InetAddress grupo = InetAddress.getByName(ipGrupo);

            // Unión al grupo multicast (compatible con Java moderno y clásico)
            InetSocketAddress direccionGrupo = new InetSocketAddress(grupo, puerto);
            NetworkInterface interfaz = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            socket.joinGroup(direccionGrupo, interfaz);

            System.out.println("Cliente conectado al grupo Multicast. Esperando la hora...");

            while (true) {
                // 1. Recibir paquete del grupo
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);

                // 2. Convertir a String y mostrar por consola
                String mensaje = new String(
                        paquete.getData(), 0, paquete.getLength(), StandardCharsets.UTF_8
                );
                System.out.println("Recibido -> " + mensaje);
            }

        } catch (Exception e) {
            System.err.println("Error en el cliente multicast: " + e.getMessage());
        }
    }
}