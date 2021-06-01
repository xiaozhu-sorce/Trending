package com.bignerdranch.android.trending.Util;

import com.bignerdranch.android.trending.Model.GetListResponse;

import java.util.Observable;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApi {

    @GET("https://api.github.com/search/repositories?q={key}&per_page=20")
    Observable<GetListResponse> getList(@Path("key") String key);
}
