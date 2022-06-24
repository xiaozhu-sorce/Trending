package com.bignerdranch.android.trending.View.List;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bignerdranch.android.trending.Model.List.ListRemoteDataSource;
import com.bignerdranch.android.trending.Model.List.ListRepository;
import com.bignerdranch.android.trending.Model.User;
import com.bignerdranch.android.trending.Presenter.ListPresenter;
import com.bignerdranch.android.trending.Presenter.MainContract;
import com.bignerdranch.android.trending.R;
import com.bignerdranch.android.trending.View.FliterType;
import com.bignerdranch.android.trending.View.MyRefreshLayout;

import com.ethanhua.skeleton.RecyclerViewSkeletonScreen;
import com.ethanhua.skeleton.Skeleton;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements MainContract.View {

    private RecyclerView recyclerView;

    private ListAdapter mAdapter;

    private List<User> userlist = new ArrayList<>();

    private MainContract.Presenter mPresenter;

    private MyRefreshLayout mRefreshLayout;

    private TitleBar mTitleBar;

    private ViewStub viewStub;

    private Button mRetry;

    private RecyclerViewSkeletonScreen skeletonScreen;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ListPresenter(ListRepository.getINSTANCE(ListRemoteDataSource.getINSTANCE()),this);
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.mainfragment, container, false);

        Fresco.initialize(getContext());

        viewStub = root.findViewById(R.id.view_stub);

        mRefreshLayout = root.findViewById(R.id.refresh);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadList(getContext());
            }
        });

        mRefreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRefreshLayout.isRefreshing()){
                    mRefreshLayout.setLoading(false);
                }
            }
        });

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
                        mPresenter.loadList(getContext());
                        break;
                    case 1:
                        mPresenter.setFliterType(FliterType.C_PROGRESS);
                        mPresenter.loadList(getContext());
                        break;
                    case 2:
                        mPresenter.setFliterType(FliterType.PYTHON_PROGRESS);
                        mPresenter.loadList(getContext());
                        break;
                }
                skeletonScreen = Skeleton.bind(recyclerView)
                        .adapter(mAdapter)
                        .load(R.layout.item_skeleton)
                        .show();
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
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        View view;
        try {
            view = viewStub.inflate();
            mRetry = view.findViewById(R.id.retry);
        } catch (Exception e) {
            viewStub.setVisibility(View.VISIBLE);
        } finally {
            if (mRetry != null) {
                mRetry.setOnClickListener(view1 -> {
                    mPresenter.loadList(getContext());
                    viewStub.setVisibility(View.GONE);
                });
            }
        }
    }

    @Override
    public void hideKeletonScreen() {
        skeletonScreen.hide();
    }
}
