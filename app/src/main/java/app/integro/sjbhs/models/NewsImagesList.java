package app.integro.sjbhs.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class NewsImagesList {

    private String message;

    @SerializedName("newsimages")
    private ArrayList<NewsImages> newsImagesArrayList;

    public ArrayList<NewsImages> getNewsImagesArrayList() {
        return newsImagesArrayList;
    }

    public void setNewsImagesArrayList(ArrayList<NewsImages> newsImagesArrayList) {
        this.newsImagesArrayList = newsImagesArrayList;
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
