/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scannerproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import static scannerproject.ScanLogic.ShowTokens;
import static scannerproject.ScanLogic.scanCode;

/**
 *
 * @author AnooD
 */
public class ScannerProject extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
          VBox tokens = new VBox();
       VBox type = new VBox();
       HBox alloutput = new HBox();
              HBox allbuttons = new HBox();

        
        
        Button button1 = new Button("Scan");
        Button button2 = new Button("Clear");
        
        
        
        allbuttons.getChildren().addAll(button1,button2);
allbuttons.setSpacing(40);
        
        button1.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
        button2.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));

       
        Label label= new Label("Enter Your Code Here:");
        label.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        label.setTextFill(Color.BLACK);
        TextArea code= new TextArea();
        code.setPromptText(" example:  int num1 = 5, num2 = 15, sum;\n + sum = num1 + num2;");
        code.setWrapText(true);
        Label label2 = new Label("Output:");
        label2.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        label2.setTextFill(Color.BLACK);
        
              Label z= new Label("Token Value");       
         z.setStyle("-fx-border-color: black;");     
          
         z.setMaxHeight(10);
         z.setMaxWidth(250);
         z.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
          z.setTextFill(Color.BLACK);
          
           Label y= new Label("Token Type");       
         y.setStyle("-fx-border-color: black;");   
           y.setMaxHeight(10);
           y.setMaxWidth(250);
            
               y.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
               y.setTextFill(Color.BLACK);
      
        
     
        
  
        button1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                  scanCode(code.getText());
                 
               tokens.getChildren().add(z);
                         type.getChildren().add(y);


        for (int i = 0; i < ShowTokens().size(); i++) {
            
          Label x= new Label(ShowTokens().get(i).getKey());
             
           tokens.getChildren().add(x);
           x.setMaxHeight(10);
           x.setMaxWidth(250);
           x.setStyle("-fx-border-color: black;");
          
               x.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
               x.setTextFill(Color.BLACK);
      


           
        }
        for (int i = 0; i < ShowTokens().size(); i++) {
            
              Label x= new Label(ShowTokens().get(i).getValue());
                x.setMaxHeight(10);
           x.setMaxWidth(250);

              x.setStyle("-fx-border-color: black;");
               x.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
               x.setTextFill(Color.BLACK);
      
              
           type.getChildren().add(x);

            }
            }
        });
        
        
         button2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                type.getChildren().clear();
                tokens.getChildren().clear();
                code.clear();
                
                
                
                
            }
         });
        
     
       
       alloutput.getChildren().addAll(tokens,type);
       
       GridPane mypane = new GridPane();

     mypane.addColumn(0,label, code,allbuttons);
     mypane.addColumn(1,label2,alloutput);
     

        
        mypane.setHgap(10);
        mypane.setVgap(10);
        
        
        Scene scene = new Scene(mypane, 1200, 400);
          
        primaryStage.setTitle("Scanner");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
