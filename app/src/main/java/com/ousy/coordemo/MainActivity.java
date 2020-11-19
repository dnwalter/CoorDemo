package com.ousy.coordemo;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AppBarLayout mAppBarLayout;
    private ConstraintLayout mContentCl;
    private View mRootView;
    private TestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootView = findViewById(R.id.rootView);
        mRecyclerView = findViewById(R.id.recyclerView);
        mAppBarLayout = findViewById(R.id.appBarLayout);
        mContentCl = findViewById(R.id.cl_content);

        initView();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TestAdapter(this, getBigLists());
        mRecyclerView.setAdapter(mAdapter);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                setContentHeight();
            }
        });
    }

    private void setContentHeight() {
        int listHeight = mAdapter.getItemCount() * dip2px(this, 40);
        int range = mRecyclerView.computeVerticalScrollRange();
        int extent = mRecyclerView.computeVerticalScrollExtent();
        int offset = mRecyclerView.computeVerticalScrollOffset();
        Log.e("ousyxx", "listHeight:" + listHeight);
        Log.e("ousyxx", "range:" + range);
        Log.e("ousyxx", "extent:" + extent);
        Log.e("ousyxx", "offset:" + offset);
        int minHeight = mRootView.getHeight() - listHeight;

        if (minHeight < 0) {
            minHeight = 0;
        }

        if (minHeight != mContentCl.getMinimumHeight()) {
            mContentCl.setMinimumHeight(minHeight);
        }
    }

    private void resetContentHeight() {
        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                setContentHeight();
            }
        });
    }

    public void onNoneClick(View view) {
        mAdapter.refreshData(getNoneLists());
        resetContentHeight();
    }

    public void onSmallClick(View view) {
        mAdapter.refreshData(getSmallLists());
        resetContentHeight();
    }

    public void onBigClick(View view) {
        mAdapter.refreshData(getBigLists());
        resetContentHeight();
    }

    private List<String> getBigLists() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("text---" + i);
        }

        return list;
    }

    private List<String> getSmallLists() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("text---" + i);
        }

        return list;
    }

    private List<String> getNoneLists() {
        List<String> list = new ArrayList<>();

        return list;
    }

    private int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dipValue * scale + 0.5f);
    }
}