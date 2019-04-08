package com.zkkc.patrolrobot.moudle.home.model;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zkkc.patrolrobot.TrackConstant;
import com.zkkc.patrolrobot.base.BaseModel;
import com.zkkc.patrolrobot.moudle.home.callback.IAddXL;
import com.zkkc.patrolrobot.moudle.home.callback.IBaseCallback;
import com.zkkc.patrolrobot.moudle.home.callback.IMQTTConnHost;
import com.zkkc.patrolrobot.moudle.home.entity.PZCSBean;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import static com.zkkc.patrolrobot.moudle.home.activity.HomeAct.DTFX;
import static com.zkkc.patrolrobot.moudle.home.activity.HomeAct.NAME;
import static com.zkkc.patrolrobot.moudle.home.activity.HomeAct.PW;
import static com.zkkc.patrolrobot.moudle.home.activity.HomeAct.USER_NAME;
import static com.zkkc.patrolrobot.moudle.home.activity.HomeAct.XL_NUM;
import static com.zkkc.patrolrobot.moudle.home.activity.HomeAct.XL_Q;
import static com.zkkc.patrolrobot.moudle.home.activity.HomeAct.XL_Z;

/**
 * Created by ShiJunRan on 2019/3/7
 */
public class MainModel extends BaseModel {
    private CallbackConnection connection;

    /**
     * 连接MQTT服务器
     *
     * @param userName 操作人姓名
     * @param name     登录名
     * @param pW       密码
     * @param mqtt
     * @param callBack
     */
    public void MQTTConnHost(String userName, String name, String pW, MQTT mqtt, final IMQTTConnHost callBack) {
        if (!"".equals(userName) && !"".equals(name) && !"".equals(pW)) {
            SPUtils.getInstance().put(USER_NAME, userName);
            SPUtils.getInstance().put(NAME, name);
            SPUtils.getInstance().put(PW, pW);
        } else {
            callBack.onFailure("用户名或密码不能为空");
            return;
        }
        mqtt.setUserName(name);
        mqtt.setPassword(pW);
        connection = mqtt.callbackConnection();
        connection.connect(new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
//                callBack.onSuccess("连接成功");
            }

            @Override
            public void onFailure(Throwable value) {
                callBack.onFailure("连接服务器失败");
            }
        });


        connection.listener(new Listener() {
            @Override
            public void onConnected() {
                callBack.onConnected(connection, "连接成功");
            }

            @Override
            public void onDisconnected() {
                callBack.onDisconnected("服务器连接中断，正在重连···");
            }

            @Override
            public void onPublish(UTF8Buffer topic, Buffer body, Runnable ack) {
                ack.run();
                callBack.onPublish(topic, body, ack);
            }

            @Override
            public void onFailure(Throwable value) {
                callBack.onFailure("连接服务器失败");
            }
        });

    }

    /**
     * 订阅topic主题
     *
     * @param topics
     * @param callback
     */
    public void subscribeAllTopic(Topic[] topics, final IBaseCallback callback) {
        connection.subscribe(topics, new Callback<byte[]>() {
            public void onSuccess(byte[] qoses) {
                // The result of the subcribe request.
                callback.onSuccess();
            }

            public void onFailure(Throwable value) {
                callback.onFailure("订阅失败，重新订阅中···");
                //如果订阅失败的话，就主动中断当前连接并且进行重连
                connection.disconnect(new Callback<Void>() {
                    @Override
                    public void onSuccess(Void value) {
                    }

                    @Override
                    public void onFailure(Throwable value) {
                    }
                });
            }
        });

    }

    /**
     * 发布
     *
     * @param b
     * @param connection
     * @param callback
     */
    public void sendPublishData(Object b, CallbackConnection connection, final IBaseCallback callback) {
        connection.publish(TrackConstant.DEVICE_OP, GsonUtils.toJson(b).getBytes(), QoS.AT_LEAST_ONCE, false, new Callback<Void>() {
            public void onSuccess(Void v) {
                callback.onSuccess();
            }

            public void onFailure(Throwable value) {
                callback.onFailure("操作失败");
            }
        });

    }

    public void addXL(String serialNumber,String dTFX,String xlNum, String XLQ, String XLZ, CallbackConnection connection, IBaseCallback baseCallback, IAddXL callback) {
        if (!"".equals(xlNum) && !"".equals(XLQ) && !"".equals(XLZ)) {
            SPUtils.getInstance().put(DTFX, dTFX);
            SPUtils.getInstance().put(XL_NUM, xlNum);
            SPUtils.getInstance().put(XL_Q, XLQ);
            SPUtils.getInstance().put(XL_Z, XLZ);
        } else {
            callback.onFailure("数据不能为空");
            return;
        }
        //添加线路配置信息
        PZCSBean pzcsBean = new PZCSBean();
        pzcsBean.setModule(10);
        pzcsBean.setOp(0);
        pzcsBean.setSerialNum(serialNumber);
        PZCSBean.DataBean dataBean = new PZCSBean.DataBean();
        dataBean.setBigTowerDir(Integer.parseInt(dTFX));
        dataBean.setLineNum(xlNum);
        dataBean.setInitialPoint(XLQ);
        dataBean.setEndPoint(XLZ);
        pzcsBean.setData(dataBean);
        sendPublishData(pzcsBean, connection, baseCallback);
    }


}
