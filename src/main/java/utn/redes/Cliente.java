package utn.redes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends Thread{

    protected Socket sk;
    protected DataOutputStream dos;
    protected DataInputStream dis;
    protected DataInputStream disfk;
    private int id;

    public Cliente(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {

            disfk = new DataInputStream(System.in);

            int flag = 0;

            while(flag == 0) {

                System.out.print("Ingrese direccion ip:");
                String ip = disfk.readLine();
                System.out.print("Ingrese puerto:");
                Integer port = Integer.parseInt(disfk.readLine());
                System.out.println(ip + port);

                try{
                    sk = new Socket(ip, port);
                    flag = 1;
                } catch (IOException i){
                    System.out.println("Imposible conectarse al servidor, pruebe con otra direccion");
                }
            }

            dos = new DataOutputStream(sk.getOutputStream());
            dis = new DataInputStream(sk.getInputStream());

            System.out.println("Cliente " + id + " Conectado");
            dos.writeUTF("Connected");
            String respuesta="";
            String mensaje = "";

            do{
                System.out.print("Escriba su mensaje: ");
                mensaje = disfk.readLine();
                dos.writeUTF(mensaje);
                System.out.print("Servidor: ");
                respuesta = dis.readUTF();
                System.out.println(respuesta);
            } while (!respuesta.equals("bye"));

            dos.writeUTF("bye");
            System.out.println("Conexion finalizada");

            dis.close();
            dos.close();
            sk.close();
        }catch (IOException ex) {
            System.out.println("La conexion con " + sk.toString() + " se ha cerrado inesperadamente");
        }
    }
}

