package udp;

import java.net.DatagramPacket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Servidor0 {
    public static void main(String[] args) {
        byte[] buffer =new byte[1024];
        DatagramPacket recibo = new DatagramPacket(buffer, 1024);
        //Socket.receive(recibo);
        int byteRec = recibo.getLength();
        String llegada = new String(recibo.getData(), 0, byteRec, StandardCharsets.UTF_8);

        System.out.println("Puerto origen del mensaje: " +recibo.getPort());
        System.out.println("IP de origen: "+recibo.getAddress().getHostAddress());
    }
}