package com.physicsformulae.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.ironsource.mobilcore.CallbackResponse;
import com.ironsource.mobilcore.MobileCore;

import roboguice.activity.RoboFragmentActivity;

public class MainActivity extends RoboFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.main_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            MobileCore.init(this,"2K3GZ5GPPKNGFNRAI4FA39AOKQSMU", MobileCore.LOG_TYPE.DEBUG, MobileCore.AD_UNITS.OFFERWALL );
            //MobileCore.showOfferWall(this, null);

            MainMenuFragment fragment = new MainMenuFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm= getSupportFragmentManager();
                if(fm.getBackStackEntryCount()>0){
                    fm.popBackStack();
                }
                return true;
            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0 && MobileCore.isOfferwallReady()) {
            MobileCore.showOfferWall(this, new CallbackResponse() {
                @Override
                public void onConfirmation(TYPE type) {
                    finish();
                }
            });
        }
        else {
            super.onBackPressed();
        }
    }
}
