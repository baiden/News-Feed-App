package com.example.android.newsfeedapp.Data;

public class NewsstandData {
    private int titleOfCategory; //Stores the name of the place's string ID
    private int imageOfCategoryResourceID; // Stores the side attraction's image ID

    /**
     * Creates a constructor of the Tour Places Data Class
     * @param inputTitleOfCategory is the string ID of the name of the side attraction
     * @param inputImageOfCategoryResourceID is the ID of the side attraction's image
     */
    public NewsstandData(int inputTitleOfCategory, int inputImageOfCategoryResourceID){
        titleOfCategory = inputTitleOfCategory;
        imageOfCategoryResourceID = inputImageOfCategoryResourceID;
    }

    /**
     * Gets the name of the place
     * @return the name of the place
     */
    public int getTitleOfCategory() {
        return titleOfCategory;
    }

    /**
     * Gets the image resource ID of the place
     * @return the image resource ID of the place
     */
    public int getImageOfCategoryResourceID() {
        return imageOfCategoryResourceID;
    }

}
