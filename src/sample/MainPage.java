package sample;

import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import sample.Tour;

public class MainPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button leftBtn;

    @FXML
    private Button rightBtn;

    @FXML
    private Label fromDateBtn;

    @FXML
    private Label toDateBtn;

    @FXML
    private Label priceLabl;

    @FXML
    private ImageView mainImg;

    @FXML
    private Label nameLbl;

    @FXML
    private Button detailsBtn;

    @FXML
    private Button AdminSingIn;

    @FXML
    void initialize() {

    window(count);
    leftBtn.setOnAction(event -> nextWindow());
    rightBtn.setOnAction(event -> prevWindow());
    backLight(detailsBtn);
    detailsBtn.setOnAction(event -> NewScene());
    AdminSingIn.setOnAction(event -> NewSceneStr("fxmlPages/AdminSignInPage.fxml"));

    }
    int count=1;
    CLDAO_Tour cldao_tour=new CLDAO_Tour();
    public void window(int count) {
        Tour tour=new Tour();

        tour=cldao_tour.foundForId(count).get(0);
        nameLbl.setText(cldao_tour.foundForId(count).get(0).getName());
        priceLabl.setText(String.valueOf(tour.getPrice()));
        fromDateBtn.setText(tour.getFromDate());
        toDateBtn.setText(tour.getToDate());
        Class<?> clazz=this.getClass();
        InputStream inputStream=clazz.getResourceAsStream(tour.getUrl());
        Image image=new Image(inputStream);
        mainImg.setImage(image);
        mainImg.setVisible(true);
        toDateBtn.setVisible(true);
        nameLbl.setVisible(true);
        priceLabl.setVisible(true);
        fromDateBtn.setVisible(true);

    }
    public void nextWindow() {
        if(count<cldao_tour.findAll().size()) {
            count++;
            window(count);
        }
        else {
            count=1;
            window(count);
        }
    }
    public void prevWindow() {
        if(count>1){
            count--;
            window(count);
        }
        else{
            count=cldao_tour.findAll().size();
            window(count);
        }

    }
    public void NewSceneStr(String string) {
        Stage str=(Stage) rightBtn.getScene().getWindow();
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
    public  void NewScene() {
        String string="fxmlPages/"+count+"idDetails.fxml";
        Stage str=(Stage) detailsBtn.getScene().getWindow();
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
}
