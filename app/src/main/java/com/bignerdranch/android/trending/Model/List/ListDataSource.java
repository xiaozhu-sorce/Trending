package com.bignerdranch.android.trending.Model.List;

import androidx.annotation.NonNull;

import com.bignerdranch.android.trending.Model.User;

import java.util.List;

public interface ListDataSource {

    interface LoadUserListCallback{

        void onUserListLoaded(List<User> userList);

        void onDataNotAvaliable();
    }

    void getJ_UserList( @NonNull LoadUserListCallback callback);

    void getLanUserList(String lang, String since, @NonNull LoadUserListCallback callback);
}
