package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class WelcomeController {
    public Button exitButton;
    public Button openButton;
    public AnchorPane rootPane;
    int flag = 0;

    public void openAction(ActionEvent event) throws IOException {
        AnchorPane choicePane = FXMLLoader.load(getClass().getResource("plotGraph.fxml"));
        rootPane.getChildren().setAll(choicePane);
        flag = 1;
        if(flag == 1){
            System.out.println("connected");
        }
    }

    public void exitButtonAction(ActionEvent event) {
        System.exit(0);
    }
}