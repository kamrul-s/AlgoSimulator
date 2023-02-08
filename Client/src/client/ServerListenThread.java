package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerListenThread extends Thread{
    private String readServer;
    Socket clientSocket;
    public ServerListenThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        start();
    }
    @Override
    public void run() {
        super.run();
        while (true) {
            BufferedReader inFromServer = null;
            try {
                inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                readServer = inFromServer.readLine();
                System.out.println(readServer);
                PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(),true);
                pw.println("Hello Server");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("From Server:\n" + readServer);
        }
    }
}
