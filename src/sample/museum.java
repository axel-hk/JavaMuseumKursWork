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


public class museum {

    @FXML
    private TextField NameVistField;
    @FXML
    private TextField StartVistField;
    @FXML
    private TextField EndVistField;
    @FXML
    private TextField NameExField;
    @FXML
    private TextField PeriodExField;
    @FXML
    private Button BaddVist;
    @FXML
    private Button BupdVist;
    @FXML
    private Button BdelVist;
    @FXML
    private Button BaddEx;
    @FXML
    private Button BupdEx;
    @FXML
    private Button BdelEx;
    @FXML
    private Button Bshow;
    @FXML
    private Button AddCh;
    @FXML
    private ListView ListOfExp;
    @FXML
    private ListView ListOfVist;
    @FXML
    private TextArea ExpInfo;
    @FXML
    private Button Botch;
    @FXML
    private Button BShowEx;
   @FXML
   private Text Tloc;

    @FXML
    void  initialize() throws SQLException {
        DataBHM dbHandler = new DataBHM();
        Vist v = new Vist();
        Exp ex = new Exp();
        ExpInfo.setPrefRowCount(10);
        String[] str = dbHandler.getVist(v).split("\n");
        String[] st = dbHandler.getExp(ex).get(0).split("\n");

        for (String s : str) {
            ListOfVist.getSelectionModel().getSelectedItem();
        }
        for (String string : st) {
            ListOfExp.getSelectionModel().getSelectedItem();
        }
        BaddVist.setOnAction(actionEvent -> {
            AddNewVist();
        });
        BupdVist.setOnAction(actionEvent -> {
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

        BdelVist.setOnAction(actionEvent -> {
            DataBHM db = new DataBHM();
            String[] arrOfStr = ListOfVist.getSelectionModel().getSelectedItem().toString().split(" Дата", 5);

            try {

                db.delVist(arrOfStr[0]);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        BaddEx.setOnAction(actionEvent -> {
            AddNewExp();
        });
        BdelEx.setOnAction(actionEvent -> {
            DataBHM db = new DataBHM();
            String[] arrOfS = ListOfExp.getSelectionModel().getSelectedItem().toString().split(" Название");

            try {

                db.delExp(arrOfS[0]);


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
        AddCh.setOnAction((actionEvent -> {
            DataBHM db = new DataBHM();
            String[] arrOfSt = ListOfExp.getSelectionModel().getSelectedItem().toString().split(" Название");
            db.AddChanges(ExpInfo.getText().trim(), arrOfSt[0]);

        }));
        Botch.setOnAction(actionEvent -> {

            DataBaseHandler db = new DataBaseHandler();
            File file = new File("D://Java//Museum", "Otchety.txt");
            file.delete();
            File file1 = new File("D://Java//Museum", "Otchety.txt");
            String path = file1.getAbsoluteFile().toString();
            FileWriter writer = null;
            try {
                writer = new FileWriter(file1, true);

                writer.write("__________________________________Отчёт о выставках___________________________");
                for (String vist : getVist().get(0)) {
                    writer.append('\n');
                    writer.write(vist);
                }
                writer.append('\n');
                writer.write("__________________________________Отчёт об экспонатх___________________________");
                for (String exp : getVist().get(1)) {
                    writer.append('\n');
                    writer.write(exp);
                }
                writer.append('\n');
                writer.write("__________________________________Отчёт о пользователях___________________________");
                writer.append('\n');
                writer.write(db.getUser());

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Tloc.setText(path);
        });
    }

    private void AddNewVist() {
        DataBHM dbHandler = new DataBHM();
        String name = NameVistField.getText().trim();
        String start = StartVistField.getText().trim();
        String end = EndVistField.getText().trim();
        Vist vist = new Vist(name,start,end);
        dbHandler.insertInfo(vist);
    }

    private void  AddNewExp(){
        DataBHM dbHandler = new DataBHM();
        String[] arr= ListOfVist.getSelectionModel().getSelectedItem().toString().split(" Дата", 5);
        String Name = arr[0];
        String NameExp = NameExField.getText().trim();
        String Period = PeriodExField.getText().trim();
        String Descr = ExpInfo.getText().trim();
        Exp exp = new Exp(Name, NameExp, Period, Descr);
        dbHandler.insertInfo(exp);
    }
    private  ArrayList<String[]> getVist(){
        DataBHM dbHandler = new DataBHM();
        Vist v = new Vist();
        Exp ex = new Exp();
        ArrayList<String[]> st = new ArrayList<>();
        try { String[] vist = dbHandler.getVist(v).split("\n");
                 String[] exp= dbHandler.getExp(ex).get(0).split("\n");
                 st.add(vist);
                 st.add(exp);

        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return st;
    }
}

