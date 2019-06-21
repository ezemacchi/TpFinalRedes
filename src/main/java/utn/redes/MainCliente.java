package utn.redes;

import java.util.ArrayList;

public class MainCliente {


   public static void main(String[] args) {
        Thread cliente = new Cliente(0);
        cliente.start();
    }
}
