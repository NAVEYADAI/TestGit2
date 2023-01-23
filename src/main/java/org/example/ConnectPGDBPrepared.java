package org.example;

import java.awt.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;



public class ConnectPGDBPrepared {
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
    public void insertWithValue(String nameOfTable , String typeForAdd , String variable){
        String arr[]=variable.split(",",' ');
        String arr2[]=nameOfTable.split(",");
        String sqlInsert= "INSERT INTO ? ";
        sqlInsert += "(";
        for (int i = 0; i < arr2.length-1; sqlInsert += ", ?" ,i++) ;
        sqlInsert +=") VALUES ( ? ";
        for (int i = 0; i < arr.length-1; sqlInsert += ", ?" ,i++) ;
        sqlInsert += ")";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlInsert);
            statement.setString(1,nameOfTable);
            for (int i = 0; i < arr.length; i++) {
                statement.setString(i+1 , arr[i]);}
            for (int i = 0; i < arr.length; i++) {
                statement.setString(i+1+arr.length , arr[i]);}
            int row=statement.executeUpdate();
            if (row > 0 ){
                System.out.println("a new connection work");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertWithObject(name obj){
        String sqlInsert = "INSERT INTO public.name(f_name, l_name) VALUES ( ?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlInsert);
            statement.setString(1, obj.getF_name());
            statement.setString(2, obj.getL_name());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void insertWithListObj(name[] arr){
        for (int i=0 ; i<arr.length ;insertWithObject(arr[i]), i++);
    }
    public void selectAll(){
        String sqlSelect = "SELECT  * FROM name ORDER BY id ASC";
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
    public List<name> selectwithCreateList(){
        List<name> listName = new ArrayList<>();
        String sqlSelect = "SELECT  * FROM name";
        try {
            Statement statement= connection.createStatement();
            ResultSet result = statement.executeQuery(sqlSelect);
            while (result.next()){
                name r=new name(result.getInt("id")
                        ,result.getString("f_name"),result.getString("l_name"));
                listName.add(new name(result.getInt("id")
                        ,result.getString("f_name"),result.getString("l_name")));
                //System.out.println("id = "+r.getId()+" f_name = "+r.getF_name()+" l_name = "+r.getL_name());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listName;
    }
    public void updateList(List<name> list){
        for (name i :list) {
            update(i);
        }
    }
    public void update(name name){
        String sqlUpdate = "UPDATE public.name\n" +
                "\tSET  f_name= ?, l_name= ?\n" +
                "\tWHERE id = ?;";
        try {
            PreparedStatement statement =connection.prepareStatement(sqlUpdate);
            statement.setString(1, name.getF_name());
            statement.setString(2,name.getL_name());
            statement.setInt(3,name.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setDataFromObj(name tmp){
        List<name> list=selectwithCreateList();
        boolean inList = true;
        for (name i :list) {
            if(i.getId()== tmp.getId()){
                i.setF_name(tmp.getF_name());
                i.setL_name(tmp.getL_name());
                inList=false;
                break;
            }
        }
        if (inList){
            insertWithObject(new name("now","is_insert"));
        }
        updateList(list);
    }
}
