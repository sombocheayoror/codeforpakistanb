package kehdo.cfp.com.kehdo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements OnDataSendToActivity {

    private List<Legislation> LegList = new ArrayList<Legislation>();
    private RecyclerView recyclerView;
    private legislationlistAdapter mAdapter;
    private TextView txtviewOpinion;
    private TextView txtviewInfo;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

       // mAdapter = new legislationlistAdapter(LegList);
        context = this;

    //    prepareIssueData();
        GetLegislationDataTask task = new GetLegislationDataTask(context, "", "");
        task.execute();

        txtviewInfo= (TextView)findViewById(R.id.info);
       /* txtviewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);


            }
        });*/


        txtviewOpinion = (TextView)findViewById(R.id.opinion);
       /* txtviewOpinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);


            }
        });
            */



    }
    public void OpnionClick(View v){


        Intent intent = new Intent(HomeActivity.this, OpinionActivity.class);
        startActivity(intent);

    }
    public void InfoClick(View v){

        Intent intent = new Intent(HomeActivity.this, OpinionActivity.class);
        startActivity(intent);






    }
/*    private void prepareIssueData() {
        LegislationList item1 = new LegislationList("Cyber Crime", "Info", "Opinion");
        LegList.add(item1);

        item1 = new LegislationList("Cyber Crime", "Info", "Opinion");
        LegList.add(item1);

        item1 = new LegislationList("Cyber Crime", "Info", "Opinion");
        LegList.add(item1);




        mAdapter.notifyDataSetChanged();
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_Profile) {
            return true;
        }
        if (id == R.id.History) {
            return true;
        }*/
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


      //  return super.onOptionsItemSelected(item);
    }
    public  boolean SettingsMain(MenuItem item){
        //  Intent profileIntent = new Intent(MainActivity.this, ProfileMain.class);

        //   startActivity(profileIntent);
        return true;

    }
    public  boolean ProfileMain(MenuItem item){
        Intent profileIntent = new Intent(HomeActivity.this, ProfileMain.class);

        startActivity(profileIntent);
        return true;

    }

    @Override
    public void sendData(HashMap<String,HashMap<String,RepInfo>> str) {
        // TODO Auto-generated method stub

    }
    @Override
    public void sendLegislation(HashMap<String,List<Legislation>> str) {
    }
        @Override
    public void sendData(List<Legislation> str) {
        // TODO Auto-generated method stub
        LegList = str;
        mAdapter = new legislationlistAdapter(getApplicationContext(),LegList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
       // mAdapter.notifyDataSetChanged();
        GetLegislationTask task = new GetLegislationTask(context, "", "");
        task.execute();

    }
}
