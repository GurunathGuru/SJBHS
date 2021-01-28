package app.integro.sjbhs.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class CoverPhotoList {
    private String message;

    @SerializedName("sjbhs_coverphoto")
    private ArrayList<CoverPhoto> coverPhotoArrayList;

    public ArrayList<CoverPhoto> getCoverPhotoArrayList() {
        return coverPhotoArrayList;
    }

    public void setCoverPhotoArrayList(ArrayList<CoverPhoto> coverPhotoArrayList) {
        this.coverPhotoArrayList = coverPhotoArrayList;
    }

    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
