package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private InputsController inputsController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Inputs.fxml"));
        primaryStage.setTitle("Inputs");
        primaryStage.setScene(new Scene(root, 1400, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}