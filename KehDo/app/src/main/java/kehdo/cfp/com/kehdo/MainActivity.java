package kehdo.cfp.com.kehdo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDataSendToActivity {

    private Button submitbtn;
    Spinner spinnertype,spinnerrep;
    HashMap<String,HashMap<String,RepInfo>> loginReturned = null;
    ProgressDialog progressDialog = null;
    private Context context;
    private SessionManager mSessionManager;
   // EditText etContact;
    TextView etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icon);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        submitbtn =   (Button) findViewById(R.id.submitbtn);
        spinnertype =   (Spinner) findViewById(R.id.spinnertype);
        spinnerrep =   (Spinner) findViewById(R.id.spinnerrep);
        etName = (TextView)findViewById(R.id.editText);

        context = this;
        mSessionManager = new SessionManager(this);

        if(mSessionManager.isLoggedIn())
        {
            //Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            Intent intent = new Intent(getApplicationContext(),LegislationActivity.class);
            startActivity(intent);
            this.finish();
        }
        else {

            if(mSessionManager.getUserContact()!=null&&mSessionManager.getUserContact().length()==11) {
                GetRepInfoTask task = new GetRepInfoTask(context, "", "");
                task.execute();
            }
            else
            {
                showPopupEnterMobileNumber();
            }
        }

/*
        LoginAsyncTask task = new LoginAsyncTask(getApplicationContext());
        task.execute();
*/

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

      //      if(etContact.getText().toString()!=null&& etContact.getText().toString().length()==11)
                showDialog();
      /*          else
            Toast.makeText(getApplicationContext(),"Please enter valid Mobile Number",Toast.LENGTH_SHORT).show();
*/

            }
        });

        spinnertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String key = adapterView.getItemAtPosition(i).toString();


                Collection<String> vals = loginReturned.get(key).keySet();
                String[] array = vals.toArray(new String[vals.size()]);

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, array);
                dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                spinnerrep.setAdapter(dataAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerrep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String typekey = spinnertype.getSelectedItem().toString();
                String key = spinnerrep.getSelectedItem().toString();
                RepInfo info = loginReturned.get(typekey).get(key);


                etName.setText(info.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
/*
    private void populateUI() {

        Collection<String> vals = loginReturned.keySet();
        String[] array = vals.toArray(new String[vals.size()]);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, array);
        spinnertype.setAdapter(dataAdapter);
    }

    class LoginAsyncTask extends AsyncTask<Void, Void, HashMap<String,HashMap<String,RepInfo>>> {
        String login,pwd;
        private Context ctxt;
        final ProgressDialog myPd_ring = ProgressDialog.show(
                MainActivity.this, "Signing in ", "Please wait..", true);

        public LoginAsyncTask(Context ctxt) {
            this.ctxt = ctxt;
        }

        @Override
        protected void onPreExecute() {
//            myPd_ring.setContentView(R.layout.activity_load);
            myPd_ring.setCancelable(false);
            myPd_ring.setCanceledOnTouchOutside(false);
            myPd_ring.show();
        }

        @Override
        protected HashMap<String,HashMap<String,RepInfo>> doInBackground(Void... credentials) {

            HashMap<String,HashMap<String,RepInfo>> loginObjToReturn = null;
            try {
                BTFTSServices service = new BTFTSServices(
                        getApplicationContext());
                loginObjToReturn = service.GetRepInfo();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return loginObjToReturn;
        }

        @Override
        protected void onPostExecute(HashMap<String,HashMap<String,RepInfo>> loginReturned1) {

            //Log.i("login", loginReturned);
            loginReturned = loginReturned1;
            if ((loginReturned!=null)) {

				*//*XMPPService.mode = "Login";
				Intent intent = new Intent(".BTFTS.ACTION");
				if (!XMPPService.isRunning(getApplicationContext())) {
					startService(intent);
				}*//*

               *//* Collection<String> vals = loginReturned.keySet();
                String[] array = vals.toArray(new String[vals.size()]);
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, array);
                spinnertype.setAdapter(dataAdapter);*//*

                Toast.makeText(getApplicationContext(),"returned",Toast.LENGTH_LONG).show();

               *//* SharedPreferences sharedPreferences = getSharedPreferences(
                        "MyPrefs", Context.MODE_PRIVATE);

                Editor editor = sharedPreferences.edit();
                editor.putString("user_id", loginReturned[1]);
                editor.putString("user_name", login);
//				editor.putString("user_pwd", pwd);
//				editor.putBoolean("logged_in", true);
                editor.commit();

                Intent intent1 = new Intent(getApplicationContext(),
                        MainScreen.class);
                startActivity(intent1);
                finish();*//*
            } else {
				*//*if (loginReturned.contains("login"))
					etLogin.setError(Html
							.fromHtml("<font color='red'>"+loginReturned+"</font>"));
				if (loginReturned.contains("password"))
					etPwd.setError(Html
							.fromHtml("<font color='red'>"+loginReturned+"</font>"));
				else
					etLogin.setError(Html
							.fromHtml("<font color='red'>"+loginReturned+"</font>"));

				*//*
                *//*if(loginReturned==null)
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Failed to connect to service.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0); toast.show();
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            loginReturned[0], Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0); toast.show();
                }*//*
            }
            myPd_ring.dismiss();

        }

    }*/
@Override
public void sendLegislation(HashMap<String,List<Legislation>> str) {
}
    @Override
    public void sendData(HashMap<String,HashMap<String,RepInfo>> str) {
        // TODO Auto-generated method stub
        loginReturned = str;
        Collection<String> vals = loginReturned.keySet();
        String[] array = vals.toArray(new String[vals.size()]);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, array);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnertype.setAdapter(dataAdapter);
    }

    @Override
    public void sendData(List<Legislation> str) {
        // TODO Auto-generated method stub

    }

    public void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("KehDo");

        // set dialog message
        alertDialogBuilder
                .setMessage("Are you sure to save your constituency.")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        String typekey = spinnertype.getSelectedItem().toString();
                        String key = spinnerrep.getSelectedItem().toString();
                        RepInfo info = loginReturned.get(typekey).get(key);
                        mSessionManager.storeRepInfo(info.getPhoneNumber());
                      //  mSessionManager.storeUserContactInfo(etContact.getText().toString());
                        Intent intent = new Intent(getApplicationContext(),LegislationActivity.class);
                        startActivity(intent);

                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void showPopupEnterMobileNumber()
    {
        final Dialog dialog;
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);

        View v = LayoutInflater.from(this).inflate(R.layout.authenticate_dialog, null);

        final EditText et = (EditText)  v.findViewById(R.id.etPhoneNo);
        et.setInputType(InputType.TYPE_CLASS_PHONE);
        Button btnVerify = (Button)  v.findViewById(R.id.btnVerify);

        mBuilder.setView(v);

        dialog = mBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        //window.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        btnVerify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String num = et.getText().toString();

                if (num!=null&&num.trim().length() == 11)
                {
/*
                    authUserImeiNumber(num);
*/
                    mSessionManager.storeUserContactInfo(num);
                    dialog.dismiss();
                    GetRepInfoTask task = new GetRepInfoTask(context, "", "");
                    task.execute();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Please enter 11 digit valid phone number like 03001234567",
                            Toast.LENGTH_SHORT).show();

/*                    try
                    {
                        dialog.dismiss();
                        showPopupEnterMobileNumber();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }*/
                }
            }
        });

        dialog.show();
    }
}
