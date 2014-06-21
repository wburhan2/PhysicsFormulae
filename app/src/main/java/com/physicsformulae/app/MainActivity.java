package com.physicsformulae.app;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class MainActivity extends RoboActivity {

    @InjectView(R.id.list_view)
    ListView mListView;

    @InjectResource(R.array.topic_list)
    String[] mListOfTopics;

    // system service
    @Inject
    LocationManager loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyAdapter adapter = new MyAdapter();
        mListView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends BaseAdapter {

        TextView mTopicItem;

        // Create a list to contain the list of strings.
        final List<String> mTopicList;

        // Constructor
        public MyAdapter() {
            mTopicList = new ArrayList<String>();
            for(String item : mListOfTopics)
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

}
