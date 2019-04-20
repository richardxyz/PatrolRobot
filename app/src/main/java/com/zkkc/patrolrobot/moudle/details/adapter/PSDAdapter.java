package com.zkkc.patrolrobot.moudle.details.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.android.library.YLCircleImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.entity.ShootAngleDao;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/3/19
 */
public class PSDAdapter extends BaseQuickAdapter<ShootAngleDao, BaseViewHolder> {
    public PSDAdapter(int layoutResId, @Nullable List<ShootAngleDao> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShootAngleDao item) {
        helper.setText(R.id.tvLeft, item.getCameraX() + "");
        helper.setText(R.id.tvRight, item.getCameraY() + "");
        if (item.getCameraZ() == -1) {
            helper.setText(R.id.tvBs, "--");
        } else {
            helper.setText(R.id.tvBs, item.getCameraZ() + "");
        }

        YLCircleImageView ivShowPic = helper.getView(R.id.ivShowPic);
        Uri uri = Uri.parse(item.getPictureUri());
        Glide.with(mContext).load(uri).into(ivShowPic);

        if (item.getCameraType() == 1) {
            helper.setText(R.id.tvNum, "角度" + (helper.getAdapterPosition() + 1) + "(可见光)");
        } else {
            helper.setText(R.id.tvNum, "角度" + (helper.getAdapterPosition() + 1) + "(红外)");
        }
    }
}
