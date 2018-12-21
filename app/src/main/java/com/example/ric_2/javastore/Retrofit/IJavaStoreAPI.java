package com.example.ric_2.javastore.Retrofit;

import com.example.ric_2.javastore.Model.Banner;
import com.example.ric_2.javastore.Model.CheckUserResponse;
import com.example.ric_2.javastore.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IJavaStoreAPI {
    @FormUrlEncoded
    @POST("checkuser.php")
    Call<CheckUserResponse>checkUserExists(@Field("phone")String phone);

    @FormUrlEncoded
    @POST("register.php")
    Call<User>registerNewUser(@Field("phone")String phone,
                              @Field("name")String name,
                              @Field("address")String address,
                              @Field("birthdate")String birthdate);

    @FormUrlEncoded
    @POST("getuser.php")
    Call<User>getUserInformation(@Field("phone")String phone);

    @GET("getbanner.php")
    io.reactivex.Observable<List<Banner>> getBanners();

}
