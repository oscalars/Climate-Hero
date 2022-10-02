package com.example.climatehero.ViewModel;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

import com.example.climatehero.Model.CloudVisionModel;

import java.util.ArrayList;


public class CloudVisionViewModel extends ViewModel {

    private Bitmap photo;
    private ArrayList<String> result;

    public void recognizeImage() {
        CloudVisionModel cloudVisionModel = new CloudVisionModel();
        result = cloudVisionModel.recognizeImage(photo);
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public ArrayList<String> getResult() {
        return result;
    }

}