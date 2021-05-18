package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminSignInPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label notTour;

    @FXML
    private Button signIn;

    @FXML
    private TextField loginField;

    @FXML
    private Button back;

    @FXML
    private Label incorrectLog;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        backLight(back);
        backLight(signIn);
        signIn.setOnAction(event -> adminSignIn());
        back.setOnAction(event -> NewScene("fxmlPages/MainPage.fxml"));
    }
    public void adminSignIn() {
        CLDAO_Admin cldao_admin=new CLDAO_Admin();
        ArrayList<Admin> admins=new ArrayList<>(cldao_admin.findAll());
        Admin checkAdmin=new Admin();
        for(int i=0;i<admins.size();i++) {
            checkAdmin=admins.get(i);
            if(loginField.getText().equals(checkAdmin.getLogin())&&passwordField.getText().equals(checkAdmin.getPassword())) {
                NewScene("fxmlPages/AdminPage.fxml");
            }
            else {
                loginField.clear();
                passwordField.clear();
                incorrectLog.setVisible(true);
            }
        }
    }
    public  void NewScene(String string) {
        Stage str=(Stage) back.getScene().getWindow();
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
