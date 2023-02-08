package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private ServerSocket serverSocket;
    private NetworkUtil client_NU;
    private static int clientCount=0;
    int id = 1;
    Server(){
        try
        {
           /*InetAddress add = InetAddress.getLocalHost();
           serverSocket = new ServerSocket(9999, 128, add);*/
           serverSocket = new ServerSocket(5555);
           //System.out.println(serverSocket.getLocalSocketAddress());
        }
        catch (IOException ex)
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Server Started Successfully");
        while(true)
        {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerThread wt = new ServerThread(clientSocket);
            Thread t = new Thread(wt);
            t.start();
            //serve(clientSocket);
            clientCount++;
            System.out.println("Client "+id+" is now connected.No. of clients connected "+clientCount);
            id++;
        }
    }
    public static void main(String[] args)
    {
        new Server();
    }
}


