package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FourthIdDetails {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView mainImg1;

    @FXML
    private Button requestBtn;

    @FXML
    private Button backBtn;

    @FXML
    void initialize() {
        backBtn.setOnAction(event -> NewScene("fxmlPages/MainPage.fxml"));
        backLight(backBtn);
        backLight(requestBtn);
        requestBtn.setOnAction(event -> NewScene("fxmlPages/RequestPage.fxml"));
    }
    public  void NewScene(String string) {
        Stage str=(Stage) backBtn.getScene().getWindow();
        str.hide();
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource(string));
            str.setTitle("Travel agency");
            Scene scene=new Scene(root);
            str.setScene(scene);
            str.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void backLight(Button btn) {
        btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn.setStyle("-fx-background-color: #20b2aa; -fx-background-radius: 30px");
            }
        });
        btn.setOnMouseExited(event-> btn.setStyle("-fx-background-color: f5f5dc"));
    }
}
