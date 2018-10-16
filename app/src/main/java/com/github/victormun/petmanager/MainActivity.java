package com.github.victormun.petmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;

//import static com.github.victormun.petmanager.RegistrationActivity.BUNDLE_KEY;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
//        Bundle intentBundle = new Bundle();
//        if (intent != null) {
//            intentBundle = intent.getBundleExtra(BUNDLE_KEY);
//        }
//        homeFragment.setArguments(intentBundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment homeFragment = new HomeFragment();
        ft.replace(R.id.container, homeFragment, "HomeFragment");
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}
