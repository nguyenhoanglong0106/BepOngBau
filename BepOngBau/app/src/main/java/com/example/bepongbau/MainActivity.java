package com.example.bepongbau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.example.bepongbau.Adapter.MenuApdater;
import com.example.bepongbau.Model.ItemMenu;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listView;
    //khai boa thu viên
    ArrayList<ItemMenu> itemMenus;
    MenuApdater menuApdater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        actionToolBar();
        actionMenu();
    }

    private void actionMenu() {
        itemMenus = new ArrayList<>();
        itemMenus.add(new ItemMenu("Về Chúng Tôi",R.drawable.ic_baseline_menu_24));
        menuApdater = new MenuApdater(this,R.layout.dong_item_menu,itemMenus);
        listView.setAdapter(menuApdater);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void setControl() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.navigationView);
        listView = findViewById(R.id.list_item_navigation);
    }
}