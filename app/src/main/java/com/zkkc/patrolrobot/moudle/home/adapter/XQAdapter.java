package com.zkkc.patrolrobot.moudle.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.moudle.home.entity.XQBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/3/11
 */
public class XQAdapter extends BaseQuickAdapter<XQBean, BaseViewHolder> {
    public XQAdapter(int layoutResId, @Nullable List<XQBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XQBean item) {

        helper.setText(R.id.tvJdNum, "角度：" + item.getJdNum());
        helper.setText(R.id.tvTh, "塔号：" + item.getThNum());
        helper.setText(R.id.tvJjBs, "焦距倍数：" + item.getJj());
        helper.setText(R.id.tvLeftJd, "左旋角度：" + item.getLeftJd());
        helper.setText(R.id.tvRightJd, "右旋角度：" + item.getRightJd());
    }
}
