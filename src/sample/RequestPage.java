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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RequestPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label notTour;

    @FXML
    private Button goBtn;

    @FXML
    private TextField numAdult;

    @FXML
    private TextField numChildren;

    @FXML
    private TextField personName;

    @FXML
    private TextField phoneNum;

    @FXML
    private ChoiceBox<String> nameTour;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        fillChoiceBox(nameTour);
        backLight(back);
        backLight(goBtn);
        back.setOnAction(event -> NewScene("fxmlPages/MainPage.fxml"));
        goBtn.setOnAction(event -> addRequest());
    }
    public void addRequest() {
        Tour tour=new Tour();
        System.out.println("1");
        tour=cldao_tour.foundForName(nameTour.getValue()).get(0);
        System.out.println("2");
        CLDAO_Request cldao_request=new CLDAO_Request();
        Request request=new Request(nameTour.getValue(),tour.getPrice(),Integer.parseInt(numAdult.getText()),Integer.parseInt(numChildren.getText()),personName.getText(),Integer.parseInt(phoneNum.getText()),tour.getId(),"new");
        System.out.println(request);
        cldao_request.create(request);
        notTour.setVisible(true);
        numAdult.clear();
        numChildren.clear();
        personName.clear();
        phoneNum.clear();
    }
    public void fillChoiceBox(ChoiceBox<String> choise) {

        ArrayList<Tour> arrayList=new ArrayList<>(cldao_tour.findAll());
        for(int i=0;i<arrayList.size();i++) {
            choise.getItems().add(arrayList.get(i).getName());
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
    CLDAO_Tour cldao_tour=new CLDAO_Tour();

}
