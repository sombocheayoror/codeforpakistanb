package kehdo.cfp.com.kehdo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KMajeed on 3/4/2017.
 */

public class Legislation {

/*
    {"Issue":{"Answers":{"Agree":{"Reason1":"Just loved the idea should be implemented as quickle as possible","Reason2":"I think its fine"},"Disagree":{"Reason1":"Still need some convincing","Reason2":"I just dont like the idea"}},"ID":1,"Info":"http://na.gov.pk/en/all_members.php","Name":"Cyber crime bill","Title":"Cyber crime"}}
*/

//    "Answers":{
//        "Agree":{
//            "Reason1":"Just loved the idea should be implemented as quickle as possible",
//                    "Reason2":"I think its fine"
//        },
//        "Disagree":{
//            "Reason1":"Still need some convincing",
//                    "Reason2":"I just dont like the idea"
//        }
//    },

    private String id;
    private String name;
    private String info;
    private String title;
    public ArrayList<String> agree = new ArrayList<>();
    public ArrayList<String> disagree = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
