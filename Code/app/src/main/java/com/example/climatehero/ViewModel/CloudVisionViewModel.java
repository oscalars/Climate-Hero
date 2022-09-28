package com.example.climatehero.ViewModel;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

public class CloudVisionViewModel extends ViewModel {

    private Bitmap photo;
    private String recognizedItem;

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getRecognizedItem() {
        return recognizedItem;
    }

    public void setRecognizedItem(String recognizedItem) {
        this.recognizedItem = recognizedItem;
    }
}
