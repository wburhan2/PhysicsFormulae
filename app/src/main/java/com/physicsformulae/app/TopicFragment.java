package com.physicsformulae.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * Created by Wilson on 8/14/14.
 */
public class TopicFragment extends RoboFragment {


    public static String _title;
    public final String KINEMATICS = "Kinematics";
    public final String MOMENTUM = "Momentum";
    public final String NEWTONS_LAWS = "Newton's Laws";
    public final String ROTATIONAL_KINEMATICS = "Rotational Kinematics";
    public final String VECTOR = "Vectors";
    public final String WORK_ENERGY = "Work and Energy";
    @InjectView(R.id.image_topic) TouchImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.topic_fragment, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("title", _title);
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
        String topic = getArguments().getString("chapter");

        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().getActionBar().setHomeButtonEnabled(true);

        Bitmap d;

        if (topic.equals(VECTOR)) {
            getActivity().setTitle(VECTOR);
            d = BitmapFactory.decodeResource(getResources(), R.drawable.vectors);
            int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
            Bitmap scaled = Bitmap.createScaledBitmap(d, 512, nh, true);
            imageView.setImageBitmap(scaled);
        } else if (topic.equals(KINEMATICS)) {
            getActivity().setTitle(KINEMATICS);
            d = BitmapFactory.decodeResource(getResources(), R.drawable.kinematics);
            int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
            Bitmap scaled = Bitmap.createScaledBitmap(d, 512, nh, true);
            imageView.setImageBitmap(scaled);
        } else if (topic.equals(MOMENTUM)) {
            getActivity().setTitle(MOMENTUM);
            d = BitmapFactory.decodeResource(getResources(), R.drawable.momentum);
            int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
            Bitmap scaled = Bitmap.createScaledBitmap(d, 512, nh, true);
            imageView.setImageBitmap(scaled);
        } else if (topic.equals(WORK_ENERGY)) {
            getActivity().setTitle(WORK_ENERGY);
            d = BitmapFactory.decodeResource(getResources(), R.drawable.work_energy);
            int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
            Bitmap scaled = Bitmap.createScaledBitmap(d, 512, nh, true);
            imageView.setImageBitmap(scaled);
        } else if (topic.equals(NEWTONS_LAWS)) {
            getActivity().setTitle(NEWTONS_LAWS);
            d = BitmapFactory.decodeResource(getResources(), R.drawable.newton_laws);
            int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
            Bitmap scaled = Bitmap.createScaledBitmap(d, 512, nh, true);
            imageView.setImageBitmap(scaled);
        } else if (topic.equals(ROTATIONAL_KINEMATICS)) {
            getActivity().setTitle(ROTATIONAL_KINEMATICS);
            d = BitmapFactory.decodeResource(getResources(), R.drawable.rotational_kinematics);
            int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
            Bitmap scaled = Bitmap.createScaledBitmap(d, 512, nh, true);
            imageView.setImageBitmap(scaled);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
