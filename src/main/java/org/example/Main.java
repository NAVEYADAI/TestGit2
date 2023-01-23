
package org.example;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
   owner : nave yadai
   id    : 326296472
 */
public class Main {
    public static void main(String[] args) {
        List<name> list ;
        System.out.println("Hello world!");
        ConnectPGDBPrepared connectPGDB;
        connectPGDB = new ConnectPGDBPrepared();
        connectPGDB.upPGDB("testing","nave","1234");
        //connectPGDB.insertWithObject(new name("DEMO","demo"));
        //list=connectPGDB.selectwithCreateList();
        //connectPGDB.setDataFromObj(new name(1,"one","one"));
        connectPGDB.selectAll();
        connectPGDB.closeConnect();
    }
}