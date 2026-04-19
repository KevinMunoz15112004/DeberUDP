package servidor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.List;

public class Servidor {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5000);
        System.out.println("Servidor iniciado...");
        List<Cuestionario> preguntas = BancoPreguntas.obtenerPreguntas();
        int indice = 0;

        byte[] buffer = new byte[1024];

        while (true) {
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);
            String msg = new String(request.getData(), 0, request.getLength());
//            SocketAddress clientAddr = request.getSocketAddress();
//            System.out.println("Recibido de " + clientAddr + ": " + msg);

            InetAddress ip = request.getAddress();
            int puerto = request.getPort();

            String respuesta = "";

            if (msg.equals("PREGUNTA")) {
                if (indice >= preguntas.size()) {
                    respuesta = "FIN";
                } else {
                    Cuestionario p = preguntas.get(indice);
                    respuesta = p.getPregunta() + "|" +
                            p.getA() + "|" +
                            p.getB() + "|" +
                            p.getC() + "|" +
                            p.getD();
                }
            }
            else if (msg.startsWith("RESPUESTA")) {
                String[] partes = msg.split("\\|");
                char seleccion = partes[1].trim().charAt(0);
                Cuestionario p = preguntas.get(indice);
                if (seleccion == p.getCorrecta()) {
                    respuesta = "CORRECTO";
                } else {
                    respuesta = "INCORRECTO";
                }
                indice++;
            }

            byte[] data = respuesta.getBytes();
            DatagramPacket reply = new DatagramPacket(data, data.length, ip, puerto);
            socket.send(reply);
        }
    }
}