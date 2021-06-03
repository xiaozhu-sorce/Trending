package com.bignerdranch.android.trending.Util;

import com.bignerdranch.android.trending.Model.GetListResponse;

import io.reactivex.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("repo")
    Call<GetListResponse> getList(@Query("lang") String lang , @Query("since") String since);

    @GET("repo")
    Observable<GetListResponse> getLanList(@Query("lang") String lang , @Query("since") String since);

}
