package com.bignerdranch.android.trending.Model.List;

import androidx.annotation.NonNull;

import com.bignerdranch.android.trending.Model.NetUtil;
import com.bignerdranch.android.trending.Model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRemoteDataSource implements ListDataSource {

    private static ListRemoteDataSource INSTANCE;

    public static ListRemoteDataSource getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ListRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getJ_UserList(@NonNull LoadUserListCallback callback) {

        List<User> muserList = new ArrayList<>();

        Call<List<User>> call = NetUtil.getInstance().getApi().getList("weekly");

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body()!=null){
                    for (User itemsBean : response.body()) {
                        muserList.add(new User(itemsBean.getUsername(),
                                itemsBean.getRepositoryName(),
                                itemsBean.getLanguage(),
                                itemsBean.getTotalStars(),
                                itemsBean.getForks(),
                                itemsBean.getDescription(),
                                itemsBean.getUrl(),
                                itemsBean.getBuiltBy()));
                    }
                }
                callback.onUserListLoaded(muserList);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onDataNotAvaliable();
            }
        });
    }

    @Override
    public void getLanUserList(String language,String since, @NonNull LoadUserListCallback callback) {
        List<User> cuserList = new ArrayList<>();

        NetUtil.getInstance().getApi().getLanList(language,since).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<User> users) {
                        for (User itemsBean : users) {
                            cuserList.add(new User(itemsBean.getUsername(),
                                    itemsBean.getRepositoryName(),
                                    itemsBean.getLanguage(),
                                    itemsBean.getTotalStars(),
                                    itemsBean.getForks(),
                                    itemsBean.getDescription(),
                                    itemsBean.getUrl(),
                                    itemsBean.getBuiltBy()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvaliable();
                    }

                    @Override
                    public void onComplete() {
                        callback.onUserListLoaded(cuserList);
                    }
                });

    }
}
