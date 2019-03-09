package com.zkkc.patrolrobot.moudle.home.contract;


import com.zkkc.patrolrobot.base.BasePresenter;
import com.zkkc.patrolrobot.base.BaseView;


/**
 * Created by ShiJunRan on 2019/3/7
 */
public interface MainContract {
    interface View extends BaseView {
//        void photoGraphSucceed();
//        void photoGraphErr(String err);
    }

    abstract class Presenter extends BasePresenter<View> {
//        public abstract void connectionHost();


    }
}
