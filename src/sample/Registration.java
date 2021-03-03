package sample;

import com.mysql.cj.log.Log;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Registration {

    @FXML
    private CheckBox MaleCheck;

    @FXML
    private TextField Log_reg;

    @FXML
    private TextField password_reg;

    @FXML
    private Button Reg_button;

    @FXML
    private TextField Name;

    @FXML
    private TextField Sername;

    @FXML
    private TextField SecondName;

    @FXML
    private TextField Age;

    @FXML
    private CheckBox WomanCheck;
    @FXML
    void  initialize(){
        DataBaseHandler dbHandler = new DataBaseHandler();

        Reg_button.setOnAction(actionEvent -> {
            signUpNewUser();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/sample.fxml"));
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

    private void signUpNewUser() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        String FirstName = Name.getText();
        String SerName = Sername.getText();
        String LastName = SecondName.getText();
        String age = Age.getText();
        String password =  password_reg.getText();
        String login = Log_reg.getText();
        String gender = "";
        if(MaleCheck.isSelected())
        {
            gender = "Мужской";
        }
        else if(WomanCheck.isSelected())
        {
            gender = "Женский";
        }
        User user = new User(FirstName,SerName,LastName,age,password,login, gender);
        dbHandler.signUpUser(user);

    }


}

