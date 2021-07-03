package moezbenselem.mobilewatch;

import android.graphics.drawable.Drawable;

import org.json.JSONObject;

/**
 * Created by Moez on 02/07/2019.
 */

public class Channel {
    String name,url;
    String logo;


    public Channel() {
    }

    public Channel(String name, String url, String logo) {
        this.name = name;
        this.url = url;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
