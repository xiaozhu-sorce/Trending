package com.bignerdranch.android.trending.Model.List;

import androidx.annotation.NonNull;

public class ListRepository implements ListDataSource{

    private static ListRepository INSTANCE = null;

    private final ListDataSource mListDataSource;

    private ListRepository(@NonNull ListDataSource listDataSource){
        mListDataSource = listDataSource;
    }

    public static ListRepository getINSTANCE(ListDataSource userlistDataSource){
        if (INSTANCE == null){
            INSTANCE = new ListRepository(userlistDataSource);
        }
        return INSTANCE;
    }


    @Override
    public void getUserList(@NonNull LoadUserListCallback callback) {
        mListDataSource.getUserList(callback);
    }

    @Override
    public void getUser( String username, String Reponame, LoadUserCallback callback) {
        mListDataSource.getUser(username,Reponame,callback);
    }
}
