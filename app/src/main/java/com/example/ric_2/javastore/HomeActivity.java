package com.example.ric_2.javastore;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.ric_2.javastore.Adapter.CategoryAdapter;
import com.example.ric_2.javastore.Database.DataSource.CartRepository;
import com.example.ric_2.javastore.Database.Local.CartDataSource;
import com.example.ric_2.javastore.Database.Local.CartDatabase;
import com.example.ric_2.javastore.Model.Banner;
import com.example.ric_2.javastore.Model.Category;
import com.example.ric_2.javastore.Model.Sensor;
import com.example.ric_2.javastore.Retrofit.IJavaStoreAPI;
import com.example.ric_2.javastore.Utils.Common;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView txt_name,txt_phone;
    SliderLayout sliderLayout;

    IJavaStoreAPI mService;

    //RxJava
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    RecyclerView lst_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        sliderLayout=(SliderLayout)findViewById(R.id.slider);
        mService=Common.getAPI();
        lst_menu=(RecyclerView)findViewById(R.id.lst_menu);
        lst_menu.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        lst_menu.setHasFixedSize(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView=navigationView.getHeaderView(0);
        txt_name=(TextView)headerView.findViewById(R.id.txt_name);
        txt_phone=(TextView)headerView.findViewById(R.id.txt_phone);

        txt_name.setText(Common.currentUser.getName());
        txt_phone.setText(Common.currentUser.getPhone());

        getBannerImage();
        
        getMenu();

        getToppingList();

        //Inicia database
        initDB();
    }

    private void initDB() {
        Common.cartDatabase=CartDatabase.getInstance(this);
        Common.cartRepository=CartRepository.getInstance(CartDataSource.getInstance(Common.cartDatabase.cartDAO()));

    }

    private void getToppingList() {
        compositeDisposable.add(mService.getSensor(Common.TOPPING_MENU_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Sensor>>() {
                    @Override
                    public void accept(List<Sensor> sensors) throws Exception {
                        Common.toppingList=sensors;
                    }
                }));

    }

    private void getMenu() {
        compositeDisposable.add(mService.getMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                       displayMenu(categories);
                    }
                }));

    }

    private void displayMenu(List<Category> categories) {
        CategoryAdapter adapter=new CategoryAdapter(this,categories);
        lst_menu.setAdapter(adapter);
    }

    private void getBannerImage() {
        compositeDisposable.add(mService.getBanners()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<Banner>>() {
                                @Override
                                public void accept(List<Banner> banners) throws Exception {
                                    displayImage(banners);
                                }
                            }));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    private void displayImage(List<Banner> banners) {
        HashMap<String,String>bannerMap=new HashMap<>();
        for (Banner item:banners)
            bannerMap.put(item.getName(),item.getLink());
        for (String name:bannerMap.keySet()){
            TextSliderView textSliderView=new TextSliderView(this);
            textSliderView.description(name)
                    .image(bannerMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            sliderLayout.addSlider(textSliderView);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
