package moezbenselem.mobilewatch;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelsFragment extends Fragment {

    private AdView mAdView;
    ArrayList<Channel> listChannels;
    RecyclerView recyclerChannels;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
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

        recyclerChannels = (RecyclerView)view.findViewById(R.id.recycler_channels);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerChannels.setLayoutManager(layoutManager);
        int id = getArguments().getInt("id");
        System.out.println("id = "+id);
        getChannels(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_channels, container, false);
    }
    ArrayList<Channel> list;
    void getChannels(int id){
        list = new ArrayList();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://www.debdev.tk/Mobiletv/getChannels.php?id="+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            response = response.replaceAll("<div style=\"text-align: right;position: fixed;top: 5px;right:5px;width: 100%;z-index:999999;cursor: pointer;line-height: 0;display:block;\"><a target=\"_blank\" href=\"https://www.freewebhostingarea.com\" title=\"Free Web Hosting with php7\"><img alt=\"Free Web Hosting\" width=\"350\" height=\"25\" src=\"https://www.freewebhostingarea.com/images/poweredby.gif\" style=\"border-width: 0px;\"></a></div>","");
                            //System.out.println(response);
                            JSONArray jsonArray = new JSONArray(response);

                            //System.out.println(jsonArray);
                            for (int i = 0;i<jsonArray.length();i++){
                                //System.out.println(jsonArray.getJSONObject(i));
                                Channel c = new Channel(jsonArray.getJSONObject(i));
                                list.add(c);
                            }
                            adapter = new RecyclerChannels(list,getContext());
                            recyclerChannels.setAdapter(adapter);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<>();

                return params;
            }

            /**
             * Passing some request headers
             */
        };

        {
            int socketTimeout = 30000;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(policy);
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("channels resumed");
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }
}

