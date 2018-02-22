package com.example.expandablerecyclerview.expandable_list;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ExpandableUtil {
    /**
     * Expand the view to its normal height
     * @param v The view to be expanded
     * @param from The initial height before it is expanded
     * @param to The final height after it is expanded
     */
    public static void expand(final View v, Integer from, Integer to) {
        if (v == null) return;

        final int initialHeight = from == null ? 0 : from.intValue();
        final int finalHeight = to;
        final int expandAmount = finalHeight - initialHeight;


        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.getLayoutParams().height = initialHeight;


        if (v.getVisibility() != View.VISIBLE) v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                } else {
                    v.getLayoutParams().height = initialHeight + (int)(expandAmount * interpolatedTime);
                }
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int)(finalHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    /**
     * Collapse the view by the collapseAmount value
     * @param v The view to be collapsed
     * @param baseHeight The height to be maintained
     */
    public static void collapse(final View v, final Integer collapseAmount) {
        if (v == null) return;

        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                Log.d("collapse", "collapse collapseAmount: " + collapseAmount + " initialHeight: " + initialHeight);

                if (interpolatedTime == 1) {
                    if (collapseAmount == initialHeight) {
                        v.setVisibility(View.INVISIBLE);
                    }
                }
                v.getLayoutParams().height = initialHeight - (int)(collapseAmount * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    /**
     * Collapse the view by the collapseAmount value with no animation
     * @param v The view to be collapsed
     * @param collapseAmount The height amount to be collapsed
     */
    public static void collapseWithNoAnimation(final View v, Integer collapseAmount) {
        if (v == null) return;
        final int initialHeight = v.getMeasuredHeight();
        v.getLayoutParams().height = initialHeight - collapseAmount;
        v.requestLayout();
    }
}