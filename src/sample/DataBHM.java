package sample;

import java.sql.*;
import java.util.ArrayList;


public class DataBHM extends Configs{
    Connection dbConnection ;
    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection =  DriverManager.getConnection(connectionString, dbUser, dbPass);
        return  dbConnection;
    }
    public void insertInfo(Vist vist)
    {

        String insert = "INSERT INTO "+ConstVist.VIST_TABLE+"("+ConstVist.VIST_NAME+","+ConstVist.VIST_START+","
                +ConstVist.VIST_END+")"+"VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,vist.getName());
            prSt.setString(2, vist.getStart());
            prSt.setString(3, vist.getEnd());
            prSt.executeUpdate();
            System.out.println(prSt);
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public String getVist(Vist vist) throws SQLException {
        ResultSet resultSet = null;
        String data = "";
        int i = 1;
        String select = "SELECT * FROM "+ConstVist.VIST_TABLE;
        try {

            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                data += resultSet.getString("Name")+" Дата начала: "+resultSet.getString("Start")+" Дата окончания: "+resultSet.getString("End");
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
    public void delVist(String name) throws SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        dbConnection =  DriverManager.getConnection(connectionString, dbUser, dbPass);
        String delete = "DELETE FROM "+dbName+"."+ConstVist.VIST_TABLE+" WHERE Name = "+'"'+name+'"';
        String deleteEx = "DELETE FROM "+dbName+"."+ConstExp.EXP_TABLE+" WHERE Name = "+'"'+name+'"';
        System.out.println(delete);
        try {

            Statement statement =dbConnection.createStatement();
            statement.executeUpdate(delete);
            statement.executeUpdate(deleteEx);

        } catch (SQLException e){
            e.printStackTrace();

        }
    }
    public void insertInfo(Exp exp)
    {

        String insertnew = "INSERT INTO "+ConstExp.EXP_TABLE+" VALUES('0',?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insertnew);
            prSt.setString(1, exp.getName());
            prSt.setString(2, exp.getNameExp());
            prSt.setString(3, exp.getPeriod());
            prSt.setString(4, exp.getDs());
            System.out.println(prSt);
            prSt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    public ArrayList<String> getExp(Exp expt) throws SQLException {
        ResultSet resultSet = null;
        String data = "";
        String discr = "";
        ArrayList<String> st = new ArrayList<>();
        int i = 1;
        String select = "SELECT * FROM "+ConstExp.EXP_TABLE + " WHERE Name = "+ConstExp.EXP_VIST;
        try {

            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                data += resultSet.getString("NameExp")+ " Название выстваки: " + resultSet.getString("Name")+ " Период экспозиции: "+resultSet.getString("Period");
                data+="\n";

                discr+= resultSet.getString("Describe");
                discr+="\n";
                // or p.name=rs.getString("firstname"); by name of column
            }
            st.add(data);
            st.add(discr);
            return st;
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return st;

    }
    public String getExp(String s) throws SQLException {
        ResultSet resultSet = null;
        String d = "";
        String select = "SELECT * FROM "+ConstExp.EXP_TABLE + " WHERE NameExp = "+"'"+s+"'";
        try {

            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                d += resultSet.getString("Describe");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(d);
        return d;
    }
    public String getSelectExp(String s) throws SQLException {
            ResultSet resultSet = null;
            String data = "";
            String select = "SELECT * FROM "+ConstExp.EXP_TABLE + " WHERE Name = "+"'"+s+"'";
            try {

                PreparedStatement prSt = getDbConnection().prepareStatement(select);
                resultSet = prSt.executeQuery();
                while (resultSet.next()) {
                    data +=  resultSet.getString("NameExp")+ " Название выстваки: " + resultSet.getString("Name") +
                           " Период экспозиции: "+resultSet.getString("Period");
                    data+="\n";


                }

            } catch (SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return data;
    }

    public void delExp(String name) throws SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        dbConnection =  DriverManager.getConnection(connectionString, dbUser, dbPass);
        String delete = "DELETE FROM "+dbName+"."+ConstExp.EXP_TABLE+" WHERE NameExp = "+'"'+name+'"';
        System.out.println(delete);
        try {

            Statement statement =dbConnection.createStatement();
            statement.executeUpdate(delete);

        } catch (SQLException e){
            e.printStackTrace();

        }
    }
    public void AddChanges(String string, String exp){
        String ubdate = "UPDATE "+ConstExp.EXP_TABLE+" SET " + ConstExp.EXP_TABLE+"."+ConstExp.EXP_DS + " = "+'"'+string+'"'+" WHERE " + ConstExp.EXP_NAME + " = " + '"'+exp+'"';
        System.out.println(ubdate);
        try {
            Statement statement = getDbConnection().createStatement();
            statement.executeUpdate(ubdate);

        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
