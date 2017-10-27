package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.CalculatedExpression;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    protected Button button;
    @FXML
    protected TextField exp;
    @FXML
    protected TextField ans;
    @FXML
    protected TableView<CalculatedExpression> table;
    private ObservableList<CalculatedExpression> data = FXCollections.observableArrayList();
    @FXML
    protected TableColumn<CalculatedExpression, String> exps;
    @FXML
    protected TableColumn<CalculatedExpression, String> anss;

    ClientSide client;

    @FXML
    protected void close(ActionEvent e) throws IOException {
        client.getSocket().close();
    }

    @FXML
    protected void act(ActionEvent e){
        String fromServer = "";
        ans.clear();

        //NET TEST HERE ...

        try {
            //------------------ CLIENT >> SERVER :SEND TO CAL , ret ANS/ERROR

            //send EXP to SERVER
            client.connect();
            fromServer = client.contactToServer(exp.getText());
            //fromServer = cal(exp.getText());
            //wait SIGNAL from SERVER

            //if OK >> add to TABLE
            if (fromServer.equals("error")) {
                //IF ERROR >> display DIALOG
                parseHandle();
            } else {
                ans.setText(fromServer);
                data.add(0, new CalculatedExpression(exp.getText() ,ans.getText()));
                table.setItems(data);
            }


        } catch (IOException e1) {
            //e1.printStackTrace();
            connectHandle();
        }



    }

    //--------------------------------------------------------- CLIENT SITE:ALERT
    private void connectHandle() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Connection Error");
        alert.setHeaderText("Can't Connect to Server");
        alert.setContentText("please ensure the server is online");
        alert.showAndWait();
    }
    //----------------------------------------------------------

    //--------------------------------------------------------- CLIENT SITE:ALERT
    private void parseHandle() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Expression Error");
        alert.setHeaderText("Please check your expression");
        alert.setContentText("according to mathematical expression");
        alert.showAndWait();
    }
    //----------------------------------------------------------

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exps.setCellValueFactory(new PropertyValueFactory<CalculatedExpression, String>("exp"));
        anss.setCellValueFactory(new PropertyValueFactory<CalculatedExpression, String>("ans"));
        int port = 6789;
        client = new ClientSide( "localhost", port);;
    }
}
