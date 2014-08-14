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
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rabbids));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
