package moezbenselem.mobilewatch;

import org.json.JSONObject;

/**
 * Created by Moez on 02/07/2019.
 */

public class Category {

    String id,category,logo;

    public Category() {
    }

    public Category(String id, String category, String logo) {
        this.id = id;
        this.category = category;
        this.logo = logo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
