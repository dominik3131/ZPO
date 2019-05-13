package lab7_3;

import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length > 2) {
            //sprawdzamy czy odchylenie wieksze od 0
            assert Double.parseDouble(args[2]) >= 0;
            Random rand = new Random();

            //plik binarny do zapisu
            DataOutputStream binaryOutput = new DataOutputStream(new FileOutputStream("binaryFile.bin"));
            //generowanie rozkladu
            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                binaryOutput.writeDouble(rand.nextGaussian() * Double.parseDouble(args[2]) + Double.parseDouble(args[1]));
            }
            binaryOutput.close();

            DataOutputStream textOutput = new DataOutputStream(new FileOutputStream("textFile.txt"));
            DataInputStream binaryInput = new DataInputStream(new FileInputStream("binaryFile.bin"));
            
            String newLine = System.getProperty("line.separator");
            //polski formater
            NumberFormat PLFormat = NumberFormat.getInstance(new Locale("pl", "PL"));
            while (binaryInput.available() > 0) {
                textOutput.writeBytes(PLFormat.format(binaryInput.readDouble()));
                textOutput.writeBytes(newLine);
            }

            binaryInput.close();
            textOutput.close();
        }

    }

}
