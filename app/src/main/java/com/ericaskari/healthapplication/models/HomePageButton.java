package com.ericaskari.healthapplication.models;

public class HomePageButton {

    private Integer textResourceId;
    private Integer imageResourceId;

    public HomePageButton(Integer textResourceId, Integer imageResourceId) {
        this.textResourceId = textResourceId;
        this.imageResourceId = imageResourceId;
    }

    public Integer getTextResourceId() {
        return textResourceId;
    }

    public Integer getImageResourceId() {
        return imageResourceId;
    }
}
