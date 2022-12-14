package com.example.galleryapp.ui;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.galleryapp.CollectionAdapter;
import com.example.galleryapp.R;
import com.example.galleryapp.databinding.ActivityCollectionListBinding;
import com.example.modulebase.bean.TestBean;
import com.example.modulebase.extend.BaseActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends BaseActivity<ActivityCollectionListBinding> {
    private CollectionAdapter adapter;
    private List<TestBean> mData;

    @Override
    protected void initView() {
        setTitle("ζηζΆθ");
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
        adapter = new CollectionAdapter(this, null);
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
        mData.add(new TestBean("1", "ι£ζ―η»1", "ιΏθ±η΄’Β·ε·΄ε°ε€η»΄εθ | ηΊ¦1425-1499εΉ΄οΌηδΊζε€§ε©", R.mipmap.ic_test1, "δΈζ―ηΉε€§ηη³ιε‘δ½δΊε·¦θΎΉοΌε³ι’εζ―θ§ζε£«ιεηεθγδΈθ?Ίζ―ε¦ζζ½ε°θ―οΌθΊ«δΈΊη½ι©¬ηε?€η·ε? οΌθ§ζε£«β¦", 1));
        mData.add(new TestBean("2", "ι£ζ―η»2", "ιΏθ±η΄’Β·ε·΄ε°ε€η»΄εθ | ηΊ¦1425-1499εΉ΄οΌηδΊζε€§ε©", R.mipmap.ic_test2, "δΈζ―ηΉε€§ηη³ιε‘δ½δΊε·¦θΎΉοΌε³ι’εζ―θ§ζε£«ιεηεθγδΈθ?Ίζ―ε¦ζζ½ε°θ―οΌθΊ«δΈΊη½ι©¬ηε?€η·ε? οΌθ§ζε£«β¦", 2));
        mData.add(new TestBean("3", "ι£ζ―η»3", "ιΏθ±η΄’Β·ε·΄ε°ε€η»΄εθ | ηΊ¦1425-1499εΉ΄οΌηδΊζε€§ε©", R.mipmap.ic_test3, "δΈζ―ηΉε€§ηη³ιε‘δ½δΊε·¦θΎΉοΌε³ι’εζ―θ§ζε£«ιεηεθγδΈθ?Ίζ―ε¦ζζ½ε°θ―οΌθΊ«δΈΊη½ι©¬ηε?€η·ε? οΌθ§ζε£«β¦", 2));
        mData.add(new TestBean("4", "ι£ζ―η»4", "ιΏθ±η΄’Β·ε·΄ε°ε€η»΄εθ | ηΊ¦1425-1499εΉ΄οΌηδΊζε€§ε©", R.mipmap.ic_test4, "δΈζ―ηΉε€§ηη³ιε‘δ½δΊε·¦θΎΉοΌε³ι’εζ―θ§ζε£«ιεηεθγδΈθ?Ίζ―ε¦ζζ½ε°θ―οΌθΊ«δΈΊη½ι©¬ηε?€η·ε? οΌθ§ζε£«β¦", 1));
        mData.add(new TestBean("5", "ι£ζ―η»5", "ιΏθ±η΄’Β·ε·΄ε°ε€η»΄εθ | ηΊ¦1425-1499εΉ΄οΌηδΊζε€§ε©", R.mipmap.ic_test5, "δΈζ―ηΉε€§ηη³ιε‘δ½δΊε·¦θΎΉοΌε³ι’εζ―θ§ζε£«ιεηεθγδΈθ?Ίζ―ε¦ζζ½ε°θ―οΌθΊ«δΈΊη½ι©¬ηε?€η·ε? οΌθ§ζε£«β¦", 1));
        adapter.setNewData(mData);
        //get

    }
}
