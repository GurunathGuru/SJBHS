package app.integro.sjbhs.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationList {
    private String message;

    @SerializedName("sjbhs_notification")
    private List<Notification> notificationList;

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
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
