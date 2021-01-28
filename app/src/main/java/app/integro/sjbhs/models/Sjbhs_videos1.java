package app.integro.sjbhs.models;

import java.io.Serializable;

public class Sjbhs_videos1 implements Serializable {

    private String id;

    private String title;

    private String updated_at;

    private String v_id;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getV_id ()
    {
        return v_id;
    }

    public void setV_id (String v_id)
    {
        this.v_id = v_id;
    }
}
