package com.bignerdranch.android.trending.Model.List;

import androidx.annotation.NonNull;

import com.bignerdranch.android.trending.Model.GetListResponse;
import com.bignerdranch.android.trending.Model.User;
import com.bignerdranch.android.trending.Util.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListRemoteDataSource implements ListDataSource{

    private static ListRemoteDataSource INSTANCE;

    private List<GetListResponse.ItemsBean> userList = new ArrayList<>();

    public static ListRemoteDataSource getINSTANCE(){
        if (INSTANCE == null){
            INSTANCE = new ListRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getUserList(@NonNull LoadUserListCallback callback) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://trendings.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        RetrofitApi api = retrofit.create(RetrofitApi.class);

        Call<GetListResponse> call = api.getList("java","weekly");

        call.enqueue(new Callback<GetListResponse>() {
            @Override
            public void onResponse(Call<GetListResponse> call, Response<GetListResponse> response) {
                userList = response.body().getItems();
            }

            @Override
            public void onFailure(Call<GetListResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getUser(String username, String Reponame, LoadUserCallback callback) {

    }
}
