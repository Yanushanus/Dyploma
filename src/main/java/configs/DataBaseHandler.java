package configs;

import userClasses.User;
import userClasses.UserOffer;
import userClasses.clubs.Club;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DataBaseHandler extends Config {
    Connection dbConnection;

    public Connection getDbConnection() throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://" + dbHost
                + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void signUpUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + UserConst.USER_TABLE + "(" + UserConst.USER_NAME + "," + UserConst.USER_SURNAME + ","
                + UserConst.USER_LOGIN + "," + UserConst.USER_PASSWORD + "," + UserConst.USER_ROLE + ")" + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, "user");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getExistUser(User user) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + UserConst.USER_TABLE + " WHERE " + UserConst.USER_LOGIN + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, user.getLogin());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + UserConst.USER_TABLE + " WHERE " + UserConst.USER_LOGIN + "=? AND " + UserConst.USER_PASSWORD + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getOffer(String name,String surname) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + UserOfferConst.USER_OFFER_TABLE + " WHERE " + UserOfferConst.USER_OFFER_NAME + "=? AND "
                + UserOfferConst.USER_OFFER_SURNAME + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public ResultSet getUserCount(){
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + UserConst.USER_TABLE;
        try {
            PreparedStatement preparedStatement=getDbConnection().prepareStatement(select);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public ResultSet getAllSum()  {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + UserOfferConst.USER_OFFER_TABLE;
        try {
            PreparedStatement preparedStatement=getDbConnection().prepareStatement(select);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public ResultSet getOfferList()  {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + UserOfferConst.USER_OFFER_TABLE;
        try {
            PreparedStatement preparedStatement=getDbConnection().prepareStatement(select);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void addOffer(UserOffer userOffer) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + UserOfferConst.USER_OFFER_TABLE + "(" + UserOfferConst.USER_OFFER_NAME + ","
                + UserOfferConst.USER_OFFER_SURNAME + ","
                + UserOfferConst.USER_OFFER_CLUBLIST + "," + UserOfferConst.USER_OFFER_SUM + "," + UserOfferConst.USER_OFFER_STATUS + ")" + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, userOffer.getName());
            preparedStatement.setString(2, userOffer.getSurname());
            preparedStatement.setString(3, userOffer.getNameList());
            preparedStatement.setString(4, userOffer.getSumString());
            preparedStatement.setString(5, userOffer.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateOffer() throws SQLException, ClassNotFoundException {

        String insert = "UPDATE user_offer "+" SET status = ? "+"WHERE status =?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,"accepted");
            preparedStatement.setString(2,"unaccepted");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getClub(String name) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ClubConst.CLUB_TABLE + " WHERE " + ClubConst.CLUB_NAME + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


}
