package kehdo.cfp.com.kehdo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 9/6/2016.
 */
public class SessionManager
{
    Context mContext;

    SharedPreferences mLoginPrefs;

    SharedPreferences.Editor mEditor;

    private static final String PREFS_NAME = "login_session";

    public static final String KEY_USER_NAME = "user_name";

    public static final String IS_LOGGED_IN = "is_logged_in";

    public static final String KEY_REP_CONTACT = "rep_contact";

    public static final String KEY_USER_CONTACT = "user_contact";

    public static final String KEY_VOTED_LEGISLATIONS = "voted_legislations";

    public SessionManager(Context context)
    {
        mContext = context;

        mLoginPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        mEditor = mLoginPrefs.edit();
    }

    public void storeLoginSession(String userName)
    {
        mEditor.putString(KEY_USER_NAME, userName);

        mEditor.putBoolean(IS_LOGGED_IN, true);

        mEditor.apply();
    }

    public void storeRepInfo(String contactNumber)
    {
        mEditor.putString(KEY_REP_CONTACT, contactNumber);

        mEditor.putBoolean(IS_LOGGED_IN, true);

        mEditor.apply();
    }

    public void storeUserContactInfo(String contactNumber)
    {
        mEditor.putString(KEY_USER_CONTACT, contactNumber);

        mEditor.apply();
    }

    public void setVotedLegislation(String legislationname)
    {
        String leg = getVotedLegislation();
        if(leg!=null) {
            leg = leg + "," + legislationname;
            mEditor.putString(KEY_VOTED_LEGISLATIONS, leg);
        }        else
            mEditor.putString(KEY_VOTED_LEGISLATIONS, legislationname);


  //      mEditor.putBoolean(IS_LOGGED_IN, true);

        mEditor.apply();
    }

    public void setAnswer(String label,String value)
    {
            mEditor.putString(label, value);

            mEditor.apply();
    }

    public String getUserName()
    {
        return mLoginPrefs.getString(KEY_USER_NAME, "");
    }

    public String getRepContact()
    {
        return mLoginPrefs.getString(KEY_REP_CONTACT, "");
    }

    public String getUserContact()
    {
        return mLoginPrefs.getString(KEY_USER_CONTACT, "");
    }

    public String getVotedLegislation()
    {
        return mLoginPrefs.getString(KEY_VOTED_LEGISLATIONS, "");
    }

    public String getAnswer(String label)
    {
        return mLoginPrefs.getString(label, "");
    }

    public boolean isLoggedIn()
    {
        return mLoginPrefs.getBoolean(IS_LOGGED_IN, false);
    }

    public void clearAll(){

        mEditor.clear();
        mEditor.commit();
    }
//    public void logout()
//    {
//        mEditor.clear().apply();
//
//        Intent intent = new Intent(mContext, SplashScreen.class);
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//
//        mContext.startActivity(intent);
//    }
}
