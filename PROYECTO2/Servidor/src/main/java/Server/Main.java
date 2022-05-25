package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Cliente cliente = new Cliente(serverSocket);
        cliente.startServer();

    }
}
