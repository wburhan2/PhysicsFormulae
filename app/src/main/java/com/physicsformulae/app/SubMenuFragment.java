package com.physicsformulae.app;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    final String _stateKey = "topic";

    private final int HOOKES_LAW = 0;
    private final int WEIGHT = 1;
    private final int EQUILIBRIUM = 2;
    private final int MOMENTUM = 3;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(_stateKey, "Forces");
    }

    public static SubMenuFragment newInstance(String topic){
        SubMenuFragment fragment = new SubMenuFragment();

        Bundle args = new Bundle();
        args.putString("topic", topic);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sub_menu_fragment, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String topic = savedInstanceState == null ? "Forces" : savedInstanceState.getString(_stateKey);

        if (getArguments().getString("topic").equals(topic)) {
            topics = getResources().getStringArray(R.array.Forces);
        }

        final MyAdapter adapter = new MyAdapter(topics);
        mListView.setAdapter(adapter);

        MyTopicClickListener myClickListener = new MyTopicClickListener();
        mListView.setOnItemClickListener(myClickListener);
    }

    public class MyTopicClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case HOOKES_LAW:
                case WEIGHT:
                case EQUILIBRIUM:
                case MOMENTUM:
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    TopicFragment fragment = TopicFragment.newInstance("Forces");
                    ft.replace(R.id.main_container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                    break;
            }
        }
    }
}
