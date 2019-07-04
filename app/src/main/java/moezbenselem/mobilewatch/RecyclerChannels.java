package moezbenselem.mobilewatch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Moez on 02/07/2019.
 */

public class RecyclerChannels extends RecyclerView.Adapter {

    Context context;
    ArrayList<Channel> listRows;
    HolderChannel viewHolder;

    public RecyclerChannels(ArrayList<Channel> listRows,Context context) {
        this.context = context;
        this.listRows = listRows;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_channel, parent, false);
        viewHolder = new HolderChannel(v,listRows,context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        viewHolder = (HolderChannel) holder;
        if(listRows.get(position).name.contains("Bein Sports Max"))
            viewHolder.channel_name.setText(listRows.get(position).name);

        Picasso.with(context).load(listRows.get(position).logo)
                //.networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.drawable.loading).into(viewHolder.logo, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {


            }
        });
    }

    @Override
    public int getItemCount() {
        return listRows.size();
    }
}

