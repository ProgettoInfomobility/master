package org.progettoinfomobility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JApplet;
import javax.swing.UIManager;

/**
 *
 * @author Francesco Toniato 
 * 
 */
public class Start extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Label dh = new Label("Dimensione Header :");
        grid.add(dh, 0, 1);
        
        Label pu = new Label("Porta UP :");
        grid.add(pu, 0, 2);
        
        Label pd = new Label("Porta DOWN :");
        grid.add(pd, 0, 3);
        
        Label pfu = new Label("Porta FFT UP :");
        grid.add(pfu,0, 4);
        
        Label pfd = new Label("Porta FFT DOWN :");
        grid.add(pfd, 0, 5);
        
        
        Spinner<Integer> dh1 = new Spinner<>(32, 2048, 256, 1);
        dh1.editableProperty().setValue(Boolean.TRUE);
        
        
        dh1.focusedProperty().addListener((observable, OldValue, NewValue) -> {
            if(!NewValue){
                dh1.increment(0);
            }
        });
        
        TextField field1 = dh1.getEditor();
        field1.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                Integer.parseInt(newValue);
            }catch(Exception e){
                field1.setText(oldValue);
            }
        });
        grid.add(dh1, 1, 1);
      
        Spinner<Integer> pu1 = new Spinner<>(0, 65525, 10010);
        pu1.editableProperty().setValue(Boolean.TRUE);
        
        pu1.focusedProperty().addListener((observable, OldValue, NewValue) -> {
            if(!NewValue){
                dh1.increment(0);
            }
        });
        
        TextField field2 = pu1.getEditor();
        field2.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                Integer.parseInt(newValue);
            }catch(Exception e){
                field2.setText(oldValue);
            }
        });
        grid.add(pu1, 1, 2);
        
        Spinner<Integer> pd1 = new Spinner<>(0, 65525, 10011);
        pd1.editableProperty().setValue(Boolean.TRUE);
        
        pd1.focusedProperty().addListener((observable, OldValue, NewValue) -> {
            if(!NewValue){
                dh1.increment(0);
            }
        });
        
        TextField field3 = pd1.getEditor();
        field3.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                Integer.parseInt(newValue);
            }catch(Exception e){
                field3.setText(oldValue);
            }
        });
        grid.add(pd1, 1, 3);
        
        Spinner<Integer> pfu1 = new Spinner<>(0, 65525, 10012);
        pfu1.editableProperty().setValue(Boolean.TRUE);
        
        pfu1.focusedProperty().addListener((observable, OldValue, NewValue) -> {
            if(!NewValue){
                dh1.increment(0);
            }
        });
        
        TextField field4 = pfu1.getEditor();
        field4.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                Integer.parseInt(newValue);
            }catch(Exception e){
                field4.setText(oldValue);
            }
        });
        grid.add(pfu1, 1, 4);
        
        Spinner<Integer> pfd1 = new Spinner<>(0, 65525, 10013);
        pfd1.editableProperty().setValue(Boolean.TRUE);
        
        pfd1.focusedProperty().addListener((observable, OldValue, NewValue) -> {
            if(!NewValue){
                dh1.increment(0);
            }
        });
        
        TextField field5 = pfd1.getEditor();
        field5.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                Integer.parseInt(newValue);
            }catch(Exception e){
                field5.setText(oldValue);
            }
        });
        grid.add(pfd1,1, 5);
        
        try{
            FileReader fstream = new FileReader("tmp_data.txt");
            BufferedReader in = new BufferedReader(fstream);
            char b[] = new char[(int)new File("tmp_data.txt").length()];
            in.read(b);
            in.close();
            String s[] = new String(b).split(" ");
            
            dh1.getEditor().setText(s[0]);
            pu1.getEditor().setText(s[1]);
            pd1.getEditor().setText(s[2]);
            pfu1.getEditor().setText(s[3]);
            pfd1.getEditor().setText(s[4]);
        }catch(Exception ex){
            
        }
        
        Button ann = new Button("Quit");
        Button ok = new Button("Next");
        
        HBox hb = new HBox(10);
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.getChildren().addAll(ann,ok);
        grid.add(hb, 1, 7);
        
        
        ok.setOnAction(new EventHandler<ActionEvent>(){    
            @Override
            public void handle(ActionEvent event) {                
                   try{
                        FileWriter fstream = new FileWriter("tmp_data.txt");
                        BufferedWriter out = new BufferedWriter(fstream);
                        out.write(dh1.getEditor().getText() + " " + pu1.getEditor().getText() + " " + pd1.getEditor().getText() + " " + pfu1.getEditor().getText() + " " + pfd1.getEditor().getText());
                        out.close();
                        
                        try {
                            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                        } catch (Exception e) {}
                        JApplet applet = new Grafica();
                        applet.init();
                        applet.start();
                        
                        Server s1 = new Server(Integer.parseInt(dh1.getEditor().getText()), Integer.parseInt(pu1.getEditor().getText()), Grafica.coda1);
                        s1.start();
                        
                        Server s2 = new Server(Integer.parseInt(dh1.getEditor().getText()), Integer.parseInt(pd1.getEditor().getText()), Grafica.coda2);
                        s2.start();
                        
                        Server s3 = new Server(Integer.parseInt(dh1.getEditor().getText()), Integer.parseInt(pfu1.getEditor().getText()), Grafica.coda3);
                        s3.start();
                        
                        Server s4 = new Server(Integer.parseInt(dh1.getEditor().getText()), Integer.parseInt(pfd1.getEditor().getText()), Grafica.coda4);
                        s4.start();
                        
                        primaryStage.close();
                  }catch (IOException e){
                  System.err.println("Error: " + e.getMessage());
                  }
              
            }
        });
        
        ann.setOnAction(new EventHandler<ActionEvent>(){    
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 500, 300);
        
        primaryStage.setTitle("Radar Monitor");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}


