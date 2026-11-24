package multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Servidor1 {

    public static void main(String[] args) {
        // IP de grupo Multicast válida (rango 224.0.0.0 a 239.255.255.255)
        String ipGrupo = "230.0.0.1";
        int puerto = 12345;

        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        try (MulticastSocket socket = new MulticastSocket()) {
            InetAddress grupo = InetAddress.getByName(ipGrupo);
            System.out.println("Servidor Multicast iniciado. Enviando hora cada 15 segundos...");

            while (true) {
                // 1. Obtener la hora actual formateada
                String horaActual = "Hora actual del servidor: " + LocalTime.now().format(formatoHora);
                byte[] buffer = horaActual.getBytes(StandardCharsets.UTF_8);

                // 2. Empaquetar y enviar al grupo Multicast
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, grupo, puerto);
                socket.send(paquete);
                System.out.println("Enviado -> " + horaActual);

                // 3. Esperar 15 segundos antes de refrescar
                Thread.sleep(15000);
            }

        } catch (Exception e) {
            System.err.println("Error en el servidor multicast: " + e.getMessage());
        }
    }
}