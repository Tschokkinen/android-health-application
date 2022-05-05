package com.ericaskari.healthapplication;

/**
 * to pass data into {@link PainHistoryListItemViewHolder}
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
