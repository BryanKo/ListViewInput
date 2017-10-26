package com.bryankoproject.cmps121asg1;

/**
 * Created by imbko on 10/18/2017.
 */

public class PhotoList {
    private String PhotoName;
    private String PhotoDate;
    private String Photographer;

    public PhotoList(String pName, String pDate, String pGrapher) {
        PhotoName = pName;
        PhotoDate = pDate;
        Photographer = pGrapher;
    }

    public String getPhotoName() { return PhotoName; }

    public String getPhotoDate() {
        return PhotoDate;
    }

    public String getPhotographer() {
        return Photographer;
    }
}
