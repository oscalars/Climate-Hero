package com.example.climatehero.ViewModel;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

import com.example.climatehero.Model.CloudVisionRequest;

import java.util.List;

public class CloudVisionViewModel extends ViewModel {

    private Bitmap photo;
    private List<String> recognizedItems;

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public void recognizeItem() {
        //this.photo = bitmap;
        CloudVisionRequest request = new CloudVisionRequest();
        this.recognizedItems = request.labelRequest(photo);
    }

    public List<String> getRecognizedItems() {
        return recognizedItems;
    }
}
