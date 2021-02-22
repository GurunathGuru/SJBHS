package app.integro.sjbhs.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Sjbhs_Photos2List {

    private String message;

    public ArrayList<Sjbhs_Photos2> getSjbhsPhotos1ArrayList() {
        return sjbhsPhotos1ArrayList;
    }

    public void setSjbhsPhotos1ArrayList(ArrayList<Sjbhs_Photos2> sjbhsPhotos1ArrayList) {
        this.sjbhsPhotos1ArrayList = sjbhsPhotos1ArrayList;
    }

    @SerializedName("newsimages")
    ArrayList<Sjbhs_Photos2> sjbhsPhotos1ArrayList;

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
