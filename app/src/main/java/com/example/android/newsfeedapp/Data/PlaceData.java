package com.example.android.newsfeedapp.Data;

public class PlaceData {
    private int titleOfStory; //Stores the name of the place's string ID
    private int imageOfStoryResourceID; // Stores the side attraction's image ID

    /**
     * Creates a constructor of the Tour Places Data Class
     * @param inputTitleOfStory is the string ID of the name of the side attraction
     * @param inputImageOfStoryResourceID is the ID of the side attraction's image
     */
    public PlaceData(int inputTitleOfStory, int inputImageOfStoryResourceID){
        titleOfStory = inputTitleOfStory;
        imageOfStoryResourceID = inputImageOfStoryResourceID;
    }

    /**
     * Gets the name of the place
     * @return the name of the place
     */
    public int getTitleOfStory() {
        return titleOfStory;
    }

    /**
     * Gets the image resource ID of the place
     * @return the image resource ID of the place
     */
    public int getImageOfStoryResourceID() {
        return imageOfStoryResourceID;
    }

}
