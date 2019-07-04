package moezbenselem.mobilewatch;

import org.json.JSONObject;

/**
 * Created by Moez on 02/07/2019.
 */

public class Category {
    int id;
    String category,logo;

    public Category(int id, String category, String logo) {
        this.id = id;
        this.category = category;
        this.logo = logo;
    }

    public Category(JSONObject cat) {
        try {
            this.id = cat.getInt("id");
            this.category = cat.getString("category");
            this.logo = cat.getString("logo").replaceAll("\\b","");
            //System.out.println("logo : "+logo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
