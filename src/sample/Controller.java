package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    @FXML
    private TextField Log_fielf;

    @FXML
    private TextField password_field;

    @FXML
    private Button Login_button;

    @FXML
    private Button registration_button;
    @FXML
    private Button Bguest;

    @FXML
    void initialize(){
        Login_button.setOnAction(actionEvent -> {
            String loginText = Log_fielf.getText().trim();
            String loginPass = password_field.getText().trim();
            if(!loginText.equals("")&&!loginPass.equals("")){
                if(loginText.equals("Admin")&&loginPass.equals("Admin"))
                {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/museum.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                }
                try {
                    loginUser(loginText,loginPass);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println("Login or Password is empty");

        });
        registration_button.setOnAction(actionEvent ->{
            registration_button.getScene().getWindow().hide();

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
        } );
        Bguest.setOnAction(actionEvent ->{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Guest.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } );


    }
    private void loginUser(String loginText, String loginPass) throws SQLException {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPass);
        ResultSet resultSet = dbHandler.getUser(user);
        int count = 0;
        while (resultSet.next()){
            count++;
        }
         if(count>=1){
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(getClass().getResource("/sample/MuseumUs.fxml"));
             try {
                 loader.load();
             } catch (IOException e) {
                 e.printStackTrace();
             }
             Parent root = loader.getRoot();
             Stage stage = new Stage();
             stage.setScene(new Scene(root));
             stage.showAndWait();

    }
    }
}
