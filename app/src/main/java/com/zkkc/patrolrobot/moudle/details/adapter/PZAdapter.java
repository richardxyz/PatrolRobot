package com.zkkc.patrolrobot.moudle.details.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.entity.LocationDetailsDao;
import com.zkkc.patrolrobot.moudle.details.entity.PZBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/3/19
 */
public class PZAdapter extends BaseQuickAdapter<LocationDetailsDao, BaseViewHolder> {


    public PZAdapter(int layoutResId, @Nullable List<LocationDetailsDao> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocationDetailsDao item) {
        helper.setText(R.id.tvThNum, "塔号" + item.getTowerNo());
        helper.setText(R.id.tvPzName, item.getMCZR());
        helper.setText(R.id.tvPsdNum, "--");
        helper.setText(R.id.tvWcName, item.getMDate());
    }
}
