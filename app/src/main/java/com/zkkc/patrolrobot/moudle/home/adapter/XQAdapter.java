package com.zkkc.patrolrobot.moudle.home.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.entity.ShootAngleDao;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ShiJunRan on 2019/3/11
 */
public class XQAdapter extends BaseQuickAdapter<ShootAngleDao, BaseViewHolder> {
    public XQAdapter(int layoutResId, @Nullable List<ShootAngleDao> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShootAngleDao item) {
        helper.setText(R.id.tvJdNum, "角度：" + item.getId());
        helper.setText(R.id.tvTh, "塔号：" + item.getTowerNo());
        helper.setText(R.id.tvJjBs, "焦距倍数：" + item.getCameraZ());
        helper.setText(R.id.tvLeftJd, "左旋角度：" + item.getCameraX());
        helper.setText(R.id.tvRightJd, "右旋角度：" + item.getCameraY());
        CircleImageView imageView = helper.getView(R.id.ivTP);
        Uri uri = Uri.parse(item.getPictureUri());
        Glide.with(mContext).load(uri).into(imageView);
    }
}
