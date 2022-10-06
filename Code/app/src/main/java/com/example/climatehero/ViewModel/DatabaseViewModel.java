package com.example.climatehero.ViewModel;


import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.climatehero.Model.AddFromCloudDatabase;
import com.example.climatehero.Model.AddToDatabase;
import com.example.climatehero.Model.DatabaseConn;
import com.example.climatehero.Model.DatabaseHelper;

import java.util.ArrayList;
import java.util.Objects;


public class DatabaseViewModel extends ViewModel {

        private DatabaseHelper myDb;
        private String suggestedBin;
        private int cloudDbVersion;
        private int localDbVersion;

        public void setDB(Context context){
                myDb = new DatabaseHelper(context);
                localDbVersion = 1;//myDb.getVersionFromTable();

                if(localDbVersion == 1){
                        myDb.clearIfExist();
                        AddToDatabase.add(myDb);
                }

                if(checkDbVersion()){
                        myDb.clearIfExist();
                        ArrayList<String> cloudResult = getCloudDB();
                        AddFromCloudDatabase.add(myDb, cloudResult, cloudDbVersion);
                }
        }

        public void queryDb(ArrayList<String> items) {
                suggestedBin = "Sorry, we found no match for that item.";
                String result;
                for (String s : items) {
                        result = myDb.getSuggestedBin(s);
                        if (!Objects.equals(result, "NoMatch")) {
                                suggestedBin = "Recycle the " + s.toLowerCase() + " in the " + result.toLowerCase() + ".";
                                break;
                        } else {
                                suggestedBin = "Sorry, we found no match for that item.";
                        }
                }
        }

        public String getSuggestedBin(){
                return suggestedBin;
        }

        //If updated database in cloud, use that one, if not, use local database
        public boolean checkDbVersion(){
                final int[] cloudDB = new int[1];
                Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                                try {
                                        DatabaseConn db = new DatabaseConn();
                                        cloudDB[0] = db.getDatabaseVersion();
                                } catch (Exception e) {
                                        System.out.print(e.getMessage());
                                        e.printStackTrace();
                                }
                        }
                });
                thread.start();
                try{
                        thread.join();
                } catch (Exception e){
                        System.out.print(e.getMessage());
                        e.printStackTrace();
                }
                if(cloudDB[0] > localDbVersion){
                        cloudDbVersion = cloudDB[0];
                        return true;
                } else return false;
        }

        //Get the whole database from cloud to replace the local database
        public ArrayList<String> getCloudDB() {
                final ArrayList<String>[] cloudDB = new ArrayList[]{new ArrayList<>()};
                Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                                try {
                                        DatabaseConn db = new DatabaseConn();
                                        cloudDB[0] = db.getWholeDb();

                                } catch (Exception e) {
                                        System.out.print(e.getMessage());
                                        e.printStackTrace();
                                }
                        }
                });
                thread.start();
                try {
                        thread.join();
                } catch (Exception e) {
                        System.out.print(e.getMessage());
                        e.printStackTrace();
                }
                return cloudDB[0];
        }
}


