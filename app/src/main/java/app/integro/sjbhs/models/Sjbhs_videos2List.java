package app.integro.sjbhs.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sjbhs_videos2List {
    private String message;

    public List<Sjbhs_videos2> getSjbhs_videos2Lists() {
        return sjbhs_videos2Lists;
    }

    public void setSjbhs_videos2Lists(List<Sjbhs_videos2> sjbhs_videos2Lists) {
        this.sjbhs_videos2Lists = sjbhs_videos2Lists;
    }

    @SerializedName("videos")
    List<Sjbhs_videos2> sjbhs_videos2Lists;

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
