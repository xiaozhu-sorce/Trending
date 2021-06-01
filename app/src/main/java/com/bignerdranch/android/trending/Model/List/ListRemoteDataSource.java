package com.bignerdranch.android.trending.Model.List;

import androidx.annotation.NonNull;

import com.bignerdranch.android.trending.Model.User;

import java.util.ArrayList;
import java.util.List;

public class ListRemoteDataSource implements ListDataSource{
    @Override
    public void getUserList(int Page, @NonNull LoadUserListCallback callback) {

        List<User> userList = new ArrayList<>();


    }

    @Override
    public void getUser(int sid, String username, String Reponame, LoadUserCallback callback) {

    }
}
