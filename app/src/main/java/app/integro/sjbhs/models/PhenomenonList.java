package app.integro.sjbhs.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PhenomenonList {

    private String success;

    @SerializedName("sjbhs_phenomenon")
    private ArrayList<Phenomenon> phenomenonArrayList;

    private String message;

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public ArrayList<Phenomenon> getPhenomenonArrayList() {
        return phenomenonArrayList;
    }

    public void setPhenomenonArrayList(ArrayList<Phenomenon> phenomenonArrayList) {
        this.phenomenonArrayList = phenomenonArrayList;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }
}
