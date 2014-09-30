package com.physicsformulae.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilson on 9/19/14.
 */
public class SettingActivity extends FragmentActivity {

    private final int ABOUT = 0;
    private final int FEEDBACK = 1;

    private ListView mSettingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        final MyCustomAdapter myCustomAdapter = new MyCustomAdapter();
        mSettingView = (ListView)findViewById(R.id.setting_list);
        mSettingView.setAdapter(myCustomAdapter);
        MyClickListener myClickListener = new MyClickListener();
        mSettingView.setOnItemClickListener(myClickListener);
    }

    public class MyClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i){
                case FEEDBACK:
                    Intent email = new Intent(Intent.ACTION_SENDTO);
                    email.setType(HTTP.PLAIN_TEXT_TYPE);
                    email.setData(Uri.parse("mailto:wilson.burhan@gmail.com"));
                    email.putExtra(Intent.EXTRA_SUBJECT, "Physics Formulae Feedback");
                    try {
                        startActivity(Intent.createChooser(email, "Send Feedback:"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(view.getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case ABOUT:
                    FragmentManager fm = getSupportFragmentManager();
                    InfoDialogFragment fragment = new InfoDialogFragment();
                    fragment.show(fm, "InfoFragment");
                    break;
            }
        }
    }

    class MyCustomAdapter extends BaseAdapter {

        List<String> mStrings;

        public MyCustomAdapter() {
            mStrings = new ArrayList<String>();
            mStrings.add("About");
            mStrings.add("Feedback");
        }

        @Override
        public int getCount() {
            return mStrings.size();
        }

        public void clear(){
            mStrings.clear();
        }

        @Override
        public Object getItem(int i) {
            if (i >= mStrings.size() || i < 0)
                return null;
            return mStrings.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View contextView, ViewGroup viewGroup) {
            View view = contextView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater)viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.setting_list_item, viewGroup, false);
            }

            TextView text = (TextView)view.findViewById(R.id.title);
            text.setText(mStrings.get(i));
            return view;
        }
    }
}
