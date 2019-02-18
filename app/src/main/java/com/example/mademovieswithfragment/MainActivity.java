package com.example.mademovieswithfragment;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mademovieswithfragment.MoviesFragment.NowFragment;
import com.example.mademovieswithfragment.MoviesFragment.UpComingFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomView = findViewById(R.id.BottomNavView);
        bottomView.setOnNavigationItemSelectedListener(navListener);

        if(getSupportActionBar() != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NowFragment()).commit();
            getSupportActionBar().setTitle(R.string.now_playing);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    int title = 0;
                    Fragment selectedFragment = null;
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    switch (menuItem.getItemId()){
                        case R.id.bottom_now:
                            selectedFragment = new NowFragment();
                            title = R.string.now_playing;
                            break;
                        case R.id.bottom_up:
                            selectedFragment = new UpComingFragment();
                            title = R.string.up_coming;
                            break;
                    }

                    if((selectedFragment != null) && (getSupportActionBar() != null)){
                        getSupportActionBar().setTitle(title);
                        fragmentTransaction.replace(R.id.fragment_container,selectedFragment);
                        fragmentTransaction.commit();
                    }

                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_translate,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.translate_settings){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
