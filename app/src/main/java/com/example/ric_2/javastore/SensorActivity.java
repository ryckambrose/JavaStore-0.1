package com.example.ric_2.javastore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.ric_2.javastore.Adapter.SensorAdapter;
import com.example.ric_2.javastore.Model.Sensor;
import com.example.ric_2.javastore.Retrofit.IJavaStoreAPI;
import com.example.ric_2.javastore.Utils.Common;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SensorActivity extends AppCompatActivity {

    IJavaStoreAPI mService;

    //RxJava
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    RecyclerView lst_sensor;

    TextView txt_banner_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        mService=Common.getAPI();
        lst_sensor=(RecyclerView)findViewById(R.id.recycler_sensor);
        lst_sensor.setLayoutManager(new GridLayoutManager(this,2));
        lst_sensor.setHasFixedSize(true);

        txt_banner_name=(TextView)findViewById(R.id.txt_menu_name);
        txt_banner_name.setText(Common.currentCategory.Name);

        loadListSensor(Common.currentCategory.ID);

    }

    private void loadListSensor(String menuId) {
        compositeDisposable.add(mService.getSensor(menuId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<Sensor>>() {
                                @Override
                                public void accept(List<Sensor> sensors) throws Exception {
                                    displaySensorList(sensors);
                                }
                            }));

    }

    private void displaySensorList(List<Sensor> sensors) {
        SensorAdapter adapter=new SensorAdapter(this,sensors);
        lst_sensor.setAdapter(adapter);
    }
}
