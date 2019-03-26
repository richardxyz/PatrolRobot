package com.zkkc.patrolrobot.moudle.details.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.moudle.details.entity.PZBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/3/19
 */
public class PZAdapter extends BaseQuickAdapter<PZBean, BaseViewHolder> {


    public PZAdapter(int layoutResId, @Nullable List<PZBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PZBean item) {
        helper.setText(R.id.tvThNum,item.getThNum());
        helper.setText(R.id.tvPzName,item.getPzName());
        helper.setText(R.id.tvPsdNum,item.getPsdNum());
        helper.setText(R.id.tvWcName,item.getWcDate());
    }
}
