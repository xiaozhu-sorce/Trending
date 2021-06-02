package com.bignerdranch.android.trending.Presenter;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bignerdranch.android.trending.Model.List.ListDataSource;
import com.bignerdranch.android.trending.Model.List.ListRemoteDataSource;
import com.bignerdranch.android.trending.Model.List.ListRepository;
import com.bignerdranch.android.trending.Model.User;
import com.bignerdranch.android.trending.View.FliterType;

import java.util.ArrayList;
import java.util.List;

public class ListPresenter implements MainContract.Presenter{
    private final MainContract.View mView;

    private ListRepository mListRepository;

    private FliterType mFliterType = FliterType.JAVA_PROGRESS;

    private List<User> mUserList1 = new ArrayList<>();
    private List<User> mUserList2 = new ArrayList<>();
    private List<User> mUserList3 = new ArrayList<>();

    public ListPresenter(@NonNull ListRepository listRepository, @NonNull MainContract.View mview){
        this.mView = mview;
        this.mListRepository = listRepository;
        mView.setPresenter(this);
    }

    public void GetInstance(){

    }

    @Override
    public void start() {

    }

    @Override
    public void loadList(Context context, boolean isupdate) {

        switch (mFliterType){
            case JAVA_PROGRESS:

//                mView.showUserList(mUserList1);
                break;
            case C_PROGRESS:
                for (int i = 0 ;i < 20;i++){
                    User zhu = new User("deng","Trending");
                    mUserList2.add(zhu);
                }
                mView.showUserList(mUserList2);
                Toast.makeText(context, "CCC", Toast.LENGTH_LONG).show();
                break;
            case PYTHON_PROGRESS:
                for (int i = 0 ;i < 20;i++){
                    User zhu = new User("yuan","Trending");
                    mUserList3.add(zhu);
                }
                mView.showUserList(mUserList3);
                Toast.makeText(context, "python", Toast.LENGTH_LONG).show();
                break;
        }

        mListRepository.getUserList(new ListDataSource.LoadUserListCallback() {
            @Override
            public void onUserListLoaded(List<User> userList) {
                mView.showUserList(userList);
            }

            @Override
            public void onDataNotAvaliable() {

            }
        });

    }

    @Override
    public void setFliterType(FliterType fliterType) {
        mFliterType = fliterType;
    }

    @Override
    public void refreshUser() {

    }


}
