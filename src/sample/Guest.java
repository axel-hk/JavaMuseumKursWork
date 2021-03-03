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


public class Guest {

    @FXML
    private Button Breg;
    @FXML
    private ListView ListOfExp;
    @FXML
    private ListView ListOfVist;

    @FXML
    void initialize() throws SQLException {
        DataBHM dbHandler = new DataBHM();
        Vist v = new Vist();
        Exp ex = new Exp();
        String[] str = dbHandler.getVist(v).split("\n");
        String[] st = dbHandler.getExp(ex).get(0).split("\n");

        for (String s : str) {
            ListOfVist.getSelectionModel().getSelectedItems();
            ListOfVist.getItems().add(s);
        }
        for (String string : st) {
            ListOfExp.getSelectionModel().getSelectedItem();
            ListOfExp.getItems().add(string);
        }



        Breg.setOnAction(actionEvent -> {
            Breg.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Registration.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

    }
}