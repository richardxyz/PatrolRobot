package com.zkkc.patrolrobot.moudle.home.presenter;

import android.content.Context;

import com.zkkc.patrolrobot.moudle.home.contract.MainContract;
import com.zkkc.patrolrobot.moudle.home.model.MainModel;

/**
 * Created by ShiJunRan on 2019/3/7
 */
public class MainPresenter extends MainContract.Presenter {
    private Context mContext;
    private MainModel model;

    public MainPresenter(Context mContext) {
        this.mContext = mContext;
        model = new MainModel();
    }
}
