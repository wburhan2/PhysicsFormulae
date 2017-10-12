package com.physicsformulae.app;

import android.os.Bundle;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v7.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Wilson on 6/22/14.
 */
public class MainMenuFragment extends Fragment {

    private final int KINEMATICS = 0;
    private final int MOMENTUM = 1;
    private final int NEWTONS_LAWS = 2;
    private final int ROTATIONAL_KINEMATICS = 3;
    private final int VECTORS = 4;
    private final int WORK_ENERGY = 5;

    int mSelectedTopic = -1;

    ListView mListView;
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
            mSelectedTopic = savedInstanceState.getInt("selected");
        }
        mListView = (ListView) view.findViewById(R.id.main_list);
        mListOfTopics = getResources().getStringArray(R.array.topic_list);
        final MyAdapter adapter = new MyAdapter(mListOfTopics);
        mListView.setAdapter(adapter);
        MyClickListener myClickListener = new MyClickListener();
        mListView.setOnItemClickListener(myClickListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_menu_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }

    public class MyClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            TopicFragment fragment;
            switch (i) {
                case KINEMATICS:
                    mSelectedTopic = KINEMATICS;
                    fragment = TopicFragment.newInstance(getResources().getString(R.string.kinematics));
                    ft.replace(R.id.main_container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                    break;

                case MOMENTUM:
                    mSelectedTopic = MOMENTUM;
                    fragment = TopicFragment.newInstance(getResources().getString(R.string.momentum));
                    ft.replace(R.id.main_container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                    break;

                case NEWTONS_LAWS:
                    mSelectedTopic = NEWTONS_LAWS;
                    fragment = TopicFragment.newInstance(getResources().getString(R.string.newtons_laws));
                    ft.replace(R.id.main_container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                    break;

                case ROTATIONAL_KINEMATICS:
                    mSelectedTopic = ROTATIONAL_KINEMATICS;
                    fragment = TopicFragment.newInstance(getResources().getString(R.string.rotational_kinematics));
                    ft.replace(R.id.main_container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                    break;

                case VECTORS:
                    mSelectedTopic = VECTORS;
                    fragment = TopicFragment.newInstance(getResources().getString(R.string.vectors));
                    ft.replace(R.id.main_container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                    break;

                case WORK_ENERGY:
                    mSelectedTopic = WORK_ENERGY;
                    fragment = TopicFragment.newInstance(getResources().getString(R.string.work_energy));
                    ft.replace(R.id.main_container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                    break;
            }
        }
    }
}
