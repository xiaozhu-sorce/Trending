package com.bignerdranch.android.trending.Util;

import com.bignerdranch.android.trending.Model.User;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitApi {

    @GET("java")
    Call<List<User>> getList(@Query("since") String since);

    @GET("")
    Observable<List<User>> getLanList(@Url String language, @Query("since") String since);

}
