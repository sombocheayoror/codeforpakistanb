package kehdo.cfp.com.kehdo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class LegislationActivity extends AppCompatActivity implements OnDataSendToActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<Legislation>> listDataChild;
    Context context;
    SessionManager mSessionManager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislation);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.icon);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        context = this;
        mSessionManager = new SessionManager(this);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        //   prepareListData();
        GetLegislationTask task = new GetLegislationTask(this, "", "");
        task.execute();

        // setting list adapter
        //expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                /*Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();*/
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
/*                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();*/

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
/*
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition).getName(), Toast.LENGTH_SHORT)
                        .show();
*/
                String voted = mSessionManager.getVotedLegislation();
                if (voted != null && voted.contains(listDataChild.get(
                        listDataHeader.get(groupPosition)).get(
                        childPosition).getName())) {
                    String ans = mSessionManager.getAnswer(listDataChild.get(
                            listDataHeader.get(groupPosition)).get(
                            childPosition).getName());
                    if (ans != null)
                        showDialog(ans);
                    else
                        Toast.makeText(getApplicationContext(), "Already voted", Toast.LENGTH_SHORT).toString();
                } else {
                    Intent intent = new Intent(getApplicationContext(), OpinionActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("parent", listDataHeader.get(groupPosition));
                    intent.putExtra("name", listDataChild.get(
                            listDataHeader.get(groupPosition)).get(
                            childPosition).getName());
                    intent.putStringArrayListExtra("agreeList", listDataChild.get(
                            listDataHeader.get(groupPosition)).get(
                            childPosition).agree);
                    intent.putStringArrayListExtra("disagreeList", listDataChild.get(
                            listDataHeader.get(groupPosition)).get(
                            childPosition).disagree);
                    startActivity(intent);
                }
                return false;
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void sendData(HashMap<String, HashMap<String, RepInfo>> str) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendData(List<Legislation> str) {
        // TODO Auto-generated method stub
    /*    LegList = str;
        mAdapter = new legislationlistAdapter(getApplicationContext(),LegList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    */    // mAdapter.notifyDataSetChanged();
      /*  GetLegislationTask task = new GetLegislationTask(context, "", "");
        task.execute();
*/

        // listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

    }

    @Override
    public void sendLegislation(HashMap<String, List<Legislation>> str) {
        // TODO Auto-generated method stub
    /*    LegList = str;
        mAdapter = new legislationlistAdapter(getApplicationContext(),LegList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    */    // mAdapter.notifyDataSetChanged();
      /*  GetLegislationTask task = new GetLegislationTask(context, "", "");
        task.execute();
*/
        listDataHeader = new ArrayList<String>();
        listDataHeader.addAll(str.keySet());
        listDataChild = str;//new HashMap<String,List<Legislation>>();
//        listDataChild
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        if (listDataHeader.size() > 0)
            expListView.expandGroup(0);
    }

    public void showDialog(String info) {
        final AlertDialog alertDialog = new AlertDialog.Builder(
                LegislationActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("KehDo");

        // Setting Dialog Message
        alertDialog.setMessage(info);

        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
//                Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();

            }
        });
        alertDialog.show();
    }

    public void onResume() {
        super.onResume();

        if (listAdapter != null)
            listAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem Item) {
        switch (Item.getItemId()) {

            case R.id.action_clear:
                mSessionManager.clearAll();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
            default:
                return super.onOptionsItemSelected(Item);
        }

        return true;

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Legislation Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

