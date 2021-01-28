package app.integro.sjbhs.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class NewsList {

    private String message;

    @SerializedName("sjbhs_news")
    private List<News> newsList;

    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}
