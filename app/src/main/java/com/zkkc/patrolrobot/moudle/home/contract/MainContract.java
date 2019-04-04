package com.zkkc.patrolrobot.moudle.home.contract;


import com.zkkc.patrolrobot.base.BasePresenter;
import com.zkkc.patrolrobot.base.BaseView;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.Topic;


/**
 * Created by ShiJunRan on 2019/3/7
 */
public interface MainContract {
    interface View extends BaseView {
        void mFailure(String strErr);

        void mConnected(CallbackConnection connection,String str);

        void mDisconnected(String str);

        void mPublish(UTF8Buffer topic, Buffer body);

        void subscribeOk();

        void subscribeErr(String strErr);

        void sendPublishSuccess();

        void sendPublishErr(String strErr);

        void addXLSuccess();

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void MQTTConnHost(String userName, String name, String pW, MQTT mqtt);

        public abstract void subscribeAllTopic(Topic[] topics);

        public abstract void sendPublishData(Object b, CallbackConnection connection);

        public abstract void addXL(String serialNumber,String dTFX,String xlNum,String XLQ, String XLZ, CallbackConnection connection);


    }
}
