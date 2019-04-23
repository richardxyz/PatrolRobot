package com.zkkc.patrolrobot.moudle.home.contract;


import android.view.View;

import com.zkkc.patrolrobot.base.BasePresenter;
import com.zkkc.patrolrobot.base.BaseView;
import com.zkkc.patrolrobot.entity.ShootAngleDao;
import com.zkkc.patrolrobot.moudle.home.callback.ISaveAngleCallback;
import com.zkkc.patrolrobot.widget.EmptyControlVideo;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.Topic;

import java.util.List;
import java.util.concurrent.ExecutorService;

import cn.com.magnity.sdk.MagDevice;


/**
 * Created by ShiJunRan on 2019/3/7
 */
public interface MainContract {
    interface View extends BaseView {
        void mFailure(String strErr);

        void mConnected(CallbackConnection connection, String str);

        void mDisconnected(String str);

        void mPublish(UTF8Buffer topic, Buffer body);

        void subscribeOk();

        void subscribeErr(String strErr);

        void sendPublishErr(String strErr);

        void addXLSuccess();

        void saveLDSuccess();

        void saveLDFailure(String err);

        void saveAngleSuccess();

        void saveAngleFailure(String err);

        void queryAngleSuccess(List<ShootAngleDao> list);
        void queryAngleFailure(String err);

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void MQTTConnHost(String userName, String name, String pW, MQTT mqtt);

        public abstract void subscribeAllTopic(Topic[] topics);

        public abstract void sendPublishData(Object b, CallbackConnection connection);

        public abstract void addXL(String serialNumber, String dTFX, String xlNum, String XLQ, String XLZ, CallbackConnection connection);

        public abstract void saveAngleDetail(EmptyControlVideo detailPlayer, MagDevice mDev, String serialNumber,
                                             int cameraType, int cameraX, int cameraY, int cameraZ);

        public abstract void queryAngleDetail(String serialNumber);

        public abstract void saveLocationDetails(String serialNumber, String towerNo, int towerType,
                                                 int direction, int inCharge, int fzcNum);
    }
}
