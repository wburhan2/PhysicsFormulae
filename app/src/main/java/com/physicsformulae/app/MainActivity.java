package com.physicsformulae.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import roboguice.activity.RoboFragmentActivity;

public class MainActivity extends RoboFragmentActivity {

    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.main_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            // Create the interstitial.
            interstitial = new InterstitialAd(this);
            interstitial.setAdUnitId("ca-app-pub-3671756782926123/2632835897");

            // Create ad request.
            AdRequest adRequest = new AdRequest.Builder().build();

            // Begin loading your interstitial.
            interstitial.loadAd(adRequest);

            interstitial.setAdListener(new AdListener(){
                public void onAdLoaded(){
                    displayInterstitial();
                }
            });

            MainMenuFragment fragment = new MainMenuFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_container, fragment)
                    .commit();
        }
    }

    // Invoke displayInterstitial() when you are ready to display an interstitial.
    public void displayInterstitial() {
        if (interstitial.isLoaded()) {
            interstitial.show();
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
}
