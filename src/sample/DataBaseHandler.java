package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.sql.ResultSet;

public class DataBaseHandler extends Configs{
    Connection dbConnection ;
    public Connection getDbConnection()
        throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection =  DriverManager.getConnection(connectionString, dbUser, dbPass);
        return  dbConnection;
    }
    public void signUpUser(User user)
    {

        String insert = "INSERT INTO "+Const.USER_TABLE+"("+Const.USER_NAME+","+Const.USER_SER+","+Const.USER_LAST
                +","+Const.USER_AGE+","+Const.USER_LOG+","+Const.USER_PASS+","+Const.USER_GENDER+")"+"VALUES(?,?,?,?,?,?,?)";
      try {
          PreparedStatement prSt = getDbConnection().prepareStatement(insert);
          prSt.setString(1, user.getFirstName());
          prSt.setString(2, user.getSerName());
          prSt.setString(3, user.getLastName());
          prSt.setString(4, user.getAge());
          prSt.setString(5, user.getLogin());
          prSt.setString(6, user.getPassword());
          prSt.setString(7, user.getGender());

          prSt.executeUpdate();
      } catch (SQLException e){
          e.printStackTrace();
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }


    }
    public ResultSet getUser(User user){
        ResultSet resultSet = null;
        String select = "SELECT * FROM "+Const.USER_TABLE+" WHERE "+Const.USER_LOG+"=? AND "
                +Const.USER_PASS+"=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());


            resultSet = prSt.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public String getUser(){
        ResultSet resultSet = null;
        String data = "";
        int i = 1;
        String select = "SELECT * FROM "+Const.USER_TABLE;
        try {

            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                data += "Имя пользователя: " + resultSet.getString("FirstName")+" Фамилия: "+resultSet.getString("SerName")+" Отчество: "+resultSet.getString("LastName");
                data+="\n";
                // or p.name=rs.getString("firstname"); by name of column
            }
            return data;
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }


}
