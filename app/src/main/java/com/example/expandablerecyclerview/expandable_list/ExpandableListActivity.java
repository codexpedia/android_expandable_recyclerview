package com.example.expandablerecyclerview.expandable_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.expandablerecyclerview.R;

import java.util.ArrayList;

public class ExpandableListActivity extends AppCompatActivity implements ListItemListener {
    ArrayList<Place> places;
    CardView cvContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);

        places = new ArrayList<>();
        for (int i=0; i<6; i++) {
            places.add(new Place("place " + i));
        }

        cvContainer = (CardView) findViewById(R.id.card_background);
        RecyclerView rvList = (RecyclerView) findViewById(R.id.rv_list);
        PlaceArrayAdapter placeArrayAdapter = new PlaceArrayAdapter(places);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setItemAnimator(new DefaultItemAnimator());
        rvList.setAdapter(placeArrayAdapter);

        placeArrayAdapter.setListItemListener(this);


        rvList.post(new Runnable() {
            @Override
            public void run() {
                int baseHeight =  Math.round(getResources().getDimension(R.dimen.place_item_height));
                int collapseAmount = baseHeight * (places.size() - 1);
                ExpandableUtil.collapseWithNoAnimation(cvContainer, collapseAmount);
            }
        });

    }
    @Override
    public void onExpand() {
        int from =  Math.round(getResources().getDimension(R.dimen.place_item_height));
        int to = from * places.size();
        ExpandableUtil.expand(cvContainer, from, to);
    }

    @Override
    public void onCollapse() {
        int baseHeight =  Math.round(getResources().getDimension(R.dimen.place_item_height));
        int collapseAmount = baseHeight * (places.size() - 1);
        ExpandableUtil.collapse(cvContainer, collapseAmount);
    }
}
