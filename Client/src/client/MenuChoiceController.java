package client;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MenuChoiceController {

    public MenuChoiceController() {
    }

    static ShowGraphController scg;
    public ImageView imageview;
    @FXML
    private Button backButton;
    @FXML
    private Button selectButton;
    @FXML
    public Button showGraph1;
    @FXML
    private RadioButton minimumbox = new RadioButton();
    @FXML
    private RadioButton bitbox = new RadioButton();
    @FXML
    private RadioButton mstbox = new RadioButton();
    @FXML
    private RadioButton dfsbox = new RadioButton();
    @FXML
    private RadioButton bfsbox = new RadioButton();
    @FXML
    private RadioButton maxbox = new RadioButton();
    @FXML
    public RadioButton djbox;

    public ToggleGroup choiceGroup;

    public AnchorPane showGraph;
    public AnchorPane choicePane;

    double mouseX[] = new double[100];
    double mouseY[] = new double[100];
    int i=0,j=0,l=0;
    private double[] edges1 = new double[5050];
    private double[] edges2 = new double[5050];
    private Circle[] circle = new Circle[100];
    private Circle dirCircle ;
    private Text[] text = new Text[100];
    private Line[] line = new Line[5050];
    private Line[] line2=new Line[5050];
    private Text end = new Text();
    private Text complete = new Text();
    private Text add = new Text();
    private int nodes,edges = 0,tempo2=1,tempo1=0,tempo00=0,tempo01=0,tempo02=0,jos=0,lolo=2,plolo=0,dlolo=1,done=0,fol=0,dir=0,wa=0;
    int adj[][]=new int[5050][5050];
    private Text weightText=new Text();

    @FXML
    public void backAction(ActionEvent event) throws IOException {
        AnchorPane welcomePane = FXMLLoader.load(getClass().getResource("plotGraph.fxml"));
        choicePane.getChildren().setAll(welcomePane);
    }
    @FXML
    public void selectionAction(ActionEvent event) throws IOException {
        loadNewScene();
    }

    public void loadNewScene() throws FileNotFoundException {
        if (bfsbox.isSelected() || dfsbox.isSelected() || maxbox.isSelected() || bitbox.isSelected()
                || djbox.isSelected() || mstbox.isSelected()){//||showGraph1.isSelected()) {
            showGraph.getChildren().removeAll(circle);
            showGraph.getChildren().removeAll(text);
            Image image = new Image("client/algobg.jpg");
            imageview.setImage(image);
            showGraph.getChildren().removeAll(complete,end,add);
            //reading value of i from the file
            File file = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\I.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextInt()) {
                nodes = scanner.nextInt();
            }
            //reading value of j from the file
            file = new File("E:\\L1T2\\cse108\\project\\Algo Simulator\\Algo Simulator\\Client\\J.txt");
            scanner = new Scanner(file);
            while (scanner.hasNextInt()) {
                edges = scanner.nextInt();
            }

            for(int removetemp=0;removetemp<edges;removetemp++) showGraph.getChildren().remove(line[removetemp]);
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
                        showGraph.getChildren().addAll(circle[i], text[i]);
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
                    showGraph.getChildren().addAll(line[j]);
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

            Button playButton = new Button("Play");
            playButton.setFont(Font.font("Arial",18));
            playButton.setLayoutX(1400);
            playButton.setLayoutY(900);
            playButton.setMinHeight(50);
            playButton.setMinWidth(70);
            playButton.setId("animPlay");
            showGraph.getChildren().add(playButton);
            for(int loop = 0;loop<nodes;loop++){
                showGraph.getChildren().remove(line2[loop]);
            }
           // System.out.println("hijibiji"+dir);
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
                            showGraph.getChildren().add(dirCircle);
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
                            showGraph.getChildren().add(weightText);
                        }
                    }
                }
            }
            if(bfsbox.isSelected()) {
                System.out.println("Edge "+j);
                AnimBfs a = new AnimBfs(nodes,j,adj);
                int[] temp = a.bfskoro();
                for(int mor=0;mor<temp.length;mor++)
                    System.out.println("BFS"+mor);
                i = 0;
                playButton.setOnAction(e -> {
                    if (i<=nodes) {
                        Timeline timeline = new Timeline();
                        timeline.setCycleCount(1);
                        timeline.setAutoReverse(false);
                        KeyValue kv1 = new KeyValue(circle[temp[i]].fillProperty(), Color.BLUE);
                        KeyFrame kf1 = new KeyFrame(Duration.millis(300), kv1);
                        timeline.getKeyFrames().add(kf1);
                        timeline.getDelay();
                        timeline.play();
                        i++;
                    }
                    else{
                        showGraph.getChildren().remove(complete);
                        complete.setX(500);
                        complete.setY(950);
                        complete.setFont(Font.font("Aerial",24));
                        complete.setText("BFS Simulation Completed");
                        showGraph.getChildren().add(complete);
                        System.out.println("BFS Completed");
                    }
                });
            }
            else if(dfsbox.isSelected()){
                AnimDfs b = new AnimDfs(nodes,j,adj);
                b.dfskoro(adj,0);
                int[] temp=b.getd();
                i = 0;
                playButton.setOnAction(e -> {
                    if (i<nodes) {
                        Timeline timeline = new Timeline();
                        timeline.setCycleCount(1);
                        timeline.setAutoReverse(false);
                        KeyValue kv1 = new KeyValue(circle[temp[i]].fillProperty(), Color.BLUE);
                        KeyFrame kf1 = new KeyFrame(Duration.millis(300), kv1);
                        timeline.getKeyFrames().add(kf1);
                        timeline.getDelay();
                        timeline.play();
                        i++;
                    }
                    else{
                        showGraph.getChildren().remove(complete);
                        complete.setX(500);
                        complete.setY(950);
                        complete.setFont(Font.font("Aerial",24));
                        complete.setText("DFS Simulation Completed");
                        showGraph.getChildren().add(complete);
                        System.out.println("DFS Completed");
                    }
                });
            }
            else if(mstbox.isSelected())
            {
                AnimMST c = new AnimMST(nodes,j,adj);
                c.primMST();
                int[][] msttemp=c.getRet();
                //System.out.println("Hibijibi");
                for(int tempo=1;tempo<nodes;tempo++)
                {
                    System.out.println(tempo+" "+msttemp[tempo][0]+" "+msttemp[tempo][1]);
                }
                tempo2 = 1;
                playButton.setOnAction(e -> {
                    line2[tempo2-1] = new Line();
                    double ab=0,cd=0,ef=0,gh=0;
                    if(tempo2<nodes) {
                        ab = circle[tempo2].getCenterX();
                        cd = circle[tempo2].getCenterY();
                        ef = circle[msttemp[tempo2][0]].getCenterX();
                        gh = circle[msttemp[tempo2][0]].getCenterY();
                        System.out.println(ab + ' ' + cd);
                        line2[tempo2 - 1].setStartX(ab);
                        line2[tempo2 - 1].setStartY(cd);
                        line2[tempo2 - 1].setEndX(ef);
                        line2[tempo2 - 1].setEndY(gh);
                        line2[tempo2 - 1].setStrokeWidth(5);
                        line2[tempo2-1].setStroke(Color.rgb(200, 150, 180));
                        line2[tempo2 - 1].setFill(Color.rgb(200, 150, 180));
                        showGraph.getChildren().addAll(line2[tempo2 - 1]);
                        tempo2++;
                    }
                    else{
                        showGraph.getChildren().remove(complete);
                        complete.setX(500);
                        complete.setY(950);
                        complete.setFont(Font.font("Aerial",24));
                        complete.setText("MST Simulation Completed");
                        showGraph.getChildren().add(complete);
                        System.out.println("MST Completed");
                    }
                });
            }
            else if(bitbox.isSelected())
            {

                tempo2=0;
                tempo00=0;
                tempo01=0;
                tempo02=0;
                int[] temp6=new int[nodes];
                temp6[0]=0;
                jos=0;
                tempo2++;
                tempo01++;
                plolo=0;
                done=0;
                int[] temp7=new int[nodes];
                int[] temp8=new int[nodes];
                temp8[0]=1;
                AnimBip d=new AnimBip(nodes,adj,0);
                boolean temp=d.isBipartite();

                if(temp)
                {
                    playButton.setOnAction(e -> {
                        tempo2=0;
                        for(int lol=0;lol<nodes;lol++){
                            if(temp8[lol]==1){
                                tempo2++;
                            }
                        }
                        if(tempo2==nodes){
                            plolo=1;
                        }

                        if(done==1){
                            jos=1;
                            showGraph.getChildren().remove(complete);
                            complete.setX(500);
                            complete.setY(950);
                            complete.setFont(Font.font("Aerial",24));
                            complete.setText("Simulation Completed");
                            showGraph.getChildren().add(complete);
                            System.out.println("Bipertite Completed");

                        }
                        if(tempo00==0&&jos==0)
                        {
                            tempo00=1;
                            if(plolo==1){
                                done=1;
                            }
                            for(int lol=0;lol<tempo01;lol++)
                            {
                                circle[temp6[lol]].setFill(Color.RED);
                                for(int lol1=0;lol1<nodes;lol1++)
                                {
                                    if(adj[temp6[lol]][lol1]!=0&&temp8[lol1]!=1){
                                        temp8[lol1]=1;
                                        tempo2++;
                                        temp7[tempo02]=lol1;
                                        tempo02++;
                                        System.out.println(tempo2);
                                    }
                                }
                            }
                            tempo01=0;
                        }
                        else if(jos==0){
                            tempo00=0;
                            if(plolo==1){
                                done=1;
                            }
                            for(int lol=0;lol<tempo02;lol++)
                            {
                                circle[temp7[lol]].setFill(Color.BLUE);
                                for(int lol1=0;lol1<nodes;lol1++)
                                {
                                    if(adj[temp7[lol]][lol1]!=0&&temp8[lol1]!=1){
                                        temp8[lol1]=1;
                                        tempo2++;
                                        temp6[tempo01]=lol1;
                                        tempo01++;
                                        System.out.println(tempo2);
                                    }
                                }
                            }
                            tempo02=0;
                        }
                    });
                }
                else {
                    showGraph.getChildren().remove(complete);
                    complete.setX(500);
                    complete.setY(950);
                    complete.setFont(Font.font("Aerial",24));
                    complete.setText("Not a Bipartite Graph");
                    showGraph.getChildren().add(complete);
                    System.out.println("Not bip.");
                }
            }
            else if (maxbox.isSelected()){
                AnimMaxFlow f=new AnimMaxFlow();
                int[][] tempo9=f.fordFulkerson(adj,0,nodes-1,nodes);
                playButton.setOnAction(e -> {
                    if (lolo<tempo9[0][0]) {
                        fol++;
                        for (int moro = 0; moro < nodes; moro++) {
                            circle[moro].setFill(Color.rgb(200, 150, 180));
                        }
                        //System.out.println("fvuvbfuhv ");
                        for (int moro = 0; moro <= tempo9[0][lolo-1]; moro++) {
                            if(moro==tempo9[0][lolo-1]) {
                                //System.out.println("ngjbgr"+tempo9[1][lolo-2]);
                            }
                            else {
                                showGraph.getChildren().remove(add);
                                circle[tempo9[lolo][moro]].setFill(Color.RED);
                                add.setX(1300);add.setY(300);
                                String p = String.valueOf(tempo9[lolo][moro]);
                                add.setText("Max Flow: "+p);
                                add.setFont(Font.font("Aerial",24));
                                showGraph.getChildren().add(add);
                                //System.out.println(tempo9[lolo][moro]);
                            }
                        }
                        lolo++;
                        //System.out.println("lol"+lolo);
                    }
                    else{
                        showGraph.getChildren().remove(complete);
                        complete.setX(500);
                        complete.setY(950);
                        complete.setFont(Font.font("Aerial",24));
                        complete.setText("Max Flow Simulation Completed");
                        showGraph.getChildren().add(complete);
                        System.out.println("Max Completed");
                    }
                });
            }
            else if(djbox.isSelected()){
                dlolo=0;
                AnimDijk g=new AnimDijk();
                int[] temp10=g.dijkstra(adj,0,nodes);
                playButton.setOnAction(e -> {
                    if(dlolo<nodes){
                        showGraph.getChildren().remove(add);
                        circle[dlolo].setFill(Color.RED);
                        add.setX(1300);add.setY(300);
                        String p = String.valueOf(temp10[dlolo]);
                        add.setText("Dist.: "+p);
                        showGraph.getChildren().add(add);
                        dlolo++;
                    }
                    else{
                        showGraph.getChildren().remove(complete);
                        complete.setX(500);
                        complete.setY(950);
                        complete.setFont(Font.font("Aerial",24));
                        complete.setText("Simulation Completed");
                        showGraph.getChildren().add(complete);
                        System.out.println("djk. Completed");
                    }
                });
            }
        }
    }

    public void showGraphAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("showGraph.fxml"));
        stage.setTitle("Show Graph");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
