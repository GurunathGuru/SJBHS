package app.integro.sjbhs.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sjbhs_videos1List {

    private String message;

    @SerializedName("sjbhs_videos")
    List<Sjbhs_videos1> arSjbhs_videos1;

    public List<Sjbhs_videos1> getArSjbhs_videos1() {
        return arSjbhs_videos1;
    }

    public void setArSjbhs_videos1(List<Sjbhs_videos1> arSjbhs_videos1) {
        this.arSjbhs_videos1 = arSjbhs_videos1;
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
