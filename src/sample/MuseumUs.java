package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javax.swing.text.View;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class MuseumUs {

    @FXML
    private Button BupdVist;
    @FXML
    private Button BupdEx;
    @FXML
    private Button Bshow;
    @FXML
    private Button BShowEx;
    @FXML
    private ListView ListOfExp;
    @FXML
    private ListView ListOfVist;
    @FXML
    private TextArea ExpInfo;

    @FXML
    void initialize() throws SQLException {
        DataBHM dbHandler = new DataBHM();
        Vist v = new Vist();
        Exp ex = new Exp();
        ExpInfo.setPrefRowCount(10);
        String[] str = dbHandler.getVist(v).split("\n");
        String[] st = dbHandler.getExp(ex).get(0).split("\n");

        for (String s : str) {
            ListOfVist.getSelectionModel().getSelectedItem();
            ListOfVist.getItems().add(s);
        }
        for (String string : st) {
            ListOfVist.getSelectionModel().getSelectedItem();
            ListOfExp.getItems().add(string);
        }

        BupdVist.setOnAction(actionEvent -> {
            System.out.println("dffdf");
            try {
                ListOfVist.getItems().clear();
                String[] string = dbHandler.getVist(v).split("\n");
                for (String s : string) {
                    ListOfVist.getItems().add(s);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        BupdEx.setOnAction(actionEvent -> {
            try {
                ListOfExp.getItems().clear();
                String[] string = dbHandler.getExp(ex).get(0).split("\n");
                for (String s : string) {
                    ListOfExp.getItems().add(s);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


        Bshow.setOnAction(actionEvent -> {
            DataBHM db = new DataBHM();
            ExpInfo.clear();
            String[] arrOfSt = ListOfExp.getSelectionModel().getSelectedItem().toString().split(" Название");
            try {
                ExpInfo.appendText(db.getExp(arrOfSt[0]));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        BShowEx.setOnAction(actionEvent -> {
            ListOfExp.getItems().clear();
            DataBHM db = new DataBHM();
            String[] arrOfSt = ListOfVist.getSelectionModel().getSelectedItem().toString().split(" Дата");
            try {
                String[] arrSt = db.getSelectExp(arrOfSt[0]).split("\n");
                for (String s:arrSt) {
                    ListOfExp.getItems().add(s);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        });

    }
}
