package udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EmisorUDP {

    public static void main(String[] args) {
        int puertoEscucha = 12345;
        int puertoDestino = 12346;

        try (DatagramSocket socket = new DatagramSocket(puertoEscucha);
             Scanner sc = new Scanner(System.in)) {

            InetAddress destino = InetAddress.getLocalHost();
            boolean ejecutando = true;

            System.out.println("--- CHAT UDP (EMISOR) ---");
            System.out.println("Escribe tus mensajes (escribe 'quit' para salir):");

            while (ejecutando) {
                // 1. ESCRIBIR Y ENVIAR
                System.out.print("\nEmisor > ");
                String textoEnviar = sc.nextLine();

                byte[] bufferEnvio = textoEnviar.getBytes(StandardCharsets.UTF_8);
                DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, destino, puertoDestino);
                socket.send(paqueteEnvio);

                if (textoEnviar.equalsIgnoreCase("quit")) {
                    ejecutando = false;
                    System.out.println("Cerrando chat en Emisor...");
                } else {
                    // 2. LEER Y RECIBIR RESPUESTA
                    byte[] bufferRecibo = new byte[1024];
                    DatagramPacket paqueteRecibido = new DatagramPacket(bufferRecibo, bufferRecibo.length);
                    
                    System.out.println("Esperando respuesta...");
                    socket.receive(paqueteRecibido);

                    String mensajeRecibido = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength(), StandardCharsets.UTF_8);
                    
                    if (mensajeRecibido.equalsIgnoreCase("quit")) {
                        ejecutando = false;
                        System.out.println("\nEl Receptor ha cerrado el chat.");
                    } else {
                        System.out.println("Receptor dice: " + mensajeRecibido);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error en el Emisor UDP: " + e.getMessage());
        }
    }
}