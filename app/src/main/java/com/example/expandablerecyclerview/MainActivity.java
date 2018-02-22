package com.example.expandablerecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.expandablerecyclerview.expandable_list.ExpandableListActivity;
import com.example.expandablerecyclerview.nested_recycler.NestRecyclerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nestedRecyclerView(View v) {
        startActivity(new Intent(this, NestRecyclerActivity.class));
    }

    public void expandableList(View v) {
        startActivity(new Intent(this, ExpandableListActivity.class));
    }

}
