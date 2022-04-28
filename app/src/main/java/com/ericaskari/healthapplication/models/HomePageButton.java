package com.ericaskari.healthapplication.models;

/**
 * to pass data into {@link com.ericaskari.healthapplication.viewholders.PainHistoryListItemViewHolder}
 * NOT A DB MODEL
 */
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
