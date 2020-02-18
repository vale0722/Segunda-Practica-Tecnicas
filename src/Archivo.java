import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
import java.util.ArrayList;

public class Archivo {
    public ArrayList<Long> leerArchivo(String ruta) {
        ArrayList<Long> numeros = new ArrayList<Long>();
        try {
            BufferedReader br = getBuffered(ruta);
            String linea = br.readLine();
            while (linea != null) {
                numeros.add(Long.parseLong(linea));
                linea = br.readLine();
            }
            System.out.println("ARCHIVO LEIDO");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return numeros;
    }

    private BufferedReader getBuffered(String link) {
        FileReader lector = null;
        BufferedReader br = null;
        try {
            File Arch = new File(link);
            if (!Arch.exists()) {
                System.out.println("No se encontró el archivo");
            } else {
                lector = new FileReader(link);
                br = new BufferedReader(lector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return br;
    }

    public ArrayList<String> convertirDecimalAIp(ArrayList<Long> numerosDecimales) {
        ArrayList<String> direccionesIp = new ArrayList<String>();
        for (int i = 0; i < numerosDecimales.size(); i++){
            long numeroDecimal = numerosDecimales.get(i);

            String ipBinaria = Long.toBinaryString(numeroDecimal);

            //Definicion de variables mascara
            long mascara1 = 4278190080L;
            long mascara2 = 16711680L;
            long mascara3 = 65280L;
            long mascara4 = 255L;

            //Creacion de octetos con logica manipulacion de bits
            long octetoi1 = (numeroDecimal & mascara1) >> 24;
            long octetoi2 = (numeroDecimal & mascara2) >> 16;
            long octetoi3 = (numeroDecimal & mascara3) >> 8;
            long octetoi4 = (numeroDecimal & mascara4);

            //Concatenacion de octetos
            String direccionIp = octetoi1 + "." + octetoi2 + "." + octetoi3 + "." + octetoi4;
            direccionesIp.add(direccionIp);
        }
        return direccionesIp;
    }

    public void escribirNuevoArchivo(ArrayList<Long> numerosDecimales, ArrayList<String> direccionesIp) throws IOException {
        String ruta = "OUTPUT.txt";
        File file = new File(ruta);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < numerosDecimales.size(); i++){
            bw.write(i+1 + "." + numerosDecimales.get(i) + " " + direccionesIp.get(i));
            bw.newLine();
        }
        bw.close();
        System.out.println("SE CREO EXITOSAMENTE EL ARCHIVO CON DIRECCIONES IP");
    }
}