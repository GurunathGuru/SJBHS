package app.integro.sjbhs.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Sjbhs_photos1List {
    private String message;

   @SerializedName("sjbhs_photos")
    ArrayList<Sjbhs_photos1> sjbhs_photos1ArrayList;

    public ArrayList<Sjbhs_photos1> getSjbhs_photos1ArrayList() {
        return sjbhs_photos1ArrayList;
    }

    public void setSjbhs_photos1ArrayList(ArrayList<Sjbhs_photos1> sjbhs_photos1ArrayList) {
        this.sjbhs_photos1ArrayList = sjbhs_photos1ArrayList;
    }

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
