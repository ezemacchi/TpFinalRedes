package utn.redes;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorJava extends Thread implements IServidor {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private DataInputStream disfk;
    private Scanner sc;
    private int idSession;

    public ServidorJava() {
    }

    private void init(){
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            disfk = new DataInputStream(System.in);
            sc = new Scanner(socket.getInputStream());
        }
        catch (IOException ex) {
            Logger.getLogger(ServidorTelnet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public void desconectar() {
        try {
            socket.close();
        }
        catch (IOException ex) {
            System.out.println("La conexion con " + socket.toString() + " se ha cerrado inesperadamente");
        }
    }

    @Override
    public void run() {

        init();
        String accion = "";
        String mensaje ="";

        try {
            accion = dis.readUTF();

            while (!accion.equals("x")) {

                accion = dis.readUTF();

                if (!accion.equals("x"))
                {
                    System.out.println("Mensaje recibido de cliente " + idSession + ": " + accion);
                    System.out.print("Escriba su Mensaje a cliente: " + idSession + ": ");
                    mensaje = disfk.readLine();
                    dos.writeUTF(mensaje);
                }
            }

            dos.writeUTF("bye");
            desconectar();

            System.out.println("Cliente desconectado");
        }
        catch (IOException ex) {
            System.out.println("La conexion con " + socket.toString() + " se ha cerrado inesperadamente");
        }

    }

}
