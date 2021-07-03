package moezbenselem.mobilewatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Moez on 02/07/2019.
 */

public class RecyclerCategories extends RecyclerView.Adapter {

    Context context;
    ArrayList<Category> listRows;
    HolderCategory viewHolder;

    public RecyclerCategories(ArrayList<Category> listRows, Context context) {
        this.context = context;
        this.listRows = listRows;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_category, parent, false);
        viewHolder = new HolderCategory(v, listRows, context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        viewHolder = (HolderCategory) holder;
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

