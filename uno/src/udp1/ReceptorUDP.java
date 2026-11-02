package udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ReceptorUDP {

    public static void main(String[] args) {
        int puertoEscucha = 12346;
        int puertoDestino = 12345;
        try (DatagramSocket socket = new DatagramSocket(puertoEscucha);
             Scanner sc = new Scanner(System.in)) {

            boolean ejecutando = true;

            System.out.println("--- CHAT UDP (RECEPTOR) ---");
            System.out.println("Esperando mensajes del Emisor...");

            while (ejecutando) {
                // 1. LEER Y RECIBIR
                byte[] bufferRecibo = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(bufferRecibo, bufferRecibo.length);
                socket.receive(paqueteRecibido);

                String mensajeRecibido = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength(), StandardCharsets.UTF_8);

                if (mensajeRecibido.equalsIgnoreCase("quit")) {
                    ejecutando = false;
                    System.out.println("\nEl Emisor ha finalizado la conversación.");
                } else {
                    System.out.println("\nEmisor dice: " + mensajeRecibido);

                    // 2. ESCRIBIR Y RESPONDER
                    System.out.print("Receptor > ");
                    String textoEnviar = sc.nextLine();

                    byte[] bufferEnvio = textoEnviar.getBytes(StandardCharsets.UTF_8);
                    // Usamos la IP y el puerto de origen que venían en el paquete recibido
                    InetAddress destino = paqueteRecibido.getAddress();

                    DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, destino, puertoDestino);
                    socket.send(paqueteEnvio);

                    if (textoEnviar.equalsIgnoreCase("quit")) {
                        ejecutando = false;
                        System.out.println("Cerrando chat en Receptor...");
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error en el Receptor UDP: " + e.getMessage());
        }
    }
}