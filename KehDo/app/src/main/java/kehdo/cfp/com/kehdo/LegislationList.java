package kehdo.cfp.com.kehdo;

/**
 * Created by maheen on 3/4/2017.
 */

public class LegislationList {


    private String title, info, Opinion;

    public LegislationList() {
    }

    public LegislationList(String title, String info, String Opinion) {
        this.title = title;
        this.info = info;
        this.Opinion =Opinion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getOpinion() {
        return Opinion;
    }

    public void setOpinion(String Opinion) {
        this.Opinion = Opinion;
    }



}
