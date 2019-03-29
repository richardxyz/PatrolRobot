package com.zkkc.patrolrobot.moudle.home.callback;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.CallbackConnection;

/**
 * Created by ShiJunRan on 2019/3/27
 */
public interface IMQTTConnHost {
//    void onSuccess(String str);

    void onFailure(String strErr);

    void onConnected(CallbackConnection connection,String str);

    void onDisconnected(String str);

    void onPublish(UTF8Buffer topic, Buffer body, Runnable ack);
}
