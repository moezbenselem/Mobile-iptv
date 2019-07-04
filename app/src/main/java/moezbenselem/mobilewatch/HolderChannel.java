package moezbenselem.mobilewatch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Moez on 02/07/2019.
 */

public class HolderChannel extends RecyclerView.ViewHolder {

    public TextView channel_name;
    public ImageView logo;
    static Context context;

    public HolderChannel(View itemView, final ArrayList<Channel> rows, final Context context){
        super(itemView);
        this.context = context;
        this.channel_name = (TextView)itemView.findViewById(R.id.channel_name);
        this.logo = itemView.findViewById(R.id.imageView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {int position = getAdapterPosition();
                    Intent toPlayer = new Intent(context, PlayerActivity.class);
                    toPlayer.putExtra("link", rows.get(position).url);
                    context.startActivity(toPlayer);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
