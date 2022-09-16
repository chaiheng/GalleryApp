package com.example.galleryapp.ui;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.galleryapp.GalleryAdapter;
import com.example.galleryapp.R;
import com.example.galleryapp.databinding.ActivityHomeBinding;
import com.example.modulebase.bean.TestBean;
import com.example.modulebase.extend.BaseActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity<ActivityHomeBinding> {
    private GalleryAdapter adapter;
    private List<TestBean> mData;

    @Override
    protected void initView() {
        binding.ivBtnTome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mActivity, MineActivity.class));
            }
        });
        binding.homeSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }
        });
        adapter = new GalleryAdapter(this, null);
        binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity, RecyclerView.VERTICAL, false));
        binding.homeRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mActivity, DetailActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        mData.add(new TestBean("1", "风景画1", "阿莱索·巴尔多维内蒂 | 约1425-1499年，生于意大利", R.mipmap.ic_test1, "丘比特大理石雕塑位于左边，右面则是角斗士雕像的双脚。不论是否有潜台词，身为罗马皇室男宠，角斗士…", 1));
        mData.add(new TestBean("2", "风景画2", "阿莱索·巴尔多维内蒂 | 约1425-1499年，生于意大利", R.mipmap.ic_test2, "丘比特大理石雕塑位于左边，右面则是角斗士雕像的双脚。不论是否有潜台词，身为罗马皇室男宠，角斗士…", 2));
        mData.add(new TestBean("3", "风景画3", "阿莱索·巴尔多维内蒂 | 约1425-1499年，生于意大利", R.mipmap.ic_test3, "丘比特大理石雕塑位于左边，右面则是角斗士雕像的双脚。不论是否有潜台词，身为罗马皇室男宠，角斗士…", 2));
        mData.add(new TestBean("4", "风景画4", "阿莱索·巴尔多维内蒂 | 约1425-1499年，生于意大利", R.mipmap.ic_test4, "丘比特大理石雕塑位于左边，右面则是角斗士雕像的双脚。不论是否有潜台词，身为罗马皇室男宠，角斗士…", 1));
        mData.add(new TestBean("5", "风景画5", "阿莱索·巴尔多维内蒂 | 约1425-1499年，生于意大利", R.mipmap.ic_test5, "丘比特大理石雕塑位于左边，右面则是角斗士雕像的双脚。不论是否有潜台词，身为罗马皇室男宠，角斗士…", 1));
        adapter.setNewData(mData);
        //get

    }
}
