package com.bignerdranch.android.trending.Model.List;

import androidx.annotation.NonNull;

import com.bignerdranch.android.trending.Model.User;

import java.util.List;

public interface ListDataSource {

    interface LoadUserCallback{

        void onUserLoaded(User user);

        void onDataNotAvaliable();
    }

    interface LoadUserListCallback{

        void onUserListLoaded(List<User> userList);

        void onDataNotAvaliable();
    }

    void getUserList( @NonNull LoadUserListCallback callback);

    void getUser(String username,String Reponame,LoadUserCallback callback);
}
