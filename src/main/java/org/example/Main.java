package org.example;
/*
   owner : nave yadai
   id    : 326296472
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ConnectPGDB connectPGDB = new ConnectPGDB();
        connectPGDB.upPGDB("testing","nave","1234");
        //connectPGDB.insertWitchOutDrop("name","f_name , l_name" , "nave3 , yadai3");
        connectPGDB.update();
        connectPGDB.select();
        connectPGDB.closeConnect();
    }
}
