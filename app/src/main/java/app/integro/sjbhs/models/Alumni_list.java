package app.integro.sjbhs.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Alumni_list {

    @SerializedName("sjbhs_alumni")
    private ArrayList<Alumni> alumniArrayList;

    public ArrayList<Alumni> getAlumniArrayList() {
        return alumniArrayList;
    }

    public void setAlumniArrayList(ArrayList<Alumni> alumniArrayList) {
        this.alumniArrayList = alumniArrayList;
    }

    private String message;


    private String success;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

}
