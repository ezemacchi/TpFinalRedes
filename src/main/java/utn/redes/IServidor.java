package utn.redes;

import java.net.Socket;

public interface IServidor {

    public void desconectar();
    public void start();
    public void setSocket(Socket socket);
    public void setIdSession(int idSession);
}
