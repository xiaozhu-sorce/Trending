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

    private List<User> Java_ListToShow = new ArrayList<>();
    private List<User> C_ListToShow = new ArrayList<>();
    private List<User> P_ListToShow = new ArrayList<>();

    public ListPresenter(@NonNull ListRepository listRepository, @NonNull MainContract.View mview){
        this.mView = mview;
        this.mListRepository = listRepository;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadList(Context context, boolean isupdate) {

        Java_ListToShow = new ArrayList<>();

        switch (mFliterType){
            case JAVA_PROGRESS:
                mView.showUserList(Java_ListToShow);
                showData();
                break;
            case C_PROGRESS:
                mView.showUserList(C_ListToShow);
                showData();
                break;
            case PYTHON_PROGRESS:
                mView.showUserList(P_ListToShow);
                showData();
                break;
        }

    }

    private void showData(){
        switch (mFliterType){
            case JAVA_PROGRESS:
                mListRepository.getJ_UserList(new ListDataSource.LoadUserListCallback() {

                    @Override
                    public void onUserListLoaded(List<User> userList) {
                        Java_ListToShow.clear();
                        Java_ListToShow.addAll(userList);
                        mView.showUserList(Java_ListToShow);
                    }

                    @Override
                    public void onDataNotAvaliable() {
                        mView.showError();
                    }
                });
                break;
            case C_PROGRESS:
                show(C_ListToShow,"C","weekly");
                break;
            case PYTHON_PROGRESS:
                show(P_ListToShow,"Python","weekly");
                break;
        }
    }

    private void show(List<User> userList,String lang,String since){
        mListRepository.getC_UserList(lang,since,new ListDataSource.LoadUserListCallback() {
            @Override
            public void onUserListLoaded(List<User> userList) {
                userList.clear();
                userList.addAll(userList);
                mView.showUserList(userList);
            }

            @Override
            public void onDataNotAvaliable() {
                mView.showError();
            }
        });
    }

    @Override
    public void setFliterType(FliterType fliterType) {
        mFliterType = fliterType;
    }

}
