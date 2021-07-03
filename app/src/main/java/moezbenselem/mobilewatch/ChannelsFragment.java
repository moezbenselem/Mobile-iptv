package moezbenselem.mobilewatch;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelsFragment extends Fragment {

    ArrayList<Channel> listChannels;
    RecyclerView recyclerChannels;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<Channel> list;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-7087198421941611/7843162043");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                System.out.println("AD CLOSED !!");

            }
        });


        try {

            recyclerChannels = (RecyclerView) view.findViewById(R.id.recycler_channels);
            layoutManager = new LinearLayoutManager(getContext());
            recyclerChannels.setLayoutManager(layoutManager);
            String id = getArguments().getString("id");
            System.out.println("id = " + id);
            getChannels(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_channels, container, false);
    }

    void getChannels(String catId) {
        list = new ArrayList();
        MainActivity.db.collection("categories").document(catId).collection("channels")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println("doc : " + document.getData().get("name"));
                                try {
                                    Channel c = document.toObject(Channel.class);
                                    list.add(c);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            adapter = new RecyclerChannels(list, getContext());
                            recyclerChannels.setAdapter(adapter);
                        }
                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("channels resumed");
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}

