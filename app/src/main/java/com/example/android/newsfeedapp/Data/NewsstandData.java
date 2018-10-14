package com.example.android.newsfeedapp.Data;

public class NewsstandData {
    private int titleOfCategory; //Stores the title of the place's string ID
    private int imageOfCategoryResourceID; // Stores the side attraction's image ID

    /**
     * Creates a constructor of the Tour Places Data Class
     * @param inputTitleOfCategory is the string ID of the title of the category
     * @param inputImageOfCategoryResourceID is the ID of the category's image
     */
    public NewsstandData(int inputTitleOfCategory, int inputImageOfCategoryResourceID){
        titleOfCategory = inputTitleOfCategory;
        imageOfCategoryResourceID = inputImageOfCategoryResourceID;
    }

    /**
     * Gets the title of the category
     * @return the title of the category
     */
    public int getTitleOfCategory() {
        return titleOfCategory;
    }

    /**
     * Gets the image resource ID of the category
     * @return the image resource ID of the category
     */
    public int getImageOfCategoryResourceID() {
        return imageOfCategoryResourceID;
    }

}
