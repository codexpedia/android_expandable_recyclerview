package com.example.expandablerecyclerview.nested_recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.expandablerecyclerview.R;

import java.util.ArrayList;
import java.util.List;


public class ChildGroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<ChildGroup> chilrenGroups;
    public static final int    BIGGEST_PATTERN_SIZE     = 4;
    static Context ctx;

    public ChildGroupAdapter(Context ctx, ArrayList<Child> children) {
        this.ctx = ctx;
        this.chilrenGroups = (ArrayList<ChildGroup>) splitChildrenIntoGroups(children);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ArrayList<FrameLayout> cardHolders;
        private final int                                       patternSize;

        public ViewHolder (View itemView, int patternSize) {
            super(itemView);
            this.patternSize = patternSize;

            cardHolders = new ArrayList<>(BIGGEST_PATTERN_SIZE);
            cardHolders.add((FrameLayout) itemView.findViewById(R.id.card_1));
            cardHolders.add((FrameLayout) itemView.findViewById(R.id.card_2));
            cardHolders.add((FrameLayout) itemView.findViewById(R.id.card_3));
            cardHolders.add((FrameLayout) itemView.findViewById(R.id.card_4));

        }


        private void setChildGroup(ChildGroup childGroup) {
            for (FrameLayout cardHolder : cardHolders) { if (cardHolder != null) { cardHolder.removeAllViews(); } }
            for (int i = 0; i < patternSize; i++) {
                FrameLayout   cardHolder = cardHolders.get(i);
                Child child       = childGroup.getChild(i);

                if (patternSize == 1) {
                    initChildListView(cardHolder);
                } else {
                    RelativeLayout childItemView = (RelativeLayout) View.inflate(ctx, R.layout.nested_recycler_item_child, null);
                    TextView tvChild = (TextView) childItemView.findViewById(R.id.tv_child);
                    tvChild.setText(child.getChildName());
                    cardHolder.addView(childItemView);
                }
            }
        }

        private void initChildListView(FrameLayout cardHolder) {
            //inflate view
            InnerRecyclerView childItemView = (InnerRecyclerView) View.inflate(ctx, R.layout.nested_recycler_item_child_parent, null);
            childItemView.initViews();

            cardHolder.addView(childItemView);

            //adjust layoutparams
            ViewGroup.LayoutParams layoutParams = cardHolder.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else { layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT; }
            cardHolder.setLayoutParams(layoutParams);
        }

    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int patternSize) {
        int  layout = ctx.getResources().getIdentifier("dashboard_group_" + patternSize, "layout", ctx.getPackageName());
        View view   = View.inflate(ctx, layout, null);
        return new ChildGroupAdapter.ViewHolder(view, patternSize);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        vh.setChildGroup(chilrenGroups.get(position));
    }

    @Override
    public int getItemViewType (int position) {
        return chilrenGroups.get(position).getChildrenCount();
    }

    @Override
    public int getItemCount() {
        return chilrenGroups == null ? 0 : chilrenGroups.size();
    }

    private List<ChildGroup> splitChildrenIntoGroups(List<Child> children) {
        ArrayList<ChildGroup> groups = new ArrayList<>();

        for (int i = 0; i < children.size(); i += 0) {
            Child  child  = children.get(i);
            ChildGroup group = new ChildGroup();

            for (int j = i; j < children.size() && j < i + BIGGEST_PATTERN_SIZE && !(child = children.get(j)).isFullWidth();
                 j++) { group.addChild(child); }

            if (group.getChildrenCount() == 0 && child.isFullWidth()) { group.addChild(child); }

            groups.add(group);
            i += group.getChildrenCount();
        }

        for(int i=0; i<groups.size(); i++) {
            ChildGroup cg = groups.get(i);
            Log.d("pye", "=====================================");
            for (int j=0; j< cg.getChildrenCount(); j++) {
                Log.d("pye", "NAME: " + cg.getChild(j).getChildName());
            }
        }

        return groups;
    }


}
