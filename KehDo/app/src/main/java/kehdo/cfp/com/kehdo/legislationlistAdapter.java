package kehdo.cfp.com.kehdo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by maheen on 3/4/2017.
 */

public class legislationlistAdapter extends RecyclerView.Adapter<legislationlistAdapter.MyViewHolder> {

    private List<kehdo.cfp.com.kehdo.Legislation> LegislationList;
    Context ctxt = null;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, info, opinion;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Legislation leg = LegislationList.get(getPosition());
                    /*TextView tv = (TextView) v.findViewById(R.id.item_name);
                    String id = tv.getText().toString();
                    mItemClickListener.onItemClick(getLayoutPosition(), v, id);
                    */
                    Intent intent = new Intent(ctxt,OpinionActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("name",leg.getName());
                    intent.putStringArrayListExtra("agreeList",leg.agree);
                    intent.putStringArrayListExtra("disagreeList",leg.disagree);
                    ctxt.startActivity(intent);
                }
            });
            title = (TextView) view.findViewById(R.id.title);
            info = (TextView) view.findViewById(R.id.info);
            opinion = (TextView) view.findViewById(R.id.opinion);
        }
    }


    public legislationlistAdapter(Context ctxt,List<kehdo.cfp.com.kehdo.Legislation> LegislationList) {
        this.LegislationList = LegislationList;
        this.ctxt = ctxt;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.legislationlistlayout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        kehdo.cfp.com.kehdo.Legislation Llist = LegislationList.get(position);
        holder.title.setText(Llist.getTitle());
        holder.info.setText(Llist.getInfo());
        holder.opinion.setText(Llist.getName());
    }

    @Override
    public int getItemCount() {
        return LegislationList.size();
    }

}