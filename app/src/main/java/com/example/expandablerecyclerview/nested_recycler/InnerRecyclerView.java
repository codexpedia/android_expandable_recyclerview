package com.example.expandablerecyclerview.nested_recycler;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Toast;


import com.example.expandablerecyclerview.R;

import java.util.ArrayList;

/**
 * Created by peng on 2/11/17.
 */

public class InnerRecyclerView extends CardView implements ChildAdapter.ChildItemListener {
    public enum Expandable {
        EXPAND, COLLPASE;
    }
    RecyclerView rvChild;

    public InnerRecyclerView (Context context) {
        super(context);
    }

    public InnerRecyclerView (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InnerRecyclerView (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initViews() {


        //get recycler view
        rvChild = (RecyclerView) findViewById(R.id.rv_child);
        rvChild.setLayoutManager(new NestedRecyclerLinearLayoutManager(getContext()));

        //create data for recycler view
        ArrayList<Child> childList = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            Child c = new Child();
            c.setChildName("Child List " + (j + 1));
            childList.add(c);
        }

        //set adapter for the recycler view
        ChildAdapter childAdapter = new ChildAdapter(childList);
        rvChild.setAdapter(childAdapter);

        childAdapter.setChildItemListener(this);
    }


    @Override
    public void onItemClick(Expandable expandable) {
        Toast.makeText(getContext(), "item clicked", Toast.LENGTH_SHORT).show();
    }
}
