package com.example.expandablerecyclerview.expandable_list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.expandablerecyclerview.R;
import java.util.ArrayList;

public class PlaceArrayAdapter extends RecyclerView.Adapter<PlaceArrayAdapter.ViewHolder> {
    private ArrayList<Place> places;
    private ListItemListener listItemListener;
    private boolean isExpanded = false;

    public PlaceArrayAdapter(ArrayList<Place> places) {
        this.places = places;
    }

    public void setListItemListener(ListItemListener listItemListener) {
        this.listItemListener = listItemListener;
    }


    // get the size of the list
    @Override
    public int getItemCount() {
        return places == null ? 0 : places.size();
    }


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_place_item, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.item;
        item.setText(places.get(listPosition).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    listItemListener.onCollapse();
                    isExpanded = false;
                } else {
                    listItemListener.onExpand();
                    isExpanded = true;
                }
            }
        });

    }

    // Static inner class to initialize the views of rows
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView item;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            item = (TextView) itemView.findViewById(R.id.row_item);
        }
        @Override
        public void onClick(View view) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " " + item.getText());
        }
    }
}
