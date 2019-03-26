package com.zkkc.patrolrobot.moudle.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.moudle.home.entity.HwBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/3/15
 */
public class HWAdapter extends BaseQuickAdapter<HwBean, BaseViewHolder> {
    public HWAdapter(int layoutResId, @Nullable List<HwBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HwBean item) {
        helper.setText(R.id.tvHwName, item.getHwName());
    }
}
