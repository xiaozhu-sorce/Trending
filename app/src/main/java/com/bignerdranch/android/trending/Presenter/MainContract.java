package com.bignerdranch.android.trending.Presenter;

import android.content.Context;

import com.bignerdranch.android.trending.View.FliterType;
import com.bignerdranch.android.trending.Model.User;

import java.util.List;

public interface MainContract {

    interface View{
        void setPresenter(MainContract.Presenter presenter);

        void showUserList(List<User> userList);

        void showError();
    }

    interface Presenter{
        void loadList(Context context,boolean isupdate);

        void setFliterType(FliterType fliterType);
    }
}
