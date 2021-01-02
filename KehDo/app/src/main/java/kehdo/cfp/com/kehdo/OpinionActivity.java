package kehdo.cfp.com.kehdo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class OpinionActivity extends AppCompatActivity {

    ArrayList<String> agreeList = new ArrayList<String>();
    ArrayList<String> disagreeList = new ArrayList<String>();
    LinearLayout layoutAgree;
    LinearLayout layoutDisagree;
    ArrayList<CheckBox> chkboxes = new ArrayList<CheckBox>();
    String title = null;
    EditText comments;
    Button btnSubmit;
    ProgressDialog progressDialog=null;
    private static final String[] sendSms =
            {
                    Manifest.permission.SEND_SMS
            };
    private static final String[] readPhoneState =
            {
                    Manifest.permission.READ_PHONE_STATE
            };
    private Context context;
    private SessionManager mSessionManager;
    String parent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion);

        context = this;

        mSessionManager = new SessionManager(this);

        layoutAgree = (LinearLayout)findViewById(R.id.agree);
        layoutDisagree = (LinearLayout)findViewById(R.id.disagree);
        comments = (EditText)findViewById(R.id.comments);
        btnSubmit = (Button)findViewById(R.id.submitbtn);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            title = extras.getString("name");
            parent = extras.getString("parent");
            agreeList = extras.getStringArrayList("agreeList");
            disagreeList = extras.getStringArrayList("disagreeList");
        }

        if(title!=null&&parent!=null)
            getSupportActionBar().setTitle(parent+"("+title+")");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) !=
                    PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(sendSms, 10);
            }
            else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) !=
                            PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(readPhoneState, 11);
                    }
                    else{
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        {
                            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) !=
                                    PackageManager.PERMISSION_GRANTED)
                            {
                                requestPermissions(readPhoneState, 11);
                            }
                            else{}}
                    }
                }
            }
        }
        for(int i=0;i<agreeList.size();i++)
        {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(agreeList.get(i));
            cb.setTextColor(Color.parseColor("#488214"));
            cb.setButtonDrawable(R.drawable.custom_checkbox);
            cb.setTextSize(16);
            layoutAgree.addView(cb);
            chkboxes.add(cb);
        }

        for(int i=0;i<disagreeList.size();i++)
        {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(disagreeList.get(i));
            cb.setTextColor(Color.RED);
            cb.setTextSize(16);
            cb.setButtonDrawable(R.drawable.custom_checkbox);
            layoutDisagree.addView(cb);
            chkboxes.add(cb);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = parent+" -> "+title+"\n";
                for(int i=0;i<chkboxes.size();i++)
                {
                    if(chkboxes.get(i).isChecked())
                    str= str+"\t>\t"+chkboxes.get(i).getText()+"\n";
                    //System.out.println(chkboxes.get(i).isChecked());
                }
                if(comments.getText().toString()!=null&&comments.getText().toString().length()>0)
                    str=str+"Comments : "+comments.getText().toString();

                showLoadingWithMsg();
                sendLongSmsMessage4(mSessionManager.getRepContact(),str,mSessionManager.getUserContact());
//                sendSMS(mSessionManager.getRepContact(),str);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
               this.finish();

                for(int i=0;i<chkboxes.size();i++)
                {
                    System.out.println(chkboxes.get(i).isChecked());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        //  return super.onOptionsItemSelected(item);
    }

    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            if(phoneNo!=null&&msg!=null)
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            else
                Toast.makeText(getApplicationContext(),"Unable to send SMS please try again later.",
                        Toast.LENGTH_LONG).show();
            /*Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();*/
            showDialog(msg);
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case 10:

                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //showPopupEnterMobileNumber();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) !=
                                PackageManager.PERMISSION_GRANTED)
                        {
                            requestPermissions(readPhoneState, 11);
                        }
                        else{}
                    }

                }
                else
                {
                    AppConstants.showToast(this, "Please choose Allow to proceed!"
                            , Toast.LENGTH_LONG);

                    finish();

                }

                break;

            case 11:

                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //showPopupEnterMobileNumber();
                }
                else
                {
                    AppConstants.showToast(this, "Please choose Allow to proceed!"
                            , Toast.LENGTH_LONG);

                    finish();

                }

                break;
        }

        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void showDialog(String msg)
    {
        final String msg1= msg;
        AlertDialog alertDialog = new AlertDialog.Builder(
                OpinionActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("KehDo");

        // Setting Dialog Message
        alertDialog.setMessage("Thank you for sharing your opinion. Every voice matters!");

        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
//                Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
        mSessionManager.setVotedLegislation(title);
                mSessionManager.setAnswer(title,msg1);
        OpinionActivity.this.finish();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    private void sendSMSTest(String phoneNumber, final String message, String contact)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK: {
                     /*   Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                     */
                        hideLoading();
                        showDialog(message);
                    }
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        hideLoading();
                        Toast.makeText(getBaseContext(), "Unable to send SMS please try again later.",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        hideLoading();
                        Toast.makeText(getBaseContext(), "Unable to send SMS please try again later.",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        hideLoading();
                        Toast.makeText(getBaseContext(), "Unable to send SMS please try again later.",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        hideLoading();
                        Toast.makeText(getBaseContext(), "Unable to send SMS please try again later.",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        hideLoading();

                        Toast.makeText(getBaseContext(), "Unable to send SMS please try again later.",
                                Toast.LENGTH_SHORT).show();

                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "Unable to send SMS please try again later.",
                                Toast.LENGTH_SHORT).show();

                                             break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
      //  if(message.length()>160) {
            ArrayList<String> parts = sms.divideMessage(message);
            sms.sendMultipartTextMessage(phoneNumber, contact, parts, null, null);
/*
        }
        else
*/
  //      sms.sendTextMessage(phoneNumber, contact, message, sentPI, deliveredPI);

    }

    public void showLoadingWithMsg()
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sending please wait");
        progressDialog.show();
        //progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //progressDialog.setContentView(new ProgressBar(context));
    }

    public void hideLoading()
    {
        try
        {
            progressDialog.dismiss();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void sendLongSmsMessage4(String phoneNumber, final String message, String contact) {

        // Receive when each part of the SMS has been sent
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // We need to make all the parts succeed before we say we have succeeded.
                switch (getResultCode()) {

                    case Activity.RESULT_OK:
                        hideLoading();
                        showDialog(message);
                        context.unregisterReceiver(this);

                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        hideLoading();
                        Toast.makeText(getBaseContext(), "Unable to send SMS please try again later.",
                                Toast.LENGTH_SHORT).show();
                        context.unregisterReceiver(this);
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        hideLoading();
                        Toast.makeText(getBaseContext(), "Unable to send SMS please try again later.",
                                Toast.LENGTH_SHORT).show();
                        context.unregisterReceiver(this);
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        hideLoading();
                        Toast.makeText(getBaseContext(), "Unable to send SMS please try again later.",
                                Toast.LENGTH_SHORT).show();
                        context.unregisterReceiver(this);
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        hideLoading();
                        Toast.makeText(getBaseContext(), "Unable to send SMS please try again later.",
                                Toast.LENGTH_SHORT).show();
                        context.unregisterReceiver(this);
                        break;
                }

            }
        };

        context.registerReceiver(broadcastReceiver, new IntentFilter("message"));


        SmsManager smsManager = SmsManager.getDefault();

        ArrayList<String> messageParts = smsManager.divideMessage(message);
        ArrayList<PendingIntent> pendingIntents = new ArrayList<PendingIntent>(messageParts.size());
        //nMsgParts = messageParts.size();

        for (int i = 0; i < messageParts.size(); i++) {
            Intent sentIntent = new Intent("message");
            pendingIntents.add(PendingIntent.getBroadcast(context, 0, sentIntent, 0));
        }

       // Log.i(LOG_TAG, "About to send multi-part message Id: " + messageInfo.getMessageId());
        smsManager.sendMultipartTextMessage(phoneNumber, contact, messageParts, pendingIntents, null);
    }
}
