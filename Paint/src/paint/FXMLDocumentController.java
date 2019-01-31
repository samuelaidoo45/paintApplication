package paint;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
    
    @FXML
   private ColorPicker colorpicker;
    
    @FXML
    private TextField bsize;
    
    @FXML
    private Canvas canvas;
    
    boolean toolSelected = false;
    
    GraphicsContext brushTool;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        brushTool = canvas.getGraphicsContext2D();
         canvas.setOnMouseDragged(e -> {
             double size = Double.parseDouble(bsize.getText());
             double x = e.getX() - size /2;
             double y = e.getY() - size /2;
             
             if(toolSelected && !bsize.getText().isEmpty()){
                 brushTool.setFill(colorpicker.getValue());
                 brushTool.fillRoundRect(x,y,size,size,size,size);
             }
         });
    }    
    
    @FXML
    public void toolSelected(ActionEvent e){
        toolSelected = true;
    }
    
    @FXML
    public void newcanvas(ActionEvent e){
        TextField getCanvasWidth = new TextField();
        getCanvasWidth.setPromptText("Width");
        getCanvasWidth.setPrefWidth(150);
        getCanvasWidth.setAlignment(Pos.CENTER);
        
        TextField getCanvasheight = new TextField();
        getCanvasheight.setPromptText("Height");
        getCanvasheight.setPrefWidth(150);
        getCanvasheight.setAlignment(Pos.CENTER);
        
        Button createButton = new Button();
        createButton.setText("Create Canvas");
        
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(getCanvasWidth,getCanvasheight,createButton);
        
        Stage createStage  = new Stage();
        AnchorPane root = new AnchorPane();
        root.setPrefWidth(200);
        root.setPrefHeight(200);
        root.getChildren().add(vBox);
        
        Scene getDimension = new Scene(root);
        createStage.setTitle("Create Canvas");
        createStage.setScene(getDimension);
        createStage.show();
        
        
        createButton.setOnAction(new  EventHandler<ActionEvent>(){;
            @Override
            public void handle(ActionEvent event) {
                double canvasWidthReceived = Double.parseDouble(getCanvasWidth.getText());
                double canvasHeightReceived = Double.parseDouble(getCanvasheight.getText());
                canvas = new Canvas();
                canvas.setWidth(canvasWidthReceived);
                canvas.setHeight(canvasHeightReceived);
                vBox.getChildren().add(canvas);
                createStage.close();
            }
                
                });
        
        
        
        
        
    }
}
