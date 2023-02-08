package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientMain extends Application {
    private Stage stage;
    static PlotGraphController pcg;
    static ArrayList<Center> centers = new ArrayList<>();
    static ArrayList<edge1Info> edge1Infos = new ArrayList<>();
    static ArrayList<edge2Info> edge2Infos = new ArrayList<>();
    static WelcomeController wc = new WelcomeController();
    static ArrayList<Socket> sockets = new ArrayList<>();
    static ArrayList<PrintWriter> writeToServer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String readInput = "";
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("localhost",5555);
            sockets.add(clientSocket);
            pcg = new PlotGraphController();

            launch(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter outToServer = null;
        new ServerListenThread(clientSocket);
        while (true){
            if(wc.flag==1)
                System.out.println("Client got Connected");
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        stage.setTitle("Welcome Window");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
