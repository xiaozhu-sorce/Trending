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
    public void getJ_UserList(@NonNull LoadUserListCallback callback) {
        mListDataSource.getJ_UserList(callback);
    }

    @Override
    public void getC_UserList(String lang,String since,@NonNull LoadUserListCallback callback) {
        mListDataSource.getC_UserList(lang,since,callback);
    }

//    @Override
//    public void getP_UserList(@NonNull LoadUserListCallback callback) {
//        mListDataSource.getP_UserList(callback);
//    }

}
