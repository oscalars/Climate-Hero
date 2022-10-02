package com.example.climatehero.ViewModel;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.climatehero.Model.AddToDatabase;
import com.example.climatehero.Model.DatabaseHelper;

import java.util.ArrayList;
import java.util.Objects;


public class DatabaseViewModel extends ViewModel {

        private DatabaseHelper myDb;
        private String suggestedBin;

        public void setDB(Context context){
                myDb = new DatabaseHelper(context);
                myDb.clearIfExist();
                AddToDatabase.add(myDb);
        }

        public void queryDb(ArrayList<String> items) {
                String result;
                for (String s : items) {
                        result = myDb.getSuggestedBin(s);
                        if (!Objects.equals(result, "NoMatch")) {
                                suggestedBin = "Recycle the " + s + " in the " + result;
                                return;
                        } else {
                                suggestedBin = "Sorry, we found no match for that item";
                        }
                }
        }

        public String getSuggestedBin(){
                return suggestedBin;
        }
}


