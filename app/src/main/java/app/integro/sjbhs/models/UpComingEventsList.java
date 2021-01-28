package app.integro.sjbhs.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpComingEventsList {

    private String message;

    @SerializedName("sjbhs_event_of_the_month")
    private List<UpComingEvents> upComingEventsList;

    public List<UpComingEvents> getUpComingEventsList() {
        return upComingEventsList;
    }

    public void setUpComingEventsList(List<UpComingEvents> upComingEventsList) {
        this.upComingEventsList = upComingEventsList;
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
