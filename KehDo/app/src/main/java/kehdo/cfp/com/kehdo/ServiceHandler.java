package kehdo.cfp.com.kehdo;

import android.content.Context;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceHandler {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String TAG = "ServiceHandler";


    Context context;

    public ServiceHandler(Context context) {
        this.context = context;

    }


    public int test() throws IOException {

        URL obj = new URL("https://kehdo-9ed65.firebaseio.com/Data.json");

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.addRequestProperty("content-type", "application/json");
        con.addRequestProperty("accept", "application/json");
        int responseCode = con.getResponseCode();
        return responseCode;

    }

    public String doGet(String GET_URL) throws IOException {

        URL obj = new URL(GET_URL);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.addRequestProperty("content-type", "application/json");
        con.addRequestProperty("accept", "application/json");

        int responseCode = con.getResponseCode();

        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));

            String inputLine;

            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            // print result
            //System.out.println(response.toString());

            return response.toString();
        } else if (responseCode == HttpURLConnection.HTTP_FORBIDDEN) {

//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    con.getInputStream()));
//
//            String inputLine = "";
//
//            StringBuffer response = new StringBuffer();
//
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//
//            in.close();
//            return response.toString();

        }

        return null;
    }

    public String doPost(String POST_URL, String POST_PARAMS) throws IOException, JSONException {
        URL obj = new URL(POST_URL);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);

        con.setConnectTimeout(150000);

        con.addRequestProperty("Content-Type", "application/json");

        OutputStream os = con.getOutputStream();

        os.write(POST_PARAMS.getBytes());

        os.flush();

        os.close();

        // For POST only - END

        int responseCode = con.getResponseCode();

        //System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));

            String inputLine = "";

            StringBuffer response = new StringBuffer();


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            return response.toString();

        }


        return null;
    }


}




