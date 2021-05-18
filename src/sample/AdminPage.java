package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.SplittableRandom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label notTour;

    @FXML
    private Button signIn;

    @FXML
    private Button back;

    @FXML
    private TableView<Request> table;

    @FXML
    private TableColumn<Request, String> nameTour;

    @FXML
    private TableColumn<Request, Integer> price;

    @FXML
    private TableColumn<Request, Integer> numOfAdult;

    @FXML
    private TableColumn<Request, Integer> numOfChildren;

    @FXML
    private TableColumn<Request, String> clientName;

    @FXML
    private TableColumn<Request, Integer> phoneNumber;

    @FXML
    private TableColumn<Request, Integer> requestId;

    @FXML
    private TableColumn<Request, Integer> tourId;

    @FXML
    private TableColumn<Request, String> status;

    @FXML
    private TextField idTextField;

    @FXML
    private Button changeBtn;

    @FXML
    private ChoiceBox<String> statusChoice;

    @FXML
    private Label noRequest;


    @FXML
    void initialize() {
        showTable();
        backLight(signIn);
        backLight(back);
        signIn.setOnAction(event -> showTable());
        back.setOnAction(event -> NewScene("fxmlPages/MainPage.fxml"));
        fillChoiceBox(statusChoice);
        changeBtn.setOnAction(event -> newStatus());


    }
    public void showTable() {
        CLDAO_Request cldao_request=new CLDAO_Request();
        ObservableList<Request> requestsInfo= FXCollections.observableArrayList(cldao_request.findAll());
        nameTour.setCellValueFactory(new PropertyValueFactory<Request,String>("nameTour"));
        price.setCellValueFactory(new PropertyValueFactory<Request,Integer>("price"));
        numOfAdult.setCellValueFactory(new PropertyValueFactory<Request,Integer>("numOfAdult"));
        numOfChildren.setCellValueFactory(new PropertyValueFactory<Request,Integer>("numOfChildren"));
        clientName.setCellValueFactory(new PropertyValueFactory<Request,String>("clientName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<Request,Integer>("phoneNumber"));
        requestId.setCellValueFactory(new PropertyValueFactory<Request,Integer>("requestId"));
        tourId.setCellValueFactory(new PropertyValueFactory<Request, Integer>("tourId"));
        status.setCellValueFactory(new PropertyValueFactory<Request,String>("status"));
        table.setItems(requestsInfo);

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
    public void  newStatus() {
        int num=Integer.parseInt(idTextField.getText());

        Request request=new Request();
        ArrayList<Request> requests=new ArrayList<>(cldao_request.findAll());
        for (int i=0;i<requests.size();i++) {
            if(requests.get(i).getRequestId()==num){
                request=requests.get(i);
                request.setStatus(statusChoice.getValue());
                noRequest.setVisible(false);
                cldao_request.update(request);
            }
            else {noRequest.setVisible(true);}
        }


    }
    public void fillChoiceBox(ChoiceBox<String> choise) {
        choise.getItems().add("accepted");
        choise.getItems().add("call back");
        choise.getItems().add("rejected");
    }
    CLDAO_Request cldao_request=new CLDAO_Request();
    CLDAO_Tour cldao_tour=new CLDAO_Tour();
}
