package app.integro.sjbhs.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TextMessageList {

    private String success;

    private String message;

    @SerializedName("sjbhs_msg")
    private ArrayList<TextMessage>textMessageArrayList;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public ArrayList<TextMessage> getTextMessageArrayList() {
        return textMessageArrayList;
    }

    public void setTextMessageArrayList(ArrayList<TextMessage> textMessageArrayList) {
        this.textMessageArrayList = textMessageArrayList;
    }
}
