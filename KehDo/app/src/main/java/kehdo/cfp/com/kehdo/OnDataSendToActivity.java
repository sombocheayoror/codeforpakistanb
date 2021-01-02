package kehdo.cfp.com.kehdo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by KMajeed on 3/5/2017.
 */
public interface OnDataSendToActivity {
    public void sendData(HashMap<String, HashMap<String, RepInfo>> str);
    public void sendData(List<Legislation> str);
    public void sendLegislation(HashMap<String, List<Legislation>> str);

}
