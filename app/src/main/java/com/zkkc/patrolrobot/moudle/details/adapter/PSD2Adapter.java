package com.zkkc.patrolrobot.moudle.details.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.patrolrobot.moudle.details.entity.PSDBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/3/19
 */
public class PSD2Adapter extends BaseQuickAdapter<PSDBean, BaseViewHolder> {
    public PSD2Adapter(int layoutResId, @Nullable List<PSDBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PSDBean item) {

    }
}
