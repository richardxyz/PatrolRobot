package com.zkkc.patrolrobot.moudle.home.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.UriUtils;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotSaveListener;
import com.zkkc.green.gen.LocationDetailsDaoDao;
import com.zkkc.green.gen.ShootAngleDaoDao;
import com.zkkc.patrolrobot.TrackConstant;
import com.zkkc.patrolrobot.base.BaseModel;
import com.zkkc.patrolrobot.common.GreenDaoManager;
import com.zkkc.patrolrobot.entity.LocationDetailsDao;
import com.zkkc.patrolrobot.entity.ShootAngleDao;
import com.zkkc.patrolrobot.moudle.home.callback.IAddXL;
import com.zkkc.patrolrobot.moudle.home.callback.IBaseCallback;
import com.zkkc.patrolrobot.moudle.home.callback.IMQTTConnHost;
import com.zkkc.patrolrobot.moudle.home.callback.ISaveAngleCallback;
import com.zkkc.patrolrobot.moudle.home.entity.PZCSBean;
import com.zkkc.patrolrobot.widget.EmptyControlVideo;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

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
        LogUtils.i("SJR_PUSH", GsonUtils.toJson(b));
        connection.publish(TrackConstant.DEVICE_OP, GsonUtils.toJson(b).getBytes(), QoS.AT_LEAST_ONCE, false, new Callback<Void>() {
            public void onSuccess(Void v) {
                callback.onSuccess();

            }

            public void onFailure(Throwable value) {
                callback.onFailure("操作失败");
            }
        });

    }

    /**
     * 添加线路信息
     *
     * @param serialNumber
     * @param dTFX
     * @param xlNum
     * @param XLQ
     * @param XLZ
     * @param connection
     * @param baseCallback
     * @param callback
     */
    public void addXL(String serialNumber, String dTFX, String xlNum, String XLQ, String XLZ, CallbackConnection connection, IBaseCallback baseCallback, IAddXL callback) {
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

    /**
     * 保存拍摄点信息
     *
     * @param threadPool
     * @param serialNumber
     * @param towerNo
     * @param towerType
     * @param direction
     * @param inCharge
     * @param fzcNum
     * @param callback
     */
    public void saveLocationDetails(ExecutorService threadPool, final String serialNumber, final String towerNo, final int towerType,
                                    final int direction, final int inCharge, final int fzcNum, final ISaveAngleCallback callback) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                LocationDetailsDao lDDao = new LocationDetailsDao();
                lDDao.setSerialNo(serialNumber);
                lDDao.setTowerNo(towerNo);
                lDDao.setTowerType(towerType);
                lDDao.setDirection(direction);
                lDDao.setFzcNum(fzcNum);
                lDDao.setInCharge(inCharge);
                getLDDao().insert(lDDao);
                callback.onSuccess();
            }
        });
    }

    /**
     * 保存角度信息和图片
     *
     * @param threadPool
     * @param detailPlayer
     * @param serialNumber
     * @param towerNo
     * @param towerType
     * @param cameraType
     * @param cameraX
     * @param cameraY
     * @param cameraZ
     * @param callback
     */
    public void saveAngleDetail(ExecutorService threadPool, final EmptyControlVideo detailPlayer, final String serialNumber, final String towerNo,
                                final int towerType, final int cameraType, final int cameraX, final int cameraY, final int cameraZ,
                                final ISaveAngleCallback callback) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                detailPlayer.saveFrame(bitmapToFile(), true, new GSYVideoShotSaveListener() {
                    @Override
                    public void result(boolean success, File file) {
                        if (success) {
                            Uri uri = UriUtils.file2Uri(file);
//                            Uri uri = Uri.parse((String) str);
                            LocationDetailsDao locationDetailsDao = queryLDDao();
                            if (locationDetailsDao != null) {
                                ShootAngleDao shootAngleDao = new ShootAngleDao();
                                shootAngleDao.setSerialNo(serialNumber);
                                shootAngleDao.setTowerNo(locationDetailsDao.getTowerNo());
                                shootAngleDao.setTowerType(locationDetailsDao.getTowerType());
                                shootAngleDao.setCameraType(cameraType);
                                shootAngleDao.setCameraX(cameraX);
                                shootAngleDao.setCameraY(cameraY);
                                shootAngleDao.setCameraZ(cameraZ);
                                shootAngleDao.setPictureUri(uri.toString());
                                getAngleDao().insert(shootAngleDao);
                                //------test--------
                                List<ShootAngleDao> shootAngleDaos = getAngleDao().loadAll();
                                if (shootAngleDaos != null) {
                                    for (ShootAngleDao b : shootAngleDaos) {
                                        LogUtils.i("JSON_LOG_ANGLE", GsonUtils.toJson(b));
                                    }
                                }
                                //------test--------
                                callback.onSuccess();
                            } else {
                                callback.onFailure("获取拍摄点配置详情失败，无法保存角度信息");
                            }
                        } else {
                            callback.onFailure("保存图片失败");
                        }


                    }
                });


            }
        });


    }

    private LocationDetailsDao queryLDDao() {
        List<LocationDetailsDao> locationDetailsDaos = getLDDao().loadAll();
        if (locationDetailsDaos != null) {
            for (LocationDetailsDao b : locationDetailsDaos) {
                LogUtils.i("JSON_LOG_LD", GsonUtils.toJson(b));
            }
        }
        if (locationDetailsDaos != null) {
            return locationDetailsDaos.get(locationDetailsDaos.size() - 1);
        }
        return null;
    }

    private File bitmapToFile() {
        String nowDate = getNowDate();
        File filesDir = Environment.getExternalStorageDirectory();
        File appDir = new File(filesDir, "a_track");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = nowDate + ".png";
        File file = new File(appDir, fileName);
        return file;
    }

    //角度
    private ShootAngleDaoDao getAngleDao() {
        return GreenDaoManager.getInstance().getSession().getShootAngleDaoDao();
    }

    //拍摄点
    private LocationDetailsDaoDao getLDDao() {
        return GreenDaoManager.getInstance().getSession().getLocationDetailsDaoDao();
    }

    /**
     * 获取系统当前时间
     */
    private String getNowDate() {
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
        return format;
    }

    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
