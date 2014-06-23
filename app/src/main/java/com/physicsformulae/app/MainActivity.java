package com.physicsformulae.app;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.inject.Inject;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

public class MainActivity extends RoboFragmentActivity {

    private final int KINEMATICS = 0;
    private final int FORCES = 1;
    private final int DYNAMICS = 2;
    private final int WORK = 3;


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

        final MyAdapter adapter = new MyAdapter(mListOfTopics);
        mListView.setAdapter(adapter);
        MyClickListener myClickListener = new MyClickListener();
        mListView.setOnItemClickListener(myClickListener);
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

    public class MyClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case KINEMATICS:
                case FORCES:
                case DYNAMICS:
                case WORK:
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    SubMenuFragment fragment = SubMenuFragment.newInstance("Forces");
                    ft.replace(R.id.main_menu, fragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
                    //fragment.show(fm, "SubMenuFragment");
                    break;
            }
        }
    }
}
