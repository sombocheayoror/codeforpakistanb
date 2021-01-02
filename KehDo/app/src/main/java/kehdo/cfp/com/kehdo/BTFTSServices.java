package kehdo.cfp.com.kehdo;

import java.io.InputStream;


import java.io.InputStreamReader;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BTFTSServices {

	public static String SERVICE_URI = "https://kehdo-9ed65.firebaseio.com/";//"http://10.11.11.29:8005/api/app/";
	
	public BTFTSServices(Context ctxt) {
	}
	
	public String[] CheckLogin(String userName, String pwd) throws JSONException {

		String[] objToReturn = null;
		
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet("http://"+SERVICE_URI+"/api/app/AuthenticateUser?Username="+userName+"&Password="+pwd);

			
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");

			HttpResponse response = httpClient.execute(request);

			HttpEntity responseEntity = response.getEntity();

			// Read response data into buffer
			char[] buffer = new char[(int) responseEntity.getContentLength()];
			InputStream stream = responseEntity.getContent();
			InputStreamReader reader = new InputStreamReader(stream);
			reader.read(buffer);
			stream.close();

			JSONArray jArray = new JSONArray(new String(buffer));

			List<String> list = new ArrayList<String>();
			for (int i=0; i<jArray.length(); i++) {
			    list.add( jArray.getString(i) );
			}
		    objToReturn = list.toArray(new String[list.size()]);
			
			//objToReturn = new String(buffer);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objToReturn;
	}

	public  HashMap<String,HashMap<String,RepInfo>> GetRepInfo() throws JSONException {

		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet(SERVICE_URI + "RepInfo.json");

			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");

			HttpResponse response = httpClient.execute(request);

			HttpEntity responseEntity = response.getEntity();

			// Read response data into buffer
			char[] buffer = new char[(int) responseEntity.getContentLength()];
			InputStream stream = responseEntity.getContent();
			InputStreamReader reader = new InputStreamReader(stream);
			reader.read(buffer);
			stream.close();

			JSONObject file = new JSONObject(new String(buffer));

			Iterator<String> iterator = file.keys();
			HashMap<String, HashMap<String,RepInfo>> repInfo = new HashMap<String, HashMap<String,RepInfo>>();
			HashMap<String,RepInfo> rep = null;
			RepInfo info = null;
			while(iterator.hasNext())
			{
				String key = iterator.next();
				rep = new HashMap<String, RepInfo>();
				JSONObject obj = file.optJSONObject(key);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
	}

}
