package com.example.contacts;



import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.contacts.adapters.ViewPagerAdapter;
import com.example.contacts.fragments.CallsFragment;
import com.example.contacts.fragments.ContactsFragment;
import com.example.contacts.fragments.FavouriteFragment;

public class MainActivity extends AppCompatActivity {

    private static final int[] ICONS = {R.drawable.ic_calls, R.drawable.ic_action_name, R.drawable.ic_star};
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);

        }

        askPermission();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CallsFragment(), "Calls");
        adapter.addFragment(new ContactsFragment(), "Contacts");
        adapter.addFragment(new FavouriteFragment(), "Favourite");

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);


        for (int i=0; i < tabLayout.getTabCount(); i++){
            TabLayout.Tab tab  = tabLayout.getTabAt(i);
            tab.setIcon(ICONS[i]);
        }
    }

    private void askPermission(){


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED ) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, 1);

        }
    }
}
