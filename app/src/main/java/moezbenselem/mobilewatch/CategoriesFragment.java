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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {

    ArrayList<Channel> listChannels;
    RecyclerView recyclerCategories;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<Category> list;
    private AdView mAdView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //.addTestDevice("F8EDF04F845FAB4C6D9652E530EBF593")
        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        try {

            recyclerCategories = view.findViewById(R.id.recycler_categories);

            layoutManager = new LinearLayoutManager(getContext());

            recyclerCategories.setLayoutManager(layoutManager);

            getCategories();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    void getCategories() {
        try {
            list = new ArrayList();
            MainActivity.db.collection("categories")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    System.out.println("doc : " + document.getData().get("logo"));
                                    try {
                                        Category c = document.toObject(Category.class);
                                        c.setId(document.getId());
                                        list.add(c);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                                adapter = new RecyclerCategories(list, getContext());
                                recyclerCategories.setAdapter(adapter);
                            }
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

