package com.example.expandablerecyclerview.nested_recycler;

import java.util.ArrayList;
import java.util.Collection;

public class ChildGroup {
    ArrayList<Child> children;

    public ChildGroup() {
        children = new ArrayList<>();
    }

    public int getChildrenCount () {
        return children.size();
    }

    public boolean addChild(Child child) {
        return children.add(child);
    }

    public boolean addChildren(Collection<? extends Child> collection) {
        return children.addAll(collection);
    }

    public Child getChild(int i) {
        return children.get(i);
    }

    public ArrayList<Child> getChildren() {
        return children;
    }
}
