package com.example.ric_2.javastore.Utils;

import com.example.ric_2.javastore.Model.Category;
import com.example.ric_2.javastore.Model.Sensor;
import com.example.ric_2.javastore.Model.User;
import com.example.ric_2.javastore.Retrofit.IJavaStoreAPI;
import com.example.ric_2.javastore.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

public class Common {
    //En pruebas, localhost=10.0.2.2
    private static final String BASE_URL="http://192.168.137.1/javastore/";

    public static User currentUser=null;
    public static Category currentCategory=null;
    public static final String TOPPING_MENU_ID="5";
    public static List<Sensor> toppingList=new ArrayList<>();
    public static double toppingPrice=0.0;
    public static List<String> toppingAdded=new ArrayList<>();

    //Hold field
    public static int nivel_1=-1;//-1: no choice(error), 0:M, 1:L
    public static int nivel_2=-1;//-1: no choice(error)
    public static int nivel_3=-1;//-1: no choice(error)


    public static IJavaStoreAPI getAPI(){
        return RetrofitClient.getRetrofit(BASE_URL).create(IJavaStoreAPI.class);
    }
}
