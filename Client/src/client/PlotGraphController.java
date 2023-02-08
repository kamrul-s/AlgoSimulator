package client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.StringTokenizer;

import static client.ClientMain.centers;

public class PlotGraphController implements Initializable {

    public AnchorPane showCircle;
    public AnchorPane plotPane;
    @FXML
    public TextField weightInput;
    @FXML
    public ChoiceBox<String> weightLabel;
    @FXML
    public Button weightNext;
    @FXML
    public Button inputComplete;
    @FXML
    public ToggleGroup group1;
    @FXML
    public ToggleGroup group2;
    @FXML
    Button backButton;
    @FXML
    Button addnodeButton;
    @FXML
    Button addedgeButton;
    @FXML
    Button nextButton;
    @FXML
    Button finishNodeButton;
    @FXML
    Button finishEdgeButton;
    @FXML
    Button completeButton;
    @FXML
    Button refreshButton;
    @FXML
    RadioButton weightedButton;
    @FXML
    RadioButton unweightedButton;
    @FXML
    RadioButton directedButton;
    @FXML
    RadioButton undirectedButton;

    private double[] mouseX = new double[100];
    private double[] mouseY = new double[100];
    private double[] edge1X = new double[5050];
    private double[] edge1Y = new double[5050];
    private double[] edge2X = new double[5050];
    private double[] edge2Y = new double[5050];
    private Circle[] circle = new Circle[100];
    private Text[] text = new Text[100];
    private Text weightText=new Text();
    private Line[] line = new Line[5050];
    private Line arr1 = new Line();
    private Line arr2 = new Line();
    private Circle dirCircle = new Circle();
    private int i=0,j=0,p=0,wa=0,dir=0,z,k=0,t=0,lweight=0,temp1,temp2;
    private int[][] adj=new int[5050][5050];
    private int[][] temp3=new int[5050][5050];
    private int temp=0,tempedge=0;

    @FXML
    public void nextAction(ActionEvent event) throws IOException {
        if ((weightedButton.isSelected() || unweightedButton.isSelected()) && (directedButton.isSelected() || undirectedButton.isSelected())){
            if(weightedButton.isSelected()) wa=1;
            if(directedButton.isSelected()) dir=1;
            File dir1  = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\dir.txt");
            if (!dir1.exists()){
                dir1.createNewFile();
            }
            PrintWriter printWriter = new PrintWriter(dir1);
            int temp;
            temp = dir;
            printWriter.println(temp);
            printWriter.close();
            File wa1  = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\wa.txt");
            if (!wa1.exists()){
                wa1.createNewFile();
            }
            printWriter = new PrintWriter(wa1);
            //int temp;
            temp = wa;
            printWriter.println(temp);
            printWriter.close();
            addnodeButton.setDisable(false);
            finishNodeButton.setDisable(true);
            weightedButton.setDisable(true);
            unweightedButton.setDisable(true);
            directedButton.setDisable(true);
            undirectedButton.setDisable(true);
        }
    }

    @FXML
    public void backclick(ActionEvent event) throws IOException {
        AnchorPane welcomePane = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        plotPane.getChildren().setAll(welcomePane);
    }

    @FXML
    public void addnodeAction(ActionEvent event) {
        finishNodeButton.setDisable(false);
        refreshButton.setDisable(false);
        showCircle.setOnMousePressed((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(temp == 0) addCircles();
            }
        }));
    }

    public void addCircles(){
        mouseX[i] = MouseInfo.getPointerInfo().getLocation().getX();
        mouseY[i] = MouseInfo.getPointerInfo().getLocation().getY();
        mouseX[i]-=400;
        mouseY[i]-=30;
        if((mouseX[i]>=100 && mouseX[i]<=1300) && (mouseY[i]>=150 && mouseY[i]<=850)) {
            circle[i] = new Circle();
            text[i] = new Text();
            circle[i].setCenterX(mouseX[i]);
            circle[i].setCenterY(mouseY[i]);
            circle[i].setRadius(20.0f);
            circle[i].setFill(Color.rgb(200, 150, 180));
            text[i].setX(mouseX[i] - 20);
            text[i].setY(mouseY[i] - 30);
            String s = Integer.toString(i);
            s = "Node " + s;
            text[i].setText(s);
            showCircle.getChildren().addAll(circle[i], text[i]);
            i++;
        }

        //writing centre coordinates of circles to file
        File mouseXFile  = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\Circle center.txt");
        if (!mouseXFile.exists()){
            try {
                mouseXFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(mouseXFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int k=0;k<=i;k++) {
            printWriter.print(mouseX[k]+" "+mouseY[k]+" ");
        }
        printWriter.close();
    }

    @FXML
    public void addedgeAction(ActionEvent event) {
        if(tempedge == 0)
            drawline();
        finishEdgeButton.setDisable(false);
        p = 0;
    }

    public void drawline() {
        showCircle.setOnMousePressed((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(p==0 && tempedge==0) {
                    edge1X[j] = MouseInfo.getPointerInfo().getLocation().getX();
                    edge1Y[j] = MouseInfo.getPointerInfo().getLocation().getY();
                    edge1X[j] -= 400;
                    edge1Y[j] -= 30;
                    edge1Info eg = new edge1Info(edge1X[j],edge1Y[j]);
                    ClientMain.edge1Infos.add(eg);
                    for (int k = 0; k < i; k++) {
                        if (edge1X[j] >= (mouseX[k] - 20) && edge1X[j] <= (mouseX[k] + 20) &&
                                edge1Y[j] >= (mouseY[k] - 20) && edge1Y[j] <= (mouseY[k] + 20)) {
                            edge1X[j] = mouseX[k];
                            edge1Y[j] = mouseY[k];
                            p = 1;
                            z=k;
                        }
                    }
                }
                else if(p==1 && tempedge==0)
                {
                    edge2X[j] = MouseInfo.getPointerInfo().getLocation().getX();
                    edge2Y[j] = MouseInfo.getPointerInfo().getLocation().getY();
                    edge2X[j] -= 400;
                    edge2Y[j] -= 30;
                    edge2Info eg2 = new edge2Info(edge2X[j],edge2Y[j]);
                    ClientMain.edge2Infos.add(eg2);
                    for (int k = 0; k < i; k++) {
                        if (edge2X[j] <= (mouseX[k] + 20) && edge2X[j] >= (mouseX[k] - 20) && edge2Y[j] <= (mouseY[k] + 20) && edge2Y[j] >= (mouseY[k] - 20)) {

                            double a, b, c, d;
                            a = edge1X[j];
                            b = edge1Y[j];
                            edge2X[j] = mouseX[k];
                            edge2Y[j] = mouseY[k];
                            c = mouseX[k];
                            d = mouseY[k];
                            if(z==k) {
                                edge2X[j]+=40;
                                c+=40;
                            }
                            line[j] = new Line();
                            arr1 = new Line();
                            arr2 = new Line();
                            line[j].setStartX(edge1X[j]);
                            line[j].setStartY(edge1Y[j]);
                            line[j].setEndX(c);
                            line[j].setEndY(edge2Y[j]);
                            line[j].setStroke(Color.rgb(200, 150, 180));
                            line[j].setFill(Color.rgb(200, 150, 180));
                            if (dir == 1){
                                dirCircle = new Circle();
                                double p,q;
                                p = ((12*mouseX[k])+(4*edge1X[j]))/16;
                                q = ((12*mouseY[k])+(4*edge1Y[j]))/16;
                                dirCircle.setCenterX(p);
                                dirCircle.setCenterY(q);
                                dirCircle.setRadius(5);
                                showCircle.getChildren().addAll(line[j],dirCircle);
                            }
                            else showCircle.getChildren().addAll(line[j]);
                            p = 0;
                            j++;
                            t=j;
                            if(dir==0){
                                adj[k][z]=1;
                                adj[z][k]=1;
                            }
                            else {
                                adj[z][k]=1;
                            }
                        }
                    }
                }
            }
        }));
    }

    public void finishNodeAction(ActionEvent event) {
        addnodeButton.setDisable(true);
        finishNodeButton.setDisable(true);
        addedgeButton.setDisable(false);
        finishEdgeButton.setDisable(true);
        temp = 1;
    }

    public void finishEdgeButton(ActionEvent event) {
        tempedge = 1;
        addnodeButton.setDisable(true);
        addedgeButton.setDisable(true);
        finishNodeButton.setDisable(true);
        finishEdgeButton.setDisable(true);
        if(wa==0) completeButton.setDisable(false);
        if(weightedButton.isSelected()) {
            weightLabel.setDisable(false);
            weightInput.setDisable(false);
            weightNext.setDisable(false);
            inputComplete.setDisable(false);
        }
        while (true) {
            int pat = 0, pat1 = -1, pat2 = -1;
            for (temp1 = 0; temp1 < i; temp1++) {
                for (temp2 = 0; temp2 < i; temp2++) {
                    if (temp3[temp1][temp2] == 0 && adj[temp1][temp2] == 1) {
                        pat1 = temp1;
                        pat2 = temp2;
                        pat = 1;
                        temp3[temp1][temp2] = 1;
                        if (dir == 0) temp3[temp2][temp1] = 1;
                        break;
                    }
                }
                if (pat == 1) break;
            }
            if (pat1 != -1 && pat2 != -1)
                weightLabel.getItems().add("Node " + Integer.toString(pat1) + " Node " + Integer.toString(pat2));
            else break;
        }
        weightInput.setAlignment(Pos.CENTER);
    }

    public void completeAction(ActionEvent event) throws IOException {
        AnchorPane menuPane = FXMLLoader.load(getClass().getResource("menuChoice.fxml"));
        plotPane.getChildren().setAll(menuPane);
        //writing the number of nodes to the file
        File file1  = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\I.txt");
        if (!file1.exists()){
            file1.createNewFile();
        }
        PrintWriter printWriter = new PrintWriter(file1);
        int temp;
        temp = i;
        printWriter.println(temp);
        printWriter.close();
        //writing the number of edges to the file
        File file2 = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\J.txt");
        if (!file2.exists()){
            file2.createNewFile();
        }
        printWriter = new PrintWriter(file2);
        temp = t;
        printWriter.println(temp);
        printWriter.close();
        //writing the number of edges to the file
        File edge1XFile  = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\Edge1.txt");
        if (!edge1XFile.exists()){
            try {
                edge1XFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        printWriter = null;
        try {
            printWriter = new PrintWriter(edge1XFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int l=0;l<t;l++){
            printWriter.print(edge1X[l]+" "+edge1Y[l]+" ");
        }
        printWriter.close();
        //writing the number of edges to the file
        File edge2XFile  = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\Edge2.txt");
        if (!edge2XFile.exists()){
            try {
                edge2XFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        printWriter = null;
        try {
            printWriter = new PrintWriter(edge2XFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int l=0;l<t;l++){
            printWriter.print(edge2X[l]+" "+edge2Y[l]+" ");
        }
        printWriter.close();
        //adjacency matrix
        File fileadj = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\Adjacency.txt");
        if(!fileadj.exists()){
            try {
                fileadj.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        printWriter = null;
        try {
            printWriter = new PrintWriter(fileadj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int l=0;l<i;l++) {
            for (int m=0;m<i;m++) {
                printWriter.print(adj[l][m]+" ");
            }
        }
        printWriter.close();
    }

    public void refreshAction(ActionEvent event) throws IOException {
        AnchorPane choicePane = FXMLLoader.load(getClass().getResource("plotGraph.fxml"));
        plotPane.getChildren().setAll(choicePane);
    }

    public void weightNextAction(ActionEvent event) throws FileNotFoundException {
        String text = weightInput.getText();
        weightInput.clear();
        int weight1 = 0;
        if (text!=null)  weight1 = Integer.parseInt(text);
        String selection = weightLabel.getValue();
        StringTokenizer tokenizer = new StringTokenizer(selection," ");
        String[] selected = new String[4];
        int m =0,part1,part2;
        while(tokenizer.hasMoreTokens()){
            selected[m] = tokenizer.nextToken();
            m++;
        }
        part1=Integer.parseInt(selected[1]);
        part2=Integer.parseInt(selected[3]);
        if(dir==0){
            adj[part1][part2]=weight1;
            adj[part2][part1]=weight1;
        }
        else {
            adj[part1][part2]=weight1;
        }
        adj[part1][part2]=weight1;
        if(dir==0) adj[part1][part2]=weight1;
        double midx = (mouseX[part1]+mouseX[part2])/2.0;
        double midY = (mouseY[(part1)]+mouseY[part2])/2.0;
        weightText = new Text();
        weightText.setX(midx-10);
        weightText.setY(midY);
        if (text!=null)  weightText.setText(text);
        else weightText.setText(Integer.toString(1));
        //System.out.println("amar matha"+adj[part1][part2]+" "+adj[part2][part1]);
        showCircle.getChildren().add(weightText);
    }

    public void inputCompleteAction(ActionEvent event) {
        completeButton.setDisable(false);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addnodeButton.setDisable(true);
        addedgeButton.setDisable(true);
        finishNodeButton.setDisable(true);
        finishEdgeButton.setDisable(true);
        completeButton.setDisable(true);
        refreshButton.setDisable(true);
        weightLabel.setDisable(true);
        weightInput.setDisable(true);
        weightNext.setDisable(true);
        inputComplete.setDisable(true);
    }
}