package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Date;

public class GestionErrores {
	public static void escribirErrores(String error) throws IOException {
		try {
            // Crea un archivo de depuración y lo abre en modo append
            FileWriter fw = new FileWriter("C:\\Users\\jordi\\eclipse-workspace\\practica11\\AppHipoteques.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            // Escribe los errores en el archivo de depuración
            java.util.Date fecha = new Date();
            InetAddress address = InetAddress.getLocalHost();
            String sHostName = address.getHostName();
            bw.write("IP "+address + " " + fecha + " " + error);
            bw.newLine();
            
            // Cierra el archivo de depuración
            bw.close();
            fw.close();
            
        } catch (IOException e) {
        	FileWriter fw = new FileWriter("debug.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            // Escribe los errores en el archivo de depuración
            bw.write(e.toString());
            bw.newLine();
            
            // Cierra el archivo de depuración
            bw.close();
            fw.close();
        }
	}
}
