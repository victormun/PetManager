package com.github.victormun.petmanager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.app_bar)
    Toolbar toolbar;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    private Fragment homeFragment, healthFragment;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ft = getSupportFragmentManager().beginTransaction();
        homeFragment = new HomeFragment();
        healthFragment = new HealthFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.menu_home) {
                    openFragment(homeFragment);
                    return true;
                }
                if (id == R.id.menu_health) {
                    openFragment(healthFragment);
                    return true;
                }
                return true;
            }
        });

        ft.replace(R.id.container, homeFragment, "HomeFragment");
        ft.commit();
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.top_menu_change_pet) {
            Toast.makeText(MainActivity.this, "Changing pet: SOON!", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
