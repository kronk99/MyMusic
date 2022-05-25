package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Cliente {
    private ServerSocket serverSocket;
    private Server server;
    public Cliente(ServerSocket serversockete){
        this.serverSocket=serversockete;
        System.out.println("se crea el serversocket");
    }
    public void startServer(){
        System.out.println("se buscan clientes...");
        try{
            while (!serverSocket.isClosed()){
                Socket socket =serverSocket.accept();
                System.out.println("nuevo cliente conectado");
                Server server=new Server(socket);//se necesita crear un hilo por cada cliente que se una, por eso el runnable

                Thread thread= new Thread(server);
                thread.start();
            }
        }catch (IOException e){
            e.printStackTrace();
            closeServerSocket();

        }
    }
    public void closeServerSocket(){
        try {
            if (serverSocket != null){//Creo que esto genera error dice no null
                serverSocket.close();

            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
