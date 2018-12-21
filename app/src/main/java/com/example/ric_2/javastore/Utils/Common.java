package com.example.ric_2.javastore.Utils;

import com.example.ric_2.javastore.Model.User;
import com.example.ric_2.javastore.Retrofit.IJavaStoreAPI;
import com.example.ric_2.javastore.Retrofit.RetrofitClient;

public class Common {
    //En pruebas, localhost=10.0.2.2
    private static final String BASE_URL="http://192.168.137.1/javastore/";

    public static User currentUser=null;

    public static IJavaStoreAPI getAPI(){
        return RetrofitClient.getRetrofit(BASE_URL).create(IJavaStoreAPI.class);
    }
}
