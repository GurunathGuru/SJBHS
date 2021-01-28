package app.integro.sjbhs.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class LeadershipList {
    private String message;

    @SerializedName("sjbhs_leadership")
    private ArrayList<Leadership> leadershipListArrayList;

    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Leadership> getLeadershipListArrayList() {
        return leadershipListArrayList;
    }

    public void setLeadershipListArrayList(ArrayList<Leadership> leadershipListArrayList) {
        this.leadershipListArrayList = leadershipListArrayList;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
