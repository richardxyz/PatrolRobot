package com.zkkc.patrolrobot.moudle.home.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.base.BaseActivity;
import com.zkkc.patrolrobot.base.BasePresenter;
import com.zkkc.patrolrobot.base.BaseView;
import com.zkkc.patrolrobot.entity.ShootAngleDao;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ShiJunRan on 2019/3/12
 */
public class PictureShowAct extends BaseActivity {

    @BindView(R.id.photoView)
    PhotoView photoView;
    @BindView(R.id.ivClose)
    ImageView ivClose;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void mEvent(ShootAngleDao shootAngleDao) {
        Uri uri = Uri.parse(shootAngleDao.getPictureUri());
        photoView.setImageURI(uri);
    }

    @Override
    public int getLayoutId() {
        return R.layout.picture_show_act;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullscreen(true);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ivClose)
    public void onViewClicked() {
        finish();
    }
}
