package com.physicsformulae.app;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * Created by Wilson on 8/14/14.
 */
public class TopicFragment extends RoboFragment {


    public static String _title;
    public final String VECTOR = "Vectors";
    public final String KINEMATIC = "Kinematics";
    @InjectView(R.id.image_topic)
    ZoomableImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.topic_fragment, container, false);
    }

    public static TopicFragment newInstance(String topic){
        TopicFragment fragment = new TopicFragment();

        Bundle args = new Bundle();
        args.putString("chapter", topic);
        _title = topic;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //String topic = savedInstanceState == null ? null : savedInstanceState.getString(_title);
        //if (topic == null)  {
        if (getArguments().getString("chapter").equals(VECTOR)) {
            getActivity().setTitle(VECTOR);
            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.vectors));
        }
        //}
        else if (getArguments().getString("chapter").equals(KINEMATIC)){
            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.kinematics));
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
