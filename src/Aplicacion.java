import java.io.IOException;
import java.util.ArrayList;

public class Aplicacion {
    public static void main(String[] args) throws IOException {
        String ruta = "Direcciones.txt";
        Archivo a = new Archivo();
        ArrayList<Long> numerosDecimales = a.leerArchivo(ruta);
        ArrayList<String> direccionesIp =a.convertirDecimalAIp(numerosDecimales);
        a.escribirNuevoArchivo(numerosDecimales, direccionesIp);
    }
}
