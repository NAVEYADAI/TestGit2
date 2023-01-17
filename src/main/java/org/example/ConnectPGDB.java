package org.example;

import java.sql.*;

/**
 * owner : nave yadai
 * @author NaveYadai
 *    id    : 326296472
 */
public class ConnectPGDB {
    private Connection connection;
    public void upPGDB(String nameOfDB , String userName, String password){
        String jdbcURL = "jdbc:postgresql://localhost:5432/"+nameOfDB;
        try {
            this.connection = DriverManager.getConnection(jdbcURL,userName,password);
            System.out.println("\nnconnect is working\n");
        } catch (SQLException e) {
            System.out.println("\n\n\n\n\n\n\n\nerror in connect to PostgreSQL sever\n\n\n\n\n\n\n\n");
            throw new RuntimeException(e);
        }
    }
    public void closeConnect(){
        try {
            this.connection.close();
            System.out.println("\nclose connect is working\n");
        } catch (SQLException e) {
            System.out.println("\n\n\n\n\n\n\n\nerror in close connection\n\n\n\n\n\n\n\n");
            throw new RuntimeException(e);
        }
    }
    public void insertWitchOutDrop(String nameOfTable , String typeForAdd , String variable){

        String sqlInsert= "INSERT INTO "+ nameOfTable + "("+typeForAdd + ") VALUES ( ? " ;
        String arr[]=variable.split(",",' ');
        for (int i = 0; i < arr.length-1; i++) {
            sqlInsert += ", ?";
        }
        sqlInsert += ")";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlInsert);
            for (int i = 0; i < arr.length; i++) {
                statement.setString(i+1 , arr[i]);
            }
            int row=statement.executeUpdate();
            if (row > 0 ){
                System.out.println("a new connection work");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(){
        String sqlUpdate = "UPDATE public.name\n" +
                "\tSET  f_name= ?, l_name= ?\n" +
                "\tWHERE id = 4;";
        try {
            PreparedStatement statement =connection.prepareStatement(sqlUpdate);
            statement.setString(1,"5555");
            statement.setString(2,"5555");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insert(String nameOfTable , String typeForAdd ,String variable){
        String sqlInsert= "INSERT INTO "+ nameOfTable + "("+typeForAdd + ") VALUES (" + variable+")" ;
        int row ;
        try {
            Statement statement = connection.createStatement();
            row=statement.executeUpdate(sqlInsert);
            if (row > 0 ){
                System.out.println("a new connection work");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void select(){
        String sqlSelect = "SELECT  * FROM name";
        try {
            Statement statement= connection.createStatement();
            ResultSet result = statement.executeQuery(sqlSelect);
            while (result.next()){
                name r=new name(result.getInt("id")
                        ,result.getString("f_name"),result.getString("l_name"));
                System.out.println("id = "+r.getId()+" f_name = "+r.getF_name()+" l_name = "+r.getL_name());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}