package client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShowGraphController {

    @FXML
    public Button showButton;

    public ShowGraphController(){
    }
    @FXML
    public AnchorPane GraphShow;
    private int i=0,j=0,l=0;
    private double[] edges1 = new double[5050];
    private double[] edges2 = new double[5050];
    private Circle[] circle = new Circle[100];
    private Text[] text = new Text[100];
    private Line[] line = new Line[5050];
    private int nodes=0,edges = 0,dir=0,wa=0;
    private int[][] adj=new int[5050][5050];
    private Circle dirCircle ;
    private Text weightText=new Text();
    @FXML
    public void loadnewGraph() throws FileNotFoundException {
        //reading value of i from the file
        GraphShow.getChildren().removeAll(circle);
        GraphShow.getChildren().removeAll(text);
        GraphShow.getChildren().removeAll(line);
        File file = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\I.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextInt()) {
            nodes = scanner.nextInt();
        }
        //reading value of j from the file
        File jfile = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\J.txt");
        scanner = new Scanner(jfile);
        while (scanner.hasNextInt()) {
            edges = scanner.nextInt();
        }
        file = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\dir.txt");
        scanner = new Scanner(file);
        while (scanner.hasNextInt()) {
            dir = scanner.nextInt();
            System.out.println(dir);
        }
        file = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\wa.txt");
        scanner = new Scanner(file);
        while (scanner.hasNextInt()) {
            wa = scanner.nextInt();
        }
        //reading centers of circle from the file
        File fileCenter = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\Circle Center.txt");
        Scanner scannerCenter = new Scanner(fileCenter);
        String[] strings = new String[200];
        double[] centers = new double[200];
        int stringLength = 0;
        while (scannerCenter.hasNext()) {
            String sampleString = scannerCenter.nextLine();
            strings = sampleString.split("\\s+");
            stringLength = strings.length;
            for (l=0; l < stringLength; l++) {
                String numString = strings[l];
                centers[l] = Double.parseDouble(numString);
            }
            for (i = 0; i < nodes; ) {
                for (l = 0; l < stringLength-2; l += 2) {
                    circle[i] = new Circle();
                    text[i] = new Text();
                    circle[i].setCenterX(centers[l]);
                    circle[i].setCenterY(centers[l + 1]);
                    circle[i].setRadius(20.0f);
                    circle[i].setFill(Color.rgb(200, 150, 180));
                    text[i].setX(centers[l] - 20);
                    text[i].setY(centers[l + 1] - 30);
                    String s = Integer.toString(i);
                    s = "Node " + s;
                    text[i].setText(s);
                    GraphShow.getChildren().addAll(circle[i],text[i]);
                    //GraphShow.getChildren().addAll(circle[i], text[i]);
                    i++;
                }
            }
        }
        //reading edge1 from file
        File fileEdge1 = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\Edge1.txt");
        Scanner edgeScanneer = new Scanner(fileEdge1);
        String[] edgeStrings = new String[200];
        while(edgeScanneer.hasNext()) {
            String sampleString = edgeScanneer.nextLine();
            edgeStrings = sampleString.split("\\s+");
            stringLength = edgeStrings.length;
            for (l = 0; l < stringLength; l++) {
                String numString = edgeStrings[l];
                edges1[l] = Double.parseDouble(numString);
            }
        }
        //reading edge2 coordinates from file
        File fileEdge2 = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\Edge2.txt");
        Scanner edge2Scanneer = new Scanner(fileEdge2);
        String[] edge2Strings = new String[200];
        while(edge2Scanneer.hasNext()){
            String sampleString = edge2Scanneer.nextLine();
            edge2Strings = sampleString.split("\\s+");
            stringLength = edge2Strings.length;
            for (l=0; l < stringLength; l++) {
                String numString = edge2Strings[l];
                edges2[l] = Double.parseDouble(numString);
            }
        }
        for (j=0;j<edges;){
            for(l=0;l<stringLength;l+=2){
                line[j] = new Line();
                line[j].setStartX(edges1[l]);
                line[j].setStartY(edges1[l+1]);
                line[j].setEndX(edges2[l]);
                line[j].setEndY(edges2[l+1]);
                line[j].setStrokeWidth(2);
                line[j].setStroke(Color.rgb(200, 150, 180));
                line[j].setFill(Color.rgb(200, 150, 180));
                GraphShow.getChildren().addAll(line[j]);
                j++;
            }
        }
        File fileadj = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\Adjacency.txt");
        Scanner adjScanner = new Scanner(fileadj);
        String[] adjStrings = new String[200];
        while(adjScanner.hasNext()){
            String sampleString = adjScanner.nextLine();
            //System.out.println(sampleString);
            adjStrings = sampleString.split("\\s+");
            stringLength = adjStrings.length;
            int n=0,m=0;
            for (l=0; l < stringLength; l++) {
                String numString = adjStrings[l];
                adj[n][m] = Integer.parseInt(numString);
                //System.out.println(adj[n][m]);
                m++;
                if(m>=Math.sqrt(stringLength)){
                    m=0;n++;
                }
            }
        }
        if(dir==1){
            //System.out.println("hijibiji"+dir);
            for(int temoloop=0;temoloop<nodes;temoloop++){
                for(int temloop2=0;temloop2<nodes;temloop2++){
                    if(adj[temoloop][temloop2]!=0){
                        dirCircle = new Circle();
                        double p,q;
                        p = ((4*centers[2*temoloop])+(12*centers[2*temloop2]))/16;
                        q = ((4*centers[2*temoloop+1])+(12*centers[2*temloop2+1]))/16;
                        dirCircle.setCenterX(p);
                        dirCircle.setCenterY(q);
                        dirCircle.setRadius(5);
                        GraphShow.getChildren().add(dirCircle);
                    }
                }
            }
        }
        if(wa==1){
            for(int temploop=0;temploop<nodes;temploop++){
                for(int temloop2=0;temloop2<nodes;temloop2++){
                    if(adj[temploop][temloop2]!=0){
                        double midx = ((centers[2*temploop])+(centers[2*temloop2]))/2;
                        double midY = ((centers[2*temploop+1])+(centers[2*temloop2+1]))/2;
                        weightText = new Text();
                        weightText.setX(midx-10);
                        weightText.setY(midY-10);
                        weightText.setText(Integer.toString(adj[temploop][temloop2]));
                        //System.out.println("amar matha"+adj[part1][part2]+" "+adj[part2][part1]);
                        GraphShow.getChildren().add(weightText);
                    }
                }
            }
        }
    }
}
