package servidor;

public class Cuestionario {

    private String pregunta;
    private String opcionA;
    private String opcionB;
    private String opcionC;
    private String opcionD;
    private char correcta;

    public Cuestionario(String pregunta, String a, String b, String c, String d, char correcta) {
        this.pregunta = pregunta;
        this.opcionA = a;
        this.opcionB = b;
        this.opcionC = c;
        this.opcionD = d;
        this.correcta = correcta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getA() {
        return opcionA;
    }

    public String getB() {
        return opcionB;
    }

    public String getC() {
        return opcionC;
    }

    public String getD() {
        return opcionD;
    }

    public char getCorrecta() {
        return correcta;
    }
}