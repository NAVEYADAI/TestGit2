package org.example;
/*
   owner : nave yadai
   id    : 326296472
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ConnectPGDB connectPGDB;
        connectPGDB = new ConnectPGDB();
        connectPGDB.upPGDB("testing","nave","1234");
        connectPGDB.select();
        connectPGDB.closeConnect();

        int f=0;
    }
}
