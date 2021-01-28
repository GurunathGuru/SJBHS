package app.integro.sjbhs.models;

import java.io.Serializable;

public class TextMessage implements Serializable {

    private String msg;

    private String updated_at;

    private String id;

    private String weblink;

    public String getWebLink() {
        return weblink;
    }

    public void setWebLink(String webLink) {
        this.weblink = webLink;
    }

    public String getMsg ()
    {
        return msg;
    }

    public void setMsg (String msg)
    {
        this.msg = msg;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }


}
