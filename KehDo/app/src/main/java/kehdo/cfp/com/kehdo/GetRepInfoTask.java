package kehdo.cfp.com.kehdo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by sps on 9/7/16.
 */
public class GetRepInfoTask extends AsyncTask<String, Void, String> {

    ProgressDialog pd;
    Context context;
    String queryId;
    String timeStamp;
    String url;
    OnDataSendToActivity dataSendToActivity;
    public GetRepInfoTask(Context context, String queryId, String timeStamp) {

        dataSendToActivity = (OnDataSendToActivity)context;
        this.context = context;
        // this.queryId = queryId;
        //this.timeStamp = timeStamp;
        this.url = AppConstants.BASE_URL + "RepInfo.json";

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = ProgressDialog.show(context, "", "Loading please wait");

    }

    @Override
    protected String doInBackground(String... params) {

        ServiceHandler serviceHandler = new ServiceHandler(context);
        String resp = "";

        try {
            resp = serviceHandler.doGet(url);

            System.out.println("......RESPONSE....." + resp);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();

        if (s != null && s.length() > 0) {
            try {
                JSONObject testResponse = new JSONObject(s);
                HashMap<String,HashMap<String,RepInfo>> repInfo = Parse.getInstance().parseRepInfo(testResponse);;
                dataSendToActivity.sendData(repInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Handle the Error Condition
            //Todo: Take user to the Login Screen
        }
    }
}



