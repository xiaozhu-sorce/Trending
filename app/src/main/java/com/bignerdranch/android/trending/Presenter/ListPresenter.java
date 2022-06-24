package com.bignerdranch.android.trending.Presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bignerdranch.android.trending.Model.List.ListDataSource;
import com.bignerdranch.android.trending.Model.List.ListRepository;
import com.bignerdranch.android.trending.Model.User;
import com.bignerdranch.android.trending.View.FliterType;

import java.util.ArrayList;
import java.util.List;

public class ListPresenter implements MainContract.Presenter{

    private final MainContract.View mView;

    private final ListRepository mListRepository;

    private FliterType mFliterType;

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
        public void loadList(Context context) {

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
                        mView.hideKeletonScreen();
                        mView.showUserList(Java_ListToShow);
                    }

                    @Override
                    public void onDataNotAvaliable() {
                        mView.showError();
                    }
                });
                break;
            case C_PROGRESS:
                show(C_ListToShow,"c","weekly");
                break;
            case PYTHON_PROGRESS:
                show(P_ListToShow,"python","weekly");
                break;
        }
    }

    private void show(List<User> List,String lan,String since){
        mListRepository.getLanUserList(lan,since,new ListDataSource.LoadUserListCallback() {
            @Override
            public void onUserListLoaded(List<User> userList) {
                List.clear();
                List.addAll(userList);
                mView.hideKeletonScreen();
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
