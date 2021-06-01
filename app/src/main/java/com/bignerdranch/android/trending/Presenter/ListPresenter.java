package com.bignerdranch.android.trending.Presenter;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bignerdranch.android.trending.Model.User;
import com.bignerdranch.android.trending.R;
import com.bignerdranch.android.trending.View.FliterType;

import java.util.ArrayList;
import java.util.List;

public class ListPresenter implements MainContract.Presenter{
    private final MainContract.View mView;

    private FliterType mFliterType = FliterType.JAVA_PROGRESS;

    private List<User> mUserList1 = new ArrayList<>();
    private List<User> mUserList2 = new ArrayList<>();
    private List<User> mUserList3 = new ArrayList<>();

    public ListPresenter(@NonNull MainContract.View mview){
        this.mView = mview;
    }

    @Override
    public void loadList(Context context, boolean isupdate) {

        switch (mFliterType){
            case JAVA_PROGRESS:
                for (int i = 0 ;i < 20;i++){
                    User zhu = new User(R.mipmap.user_profile,"xiaozhu-sorce","Trending");
                    mUserList1.add(zhu);
                }
                mView.showUserList(mUserList1);
                break;
            case C_PROGRESS:
                for (int i = 0 ;i < 20;i++){
                    User zhu = new User(R.mipmap.user_profile,"deng","Trending");
                    mUserList2.add(zhu);
                }
                mView.showUserList(mUserList2);
                Toast.makeText(context, "CCC", Toast.LENGTH_LONG).show();
                break;
            case PYTHON_PROGRESS:
                for (int i = 0 ;i < 20;i++){
                    User zhu = new User(R.mipmap.user_profile,"yuan","Trending");
                    mUserList3.add(zhu);
                }
                mView.showUserList(mUserList3);
                Toast.makeText(context, "python", Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void setFliterType(FliterType fliterType) {
        mFliterType = fliterType;
    }


}
