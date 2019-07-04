package moezbenselem.mobilewatch;

import android.graphics.drawable.Drawable;

import org.json.JSONObject;

/**
 * Created by Moez on 02/07/2019.
 */

public class Channel {
    String name,url;
    String logo;

    public Channel(JSONObject c) {
        try {
            this.name = c.getString("name");
            this.url = c.getString("url").replaceAll("\\b","");
            this.logo = c.getString("logo").replaceAll("\\b","");
            //System.out.println(logo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
