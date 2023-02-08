package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ServerThread implements Runnable{
    private Socket connection;
    private InputStream is;
    private OutputStream os;
    static ArrayList<PrintWriter> toClient = new ArrayList<>();
    public ServerThread(Socket connection) {
        this.connection = connection;
        try
        {
            this.is = this.connection.getInputStream();
            this.os = this.connection.getOutputStream();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while(connection.isConnected()){
            String clientMessage;
            int i=0,j=0;;
            BufferedReader inFromClient = null;
            try {
                inFromClient = new BufferedReader(new InputStreamReader(this.is));
                PrintWriter outToClient = new PrintWriter(this.os, true);
                //outToClient.println("Hello!Welcome to Algo Simulator");
                clientMessage = inFromClient.readLine();
                System.out.println(clientMessage);
                /*StringTokenizer tokenizer = new StringTokenizer(clientMessage, "#");
                String[] userInput = new String[1000];
                while (tokenizer.hasMoreTokens()) {
                    userInput[i] = tokenizer.nextToken();
                    System.out.println(userInput[i]);
                    System.out.println(userInput[i]);
                    i++;
                }*/
                /*if(userInput[0].equals("C")){
                        toClient.add(outToClient);
                }*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}