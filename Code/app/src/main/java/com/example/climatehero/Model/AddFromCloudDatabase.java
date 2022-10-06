package com.example.climatehero.Model;

import java.util.ArrayList;

public class AddFromCloudDatabase {

    public static void add(DatabaseHelper db, ArrayList<String> list, int version){

        db.insertData(list.get(0), list.get(1), version);

        int j;
        for(int i = 2; i < list.size(); i++){
            j = i + 1;
            db.insertData(list.get(i), list.get(j), null);
            i++;
        }
    }
}
