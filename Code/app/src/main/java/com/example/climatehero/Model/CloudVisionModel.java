package com.example.climatehero.Model;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Data;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.AnnotateImageResponse;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.api.services.vision.v1.model.ImageSource;


import android.graphics.Bitmap;
import android.util.Log;



import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.util.ArrayList;



public class CloudVisionModel {

    public CloudVisionModel() {}

    private static final String TAG = "CloudVisionModel";

    public ArrayList<String> recognizeImage(Bitmap photo) {
        try {
            Log.d(TAG, "inside try");
            Vision.Images.Annotate request = prepareAnnotationRequest(photo);
            BatchAnnotateImagesResponse response = request.execute();

            ArrayList<String> labels = new ArrayList<>();

            for (AnnotateImageResponse res : response.getResponses()) {
                for (EntityAnnotation annotation : res.getLabelAnnotations()) {
                    Log.d(TAG, annotation.getDescription());
                    if (annotation.getScore() > 0.90) {
                        labels.add(annotation.getDescription());
                    }
                }
            }

            Log.d(TAG, labels.toString());
            return labels;

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }


    private static Vision.Images.Annotate prepareAnnotationRequest(Bitmap photo) throws IOException {
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

        VisionRequestInitializer requestInitializer = new VisionRequestInitializer("AIzaSyDKTEftqwtRnLeRCDH81mVy966Q6dI8e5M");

        Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
        builder.setVisionRequestInitializer(requestInitializer);

        Vision vision = builder.build();

        BatchAnnotateImagesRequest batchAnnotateImagesRequest = new BatchAnnotateImagesRequest();
        batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
            AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

            Image encodedimage = new Image();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();

            encodedimage.encodeContent(imageBytes);
            annotateImageRequest.setImage(encodedimage);

            annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                Feature labelDetection = new Feature();
                labelDetection.setType("LABEL_DETECTION");
                labelDetection.setMaxResults(5);
                add(labelDetection);
            }});
            add(annotateImageRequest);
        }});
        Vision.Images.Annotate annotateRequest = vision.images().annotate(batchAnnotateImagesRequest);
        return annotateRequest;

    }
}
