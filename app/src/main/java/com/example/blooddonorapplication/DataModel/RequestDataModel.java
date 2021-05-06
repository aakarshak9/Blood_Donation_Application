package com.example.blooddonorapplication.DataModel;

public class RequestDataModel {

    private String message;
    private String ImageUrl;

    public RequestDataModel(String message, String imageUrl) {
        this.message = message;
        this.ImageUrl = imageUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.ImageUrl = imageUrl;
    }
}

