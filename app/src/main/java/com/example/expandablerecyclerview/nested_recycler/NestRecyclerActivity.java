package com.example.expandablerecyclerview.nested_recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.expandablerecyclerview.R;

import java.util.ArrayList;

/**
 * Reference
 * https://github.com/aimanbaharum/RecyclerViewCeption
 */
public class NestRecyclerActivity extends AppCompatActivity {
    private RecyclerView recyclerViewParent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_list);
        recyclerViewParent = (RecyclerView) findViewById(R.id.rv_parent);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewParent.setLayoutManager(manager);
        recyclerViewParent.setHasFixedSize(true);

        ChildGroupAdapter parentAdapter = new ChildGroupAdapter(this, createData());
        recyclerViewParent.setAdapter(parentAdapter);
    }

    private ArrayList<Child> createData() {
        ArrayList<Child> children = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Child c1 = new Child();
            c1.setChildName("Group 1." + (i + 1));
            children.add(c1);
        }

        Child c2 = new Child();
        c2.setChildName("Group 2. 1");
        c2.setIsFullWidth(true);
        children.add(c2);


        for (int i = 0; i < 3; i++) {
            Child c3 = new Child();
            c3.setChildName("Group 3. " + (i + 1));
            children.add(c3);
        }

        Child c4 = new Child();
        c4.setChildName("Group 4. 1");
        c4.setIsFullWidth(true);
        children.add(c4);


        for (int i = 0; i < 4; i++) {
            Child c5 = new Child();
            c5.setChildName("Group 5." + (i + 1));
            children.add(c5);
        }

        return children;
    }
}
