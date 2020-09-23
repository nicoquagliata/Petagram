package com.uy.cra.petagram;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.uy.cra.petagram.adapter.PageAdapter;
import com.uy.cra.petagram.fragment.PerfilFragment;
import com.uy.cra.petagram.fragment.RecyclerViewFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {


        case R.id.fav:
            Toast.makeText(getBaseContext(), "fav clicked", Toast.LENGTH_LONG).show();
            //Intent intent = new Intent(MainActivity.this, Favoritos.class);
            Intent intent1 = new Intent();
            intent1.setClass(MainActivity.this, Favoritos.class);
            startActivity(intent1);
            return(true);
        case R.id.mContacto:
            Toast.makeText(getBaseContext(), "Contacto clicked", Toast.LENGTH_LONG).show();
            // Intent intent = new Intent(MainActivity.this, Favoritos.class);
            Intent intent2 = new Intent();
            intent2.setClass(MainActivity.this, Contacto.class);
            startActivity(intent2);
            return(true);
        case R.id.mAcerca:
            Toast.makeText(getBaseContext(), "Acerca clicked", Toast.LENGTH_LONG).show();
            //Intent intent = new Intent(MainActivity.this, Favoritos.class);
            Intent intent3 = new Intent();
            intent3.setClass(MainActivity.this, Acerca.class);
            startActivity(intent3);
            return(true);

        case R.id.exit:
            finish();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }


    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_pet);
    }


}
