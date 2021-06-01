package com.bignerdranch.android.trending.View.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.trending.Model.User;
import com.bignerdranch.android.trending.Presenter.ListPresenter;
import com.bignerdranch.android.trending.Presenter.MainContract;
import com.bignerdranch.android.trending.R;
import com.bignerdranch.android.trending.View.FliterType;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements MainContract.View {

    private RecyclerView recyclerView;

    private ListAdapter mAdapter;

    private List<User> userlist = new ArrayList<>();

    private MainContract.Presenter mPresenter;

    private TitleBar mTitleBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ListPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.mainfragment, container, false);

        mAdapter = new ListAdapter(userlist);
        recyclerView = root.findViewById(R.id.main_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        mTitleBar = root.findViewById(R.id.title_menu);
        mTitleBar.setMenu(getContext());
        mTitleBar.setBackgroundColor(Color.parseColor("#fafafa"));
        mTitleBar.setOptionSelectListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTitleBar.adapter.setSelectedPosition(position);
                switch (position) {
                    case 0:
                        mPresenter.setFliterType(FliterType.JAVA_PROGRESS);
                        mPresenter.loadList(getContext(),true);
                        break;
                    case 1:
                        mPresenter.setFliterType(FliterType.C_PROGRESS);
                        mPresenter.loadList(getContext(),true);
                        break;
                    case 2:
                        mPresenter.setFliterType(FliterType.PYTHON_PROGRESS);
                        mPresenter.loadList(getContext(),true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showUserList(List<User> userList) {
        mAdapter.replaceUser(userList);
        recyclerView.scrollToPosition(0);
        LinearLayoutManager mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        mLayoutManager.scrollToPositionWithOffset(0, 0);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "失败辽", Toast.LENGTH_LONG).show();
    }
}
