package com.physicsformulae.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * Created by Wilson on 6/22/14.
 */
public class SubMenuFragment extends RoboFragment {

    @InjectView(R.id.sub_topic_list)
    private ListView mListView;

    private String[] topics;

    public static SubMenuFragment newInstance(String topic){
        SubMenuFragment fragment = new SubMenuFragment();

        Bundle args = new Bundle();
        args.putString("topic", topic);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.topic_fragment, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments().getString("topic").equals("Forces")) {
            topics = getResources().getStringArray(R.array.Forces);
        }

        final MyAdapter adapter = new MyAdapter(topics);
        mListView.setAdapter(adapter);
    }
}
