package utn.redes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String args[]) throws IOException {

        ServerSocket ss;

        IServidor servidor = null;


        System.out.print("Inicializando servidor... ");

        try {
            ss = new ServerSocket(3000);
            System.out.println("\t[OK]");
            int idSession = 0;
            int opt = 0;

            while (true) {
                Socket socket;
                socket = ss.accept();
                servidor = optServidor();
                System.out.println("Nueva conexión entrante: "+socket);
                servidor.setIdSession(idSession);
                servidor.setSocket(socket);
                servidor.start();
                idSession++;
            }
        }
        catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static IServidor optServidor(){

        Scanner sc = new Scanner(System.in);
        int opt = 0;
        int flag = 0;
        IServidor servidor = null;

        while(flag == 0){

            System.out.println("Tipo de servidor:");
            System.out.println("1.Servidor Telnet");
            System.out.println("2.Servidor Java");
            System.out.print("Opcion: ");


            if(sc.hasNextInt()) {
                opt = sc.nextInt();
                if (opt == 1) {
                    servidor = new ServidorTelnet();
                    flag = 1;
                } else if (opt == 2) {
                    servidor = new ServidorJava();
                    flag = 1;
                } else {
                    System.out.println("Opcion incorrecta");
                }
            }
            else {
                System.out.println("Opcion invalida");
            }
        }

        return servidor;
    }

}
