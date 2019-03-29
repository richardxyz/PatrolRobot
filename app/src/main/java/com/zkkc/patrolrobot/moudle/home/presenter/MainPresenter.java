package com.zkkc.patrolrobot.moudle.home.presenter;

import android.content.Context;

import com.zkkc.patrolrobot.moudle.home.callback.IAddXL;
import com.zkkc.patrolrobot.moudle.home.callback.IBaseCallback;
import com.zkkc.patrolrobot.moudle.home.callback.IMQTTConnHost;
import com.zkkc.patrolrobot.moudle.home.contract.MainContract;
import com.zkkc.patrolrobot.moudle.home.model.MainModel;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.Topic;

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

    @Override
    public void MQTTConnHost(String userName, String name, String pW, MQTT mqtt) {
        model.MQTTConnHost(userName, name, pW, mqtt, new IMQTTConnHost() {
            @Override
            public void onFailure(String strErr) {
                getView().mFailure(strErr);
            }

            @Override
            public void onConnected(CallbackConnection connection, String str) {
                getView().mConnected(connection, str);
            }

            @Override
            public void onDisconnected(String str) {
                getView().mDisconnected(str);
            }

            @Override
            public void onPublish(UTF8Buffer topic, Buffer body, Runnable ack) {
                getView().mPublish(topic, body);
            }
        });
    }

    @Override
    public void subscribeAllTopic(Topic[] topics) {
        model.subscribeAllTopic(topics, new IBaseCallback() {
            @Override
            public void onSuccess() {
                getView().subscribeOk();
            }

            @Override
            public void onFailure(String strErr) {
                getView().subscribeErr(strErr);
            }
        });
    }

    @Override
    public void sendPublishData(Object b, CallbackConnection connection) {
        model.sendPublishData(b, connection, new IBaseCallback() {
            @Override
            public void onSuccess() {
                getView().sendPublishSuccess();
            }

            @Override
            public void onFailure(String strErr) {
                getView().sendPublishErr(strErr);
            }
        });
    }

    @Override
    public void addXL(String xlNum, String XLQ, String XLZ, CallbackConnection connection) {
        model.addXL(xlNum, XLQ, XLZ, connection, new IBaseCallback() {
            @Override
            public void onSuccess() {
                getView().addXLSuccess();
            }

            @Override
            public void onFailure(String strErr) {
                getView().sendPublishErr(strErr);
            }
        }, new IAddXL() {
            @Override
            public void onFailure(String strErr) {
                getView().sendPublishErr(strErr);
            }
        });

    }
}
