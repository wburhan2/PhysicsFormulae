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
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

/**
 * Created by Wilson on 6/22/14.
 */
public class MainMenuFragment extends RoboFragment {

    private final int VECTORS = 0;
    private final int KINEMATICS = 1;

    int mSelectedTopic = 0;

    @InjectView(R.id.main_list)
    ListView mListView;

    @InjectResource(R.array.topic_list)
    String[] mListOfTopics;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selected", mSelectedTopic);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mSelectedTopic = savedInstanceState.getInt("selected", 0);
        }

        final MyAdapter adapter = new MyAdapter(mListOfTopics);
        mListView.setAdapter(adapter);
        MyClickListener myClickListener = new MyClickListener();
        mListView.setOnItemClickListener(myClickListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_menu_fragment, container, false);
    }

    public class MyClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            switch (i) {
                case VECTORS:
                    TopicFragment fragment = TopicFragment.newInstance(getResources().getString(R.string.vectors));
                    ft.replace(R.id.main_container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                    break;

                case KINEMATICS:
                    fragment = TopicFragment.newInstance(getResources().getString(R.string.kinematics));
                    ft.replace(R.id.main_container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                    break;
            }
        }
    }
}
