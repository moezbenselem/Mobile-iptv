package moezbenselem.mobilewatch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Moez on 02/07/2019.
 */

public class HolderCategory  extends RecyclerView.ViewHolder {

    public ImageView logo;
    static Context context;

    public HolderCategory(View itemView, final ArrayList<Category> rows, final Context context){
        super(itemView);
        this.context = context;
        this.logo = (ImageView) itemView.findViewById(R.id.imageView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {int position = getAdapterPosition();
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", rows.get(position).id);

                    Fragment myFragment = new ChannelsFragment();
                    myFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.content_main, myFragment).addToBackStack(null).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
