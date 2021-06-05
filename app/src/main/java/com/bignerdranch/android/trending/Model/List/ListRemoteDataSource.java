package com.bignerdranch.android.trending.Model.List;

import android.util.Log;

import androidx.annotation.NonNull;

import com.bignerdranch.android.trending.Model.GetListResponse;
import com.bignerdranch.android.trending.Model.User;
import com.bignerdranch.android.trending.Util.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListRemoteDataSource implements ListDataSource{

    private static ListRemoteDataSource INSTANCE;

    public static ListRemoteDataSource getINSTANCE(){
        if (INSTANCE == null){
            INSTANCE = new ListRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getJ_UserList(@NonNull LoadUserListCallback callback) {

        List<User> muserList = new ArrayList<>();

        RetrofitApi api = new Retrofit.Builder()
                .baseUrl("https://trendings.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitApi.class);

        Call<GetListResponse> call = api.getList("java","weekly");

        call.enqueue(new Callback<GetListResponse>() {
            @Override
            public void onResponse(Call<GetListResponse> call, Response<GetListResponse> response) {

                for (GetListResponse.ItemsBean itemsBean : response.body().getItems()){
                    muserList.add(new User(itemsBean.getRepo(),
                            itemsBean.getDesc(),
                            itemsBean.getLang(),
                            itemsBean.getStars(),
                            itemsBean.getForks(),
                            itemsBean.getAvatars()));
                }

                callback.onUserListLoaded(muserList);
            }

            @Override
            public void onFailure(Call<GetListResponse> call, Throwable t) {
                callback.onDataNotAvaliable();
            }
        });
    }

    @Override
    public void getLanUserList(String lang,String since,@NonNull LoadUserListCallback callback) {

        List<User> cuserList = new ArrayList<>();

        RetrofitApi api = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://trendings.herokuapp.com/")
                .build()
                .create(RetrofitApi.class);

        api.getLanList(lang,since).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetListResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull GetListResponse getListResponse) {
                        for (GetListResponse.ItemsBean itemsBean : getListResponse.getItems()){
                            cuserList.add(new User(itemsBean.getRepo(),
                                    itemsBean.getDesc(),
                                    itemsBean.getLang(),
                                    itemsBean.getStars(),
                                    itemsBean.getForks(),
                                    itemsBean.getAvatars()));
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        callback.onDataNotAvaliable();
                    }

                    @Override
                    public void onComplete() {
                        callback.onUserListLoaded(cuserList);
                    }
                });
    }
}
