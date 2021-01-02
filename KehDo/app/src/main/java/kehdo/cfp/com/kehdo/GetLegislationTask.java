package kehdo.cfp.com.kehdo;

/**
 * Created by KMajeed on 3/7/2017.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetLegislationTask extends AsyncTask<String, Void, String> {

    ProgressDialog pd;
    Context context;
    String url;
    OnDataSendToActivity dataSendToActivity;

    public GetLegislationTask(Context context, String queryId, String timeStamp) {

        dataSendToActivity = (OnDataSendToActivity)context;
        this.context = context;
        this.url = AppConstants.BASE_URL + "Legislation.json";

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
                HashMap<String,List<Legislation>> legislations = Parse.getInstance().parseLegislation(testResponse);
                dataSendToActivity.sendLegislation(legislations);


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Handle the Error Condition
            //Todo: Take user to the Login Screen


        }
    }
}



