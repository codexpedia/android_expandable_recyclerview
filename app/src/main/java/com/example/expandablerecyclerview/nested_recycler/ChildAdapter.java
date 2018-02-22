package com.example.expandablerecyclerview.nested_recycler;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.expandablerecyclerview.R;

import java.util.ArrayList;


public class ChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Child> visibleChildren;
    private ArrayList<Child> allChildren;
    private ChildItemListener childItemListener;

    public ChildAdapter(ArrayList<Child> childData) {
        this.visibleChildren = childData;
        allChildren = new ArrayList<>(childData);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvChild;
        ImageView tvExpandCollapseToggle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvChild = (TextView) itemView.findViewById(R.id.tv_child);
            tvExpandCollapseToggle = (ImageView) itemView.findViewById(R.id.iv_expand_collapse_toggle);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nested_recycler_item_child_child, parent, false);

        ChildAdapter.ViewHolder cavh = new ChildAdapter.ViewHolder(itemLayoutView);
        return cavh;
    }


    final Handler handler = new Handler();
    Runnable collapseList = new Runnable() {
        @Override
        public void run() {
            if (getItemCount() > 1) {
                visibleChildren.remove(visibleChildren.size() - 1);
                notifyDataSetChanged();
                handler.postDelayed(collapseList, 50);
            }
        }
    };

    Runnable expandList = new Runnable() {
        @Override
        public void run() {
            int currSize = visibleChildren.size();
            if (currSize == allChildren.size()) return;

            visibleChildren.add(allChildren.get(currSize));

            notifyDataSetChanged();

            handler.postDelayed(expandList, 50);
        }
    };


    public void expandItems() {
        for (int i=1; i<allChildren.size(); i++) {
            visibleChildren.add(allChildren.get(i));
        }
        notifyItemRangeInserted(1, allChildren.size() - 1);
    }

    public void collapseItems() {
        for (int i=visibleChildren.size() - 1; i > 0;  i--) {
            visibleChildren.remove(i);
        }
        notifyItemRangeRemoved(1, allChildren.size() - 1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder vh = (ViewHolder) holder;

        if (position == 0 && getItemCount() == 1) {
            vh.tvExpandCollapseToggle.setImageResource(R.drawable.ic_expand_more_white_24dp);
            vh.tvExpandCollapseToggle.setVisibility(View.VISIBLE);
        } else if (position == allChildren.size() - 1) {
            vh.tvExpandCollapseToggle.setImageResource(R.drawable.ic_expand_less_white_24dp);
            vh.tvExpandCollapseToggle.setVisibility(View.VISIBLE);
        } else {
            vh.tvExpandCollapseToggle.setVisibility(View.GONE);
        }

        Child c = visibleChildren.get(position);
        vh.tvChild.setText(c.getChildName());

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacksAndMessages(null);
                if (getItemCount() > 1) {
                    childItemListener.onItemClick(InnerRecyclerView.Expandable.COLLPASE);
                    collapseItems();
//                    handler.post(collapseList);
                } else {
                    childItemListener.onItemClick(InnerRecyclerView.Expandable.EXPAND);
                    expandItems();
//                    handler.post(expandList);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return visibleChildren.size();
    }


    public void setChildItemListener(ChildItemListener childItemListener) {
        this.childItemListener = childItemListener;
    }

    public interface ChildItemListener {
        void onItemClick(InnerRecyclerView.Expandable expandable);
    }
}
