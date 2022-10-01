package com.example.climatehero.Model;

import android.graphics.Bitmap;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.AnnotateImageResponse;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.EntityAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CloudVisionRequest {
    public List<String> labelRequest(Bitmap bitmap) {

        Image image = getImageEncodeImage(bitmap);

        ArrayList<Feature> features = new ArrayList<Feature>();
        ArrayList<AnnotateImageRequest> annotateImageRequests = new ArrayList<AnnotateImageRequest>();

        Feature feature = new Feature();
        feature.setType("LABEL_DETECTION");
        feature.setMaxResults(10);
        features.add(feature);

        AnnotateImageRequest annotateImageReq = new AnnotateImageRequest();
        annotateImageReq.setFeatures(features);
        annotateImageReq.setImage(image);
        annotateImageRequests.add(annotateImageReq);

        try {
            HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

            VisionRequestInitializer requestInitializer = new VisionRequestInitializer("AIzaSyDBLJOew5YIs7lCYGTzW93X6-oMbCNz1vs");
            Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
            builder.setVisionRequestInitializer(requestInitializer);

            Vision vision = builder.build();

            BatchAnnotateImagesRequest batchAnnotateImagesRequest = new BatchAnnotateImagesRequest();
            batchAnnotateImagesRequest.setRequests(annotateImageRequests);
            Vision.Images.Annotate annotateRequest = vision.images().annotate(batchAnnotateImagesRequest);
            annotateRequest.setDisableGZipContent(true);
            BatchAnnotateImagesResponse response = annotateRequest.execute();

            List<String> labels = null;

            for (AnnotateImageResponse res : response.getResponses()) {
                for (EntityAnnotation annotation : res.getLabelAnnotations()) {
                    if (annotation.getScore() > 0.90) {
                        labels.add(annotation.getDescription());
                    }
                }
            }

            return labels;
        } catch (GoogleJsonResponseException e) {
            //Log.d(TAG, "failed to make API request because " + e.getContent());
        } catch (IOException e) {
            //Log.d(TAG, "failed to make API request because of other IOException " + e.getMessage());
        }
        return null;

    }

    private Image getImageEncodeImage(Bitmap bitmap) {
        Image base64EncodedImage = new Image();
        // Convert the bitmap to a JPEG
        // Just in case it's a format that Android understands but not Cloud Vision
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        // Base64 encode the JPEG
        base64EncodedImage.encodeContent(imageBytes);
        return base64EncodedImage;
    }
}
