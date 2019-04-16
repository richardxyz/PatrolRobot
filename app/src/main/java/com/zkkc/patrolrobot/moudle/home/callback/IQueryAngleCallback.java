package com.zkkc.patrolrobot.moudle.home.callback;

import com.zkkc.patrolrobot.entity.ShootAngleDao;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/4/16
 */
public interface IQueryAngleCallback {
    void onSuccess(List<ShootAngleDao> list);
    void onFailure(String strErr);
}
