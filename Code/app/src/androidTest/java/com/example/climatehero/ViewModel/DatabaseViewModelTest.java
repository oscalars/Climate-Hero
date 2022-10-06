package com.example.climatehero.ViewModel;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class DatabaseViewModelTest extends TestCase {

    @Test
    public void testQueryDbForFood() {
        DatabaseViewModel databaseViewModel = new DatabaseViewModel();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        databaseViewModel.setDB(appContext);
        ArrayList<String> items = new ArrayList<>();
        items.add("Food");
        databaseViewModel.queryDb(items);
        assertEquals("Recycle the food in the compost.",
                databaseViewModel.getSuggestedBin());
    }

    @Test
    public void testQueryDBForNoMatch() {
        DatabaseViewModel databaseViewModel = new DatabaseViewModel();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        databaseViewModel.setDB(appContext);
        ArrayList<String> items = new ArrayList<>();
        items.add("Koala");
        databaseViewModel.queryDb(items);
        assertEquals("Sorry, we found no match for that item.",
                databaseViewModel.getSuggestedBin());
    }
}