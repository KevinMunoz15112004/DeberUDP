package cliente;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ControladorCliente {

    @FXML private Label lblPregunta;
    @FXML private RadioButton opA;
    @FXML private RadioButton opB;
    @FXML private RadioButton opC;
    @FXML private RadioButton opD;
    @FXML private ToggleGroup grupo;
    @FXML private Label lblResultado;

    private int numeroPregunta = 1;
    private int totalRespondidas = 0;
    private int puntaje = 0;
    private DatagramSocket socket;
    private InetAddress servidor;

    @FXML
    public void initialize() {
        new Thread(() -> {
            try {
                socket = new DatagramSocket();
                servidor = InetAddress.getByName("localhost");
                enviar("PREGUNTA");
                String data = recibir();
                Platform.runLater(() -> mostrarPregunta(data));
            } catch (Exception e) {
                Platform.runLater(() ->
                        lblResultado.setText("Error de conexión")
                );
            }
        }).start();
    }

    private void mostrarPregunta(String data) {
        String[] partes = data.split("\\|");
        lblPregunta.setText("PREGUNTA " + numeroPregunta + " | " + partes[0]);
        opA.setText("A) " + partes[1]);
        opB.setText("B) " + partes[2]);
        opC.setText("C) " + partes[3]);
        opD.setText("D) " + partes[4]);
    }

    @FXML
    private void responder() {
        new Thread(() -> {
            try {
                String seleccion = "";
                if (opA.isSelected()) seleccion = "A";
                else if (opB.isSelected()) seleccion = "B";
                else if (opC.isSelected()) seleccion = "C";
                else if (opD.isSelected()) seleccion = "D";
                if (seleccion.isEmpty()) {
                    Platform.runLater(() ->
                            lblResultado.setText("Selecciona una opción")
                    );
                    return;
                }

                enviar("RESPUESTA | " + seleccion);
                String resultado = recibir();
                if (resultado.equals("CORRECTO")) {
                    puntaje++;
                }
                totalRespondidas++;

                Platform.runLater(() -> {
                    lblResultado.setText(resultado);
                    grupo.selectToggle(null); // limpiar selección
                });

                // pedir siguiente pregunta automáticamente
                enviar("PREGUNTA");
                String nueva = recibir();

                if (nueva.equals("FIN")) {
                    Platform.runLater(() -> mostrarResultadoFinal());
                    return;
                }

                Platform.runLater(() -> {
                    numeroPregunta++;
                    mostrarPregunta(nueva);
                });

            } catch (Exception e) {
                Platform.runLater(() ->
                        lblResultado.setText("Error")
                );
            }
        }).start();
    }

    private void enviar(String msg) throws Exception {
        byte[] data = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, servidor, 5000);
        socket.send(packet);
    }

    private String recibir() throws Exception {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return new String(packet.getData(), 0, packet.getLength());
    }

    private void mostrarResultadoFinal() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado final");
        alert.setHeaderText("Examen terminado");
        alert.setContentText("Tu puntaje es: " + puntaje + " / " + totalRespondidas);

        alert.showAndWait();

        lblPregunta.setText("Examen finalizado");
        lblResultado.setText("Puntaje: " + puntaje);
        opA.setDisable(true);
        opB.setDisable(true);
        opC.setDisable(true);
        opD.setDisable(true);
    }
}