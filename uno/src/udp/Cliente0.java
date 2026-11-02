package udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Cliente0 {
    public static void main(String[] args) throws UnknownHostException{
        int port = 12345; // Puerto al que envío
    InetAddress destino = InetAddress.getLocalHost(); // IP a la que envío

    byte[] mensaje = new byte[1024]; // Matriz de bytes
    String Saludo = ("Enviando Saludos desde el cliente");
    
    // Codificarlo a bytes para enviarlo
    mensaje=Saludo.getBytes();

    // Construyo el datagrama a enviar
    DatagramPacket envio = new DatagramPacket(
            mensaje,
            mensaje.length,
            destino,
            port);

    } 
}