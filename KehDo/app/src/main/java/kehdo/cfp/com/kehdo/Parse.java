package kehdo.cfp.com.kehdo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gulafshan on 04/03/2017.
 */
public class Parse {
    private static Parse ourInstance = new Parse();

    public static Parse getInstance() {
        return ourInstance;
    }

    private Parse() {
    }

    public HashMap<String,HashMap<String,RepInfo>> parseRepInfo(JSONObject jsonObject) {
        if (jsonObject == null)
            return null;

        Iterator<String> iterator = jsonObject.keys();
        HashMap<String, HashMap<String,RepInfo>> repInfo = new HashMap<String, HashMap<String,RepInfo>>();
        HashMap<String,RepInfo> rep = null;
        RepInfo info = null;
        while(iterator.hasNext())
        {
            String key = iterator.next();
            rep = new HashMap<String, RepInfo>();
            JSONObject obj = jsonObject.optJSONObject(key);

            Iterator<String> it = obj.keys();

            while(it.hasNext()) {
                info = new RepInfo();
                String key1 = it.next();
                JSONObject ob = obj.optJSONObject(key1);

                info.setName(ob.optString("Name"));
                info.setAddress(ob.optString("Address"));
                info.setParty(ob.optString("Party"));
                info.setPhoneNumber(ob.optString("Phone Number"));

                rep.put(key1,info);
            }
            repInfo.put(key,rep);
        }
        return repInfo;
    }

    public List<Legislation> parseLegislationObj(JSONObject jsonObject) {
        if (jsonObject == null)
            return null;

        List<Legislation> legislationList = new ArrayList<Legislation>();
        Iterator<String> iterator = jsonObject.keys();

        while (iterator.hasNext()) {

            String key = iterator.next();

            try {
                if (jsonObject.get(key) instanceof JSONObject) {

                    JSONObject obj1 = (JSONObject) jsonObject.get(key);

                    Legislation legislation = new Legislation();

                    legislation.setId(obj1.optString("ID").toString());
                    legislation.setTitle(obj1.optString("Title").toString());
                    legislation.setInfo(obj1.optString("Info").toString());
                    legislation.setName(obj1.optString("Name").toString());

                    System.out.println("LEGISLATION : " + legislation.getTitle());

                    JSONObject answers = obj1.optJSONObject("Answers");
                    JSONObject agreeAnswers = answers.optJSONObject("Agree");
                    JSONObject disagreeAnswers = answers.optJSONObject("Disagree");

                    Iterator<String> it = agreeAnswers.keys();
                    while (it.hasNext()) {
                        String key1 = it.next();
                        legislation.agree.add(agreeAnswers.optString(key1));
                    }

                    Iterator<String> itd = agreeAnswers.keys();
                    while (itd.hasNext()) {
                        String key1 = itd.next();
                        legislation.disagree.add(disagreeAnswers.optString(key1));
                    }

                    legislationList.add(legislation);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return legislationList;
    }

    public HashMap<String,List<Legislation>> parseLegislation(JSONObject jsonObject) {
        if (jsonObject == null)
            return null;

        List<Legislation> legislationList = null;//new ArrayList<Legislation>();
        HashMap<String,List<Legislation>> legislations = new HashMap<String,List<Legislation>>();

        Iterator<String> iteratorLeg = jsonObject.keys();

        while (iteratorLeg.hasNext()) {

            String keyLeg = iteratorLeg.next();

            try {
                if (jsonObject.get(keyLeg) instanceof JSONObject) {
                    JSONObject objLeg = (JSONObject) jsonObject.get(keyLeg);

                    Iterator<String> iterator = objLeg.keys();
                    legislationList = new ArrayList<Legislation>();
                    while (iterator.hasNext()) {

                        String key = iterator.next();

                        try {
                            if (objLeg.get(key) instanceof JSONObject) {

                                JSONObject obj1 = (JSONObject) objLeg.get(key);

                                Legislation legislation = new Legislation();

                                legislation.setId(obj1.optString("ID").toString());
                                legislation.setTitle(key);
                                legislation.setInfo(obj1.optString("Info").toString());
                                legislation.setName(obj1.optString("Name").toString());

                                System.out.println("LEGISLATION : " + legislation.getTitle());

                                JSONObject answers = obj1.optJSONObject("Answers");
                                JSONObject agreeAnswers = answers.optJSONObject("Agree");
                                JSONObject disagreeAnswers = answers.optJSONObject("Disagree");

                                Iterator<String> it = agreeAnswers.keys();
                                while (it.hasNext()) {
                                    String key1 = it.next();
                                    legislation.agree.add(agreeAnswers.optString(key1));
                                }

                                Iterator<String> itd = agreeAnswers.keys();
                                while (itd.hasNext()) {
                                    String key1 = itd.next();
                                    legislation.disagree.add(disagreeAnswers.optString(key1));
                                }

                                legislationList.add(legislation);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
                legislations.put(keyLeg,legislationList);
            } catch (JSONException ex) {
            }
        }
        return legislations;
    }

}
