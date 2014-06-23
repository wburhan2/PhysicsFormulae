package com.physicsformulae.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilson on 6/22/14.
 */
public class MyAdapter extends BaseAdapter {

    TextView mTopicItem;

    // Create a list to contain the list of strings.
    final List<String> mTopicList;

    // Constructor
    public MyAdapter(String[] itemList) {
        mTopicList = new ArrayList<String>();
        for(String item : itemList)
            mTopicList.add(item);
    }

    // To clear the list
    public void clear(){
        mTopicList.clear();
    }

    @Override
    public int getCount() {
        return mTopicList.size();
    }

    @Override
    public Object getItem(int i) {
        if (i >= mTopicList.size() || i < 0)
            return null;
        return mTopicList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View contextView, ViewGroup viewGroup) {
        View view = contextView;

        //In case the view is null, inflate the layout.
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, viewGroup, false);
        }

        mTopicItem = (TextView)view.findViewById(R.id.topic_item);
        mTopicItem.setText(mTopicList.get(i));
        return view;
    }
}