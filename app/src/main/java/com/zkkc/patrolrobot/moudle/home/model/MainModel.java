package com.zkkc.patrolrobot.moudle.home.model;

import android.net.Uri;
import android.os.Environment;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
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
import com.zkkc.patrolrobot.moudle.home.callback.IQueryAngleCallback;
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
import org.greenrobot.greendao.query.Query;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

import cn.com.magnity.sdk.MagDevice;


/**
 * Created by ShiJunRan on 2019/3/7
 */
public class MainModel extends BaseModel {
    private CallbackConnection connection;
    public static final String USER_NAME = "USER_NAME";
    public static final String NAME = "NAME";
    public static final String PW = "PW";
    public static final String DTFX = "DTFX";
    public static final String XL_NUM = "XL_NUM";
    public static final String XL_Q = "XL_Q";
    public static final String XL_Z = "XL_Z";
    public static final String TOWER_NO = "TOWER_NO";
    public static final String TOWER_TOTAL = "TOWER_TOTAL";
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
                callBack.onDisconnected("服务器连接中断,正在重连...");
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
                String tower_no = SPUtils.getInstance().getString(TOWER_NO);
                int tower_total = SPUtils.getInstance().getInt(TOWER_TOTAL);
                if (!towerNo.equals(tower_no)) {
                    tower_total++;
                }
                LocationDetailsDao lDDao = new LocationDetailsDao();
                lDDao.setSerialNo(serialNumber);
                lDDao.setTowerNo(towerNo);
                lDDao.setTowerType(towerType);
                lDDao.setDirection(direction);
                lDDao.setFzcNum(fzcNum);
                lDDao.setInCharge(inCharge);
                lDDao.setMDate(getNowDate());
                lDDao.setMCZR(SPUtils.getInstance().getString(USER_NAME));
                getLDDao().insert(lDDao);
                SPUtils.getInstance().put(TOWER_NO, towerNo);
                SPUtils.getInstance().put(TOWER_TOTAL, tower_total);
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
     * @param cameraType   1-可见光  2-红外
     * @param cameraX
     * @param cameraY
     * @param cameraZ
     * @param callback
     */
    public void saveAngleDetail(ExecutorService threadPool, final EmptyControlVideo detailPlayer, final MagDevice mDev, final String serialNumber,
                                final int cameraType, final int cameraX, final int cameraY, final int cameraZ, final ISaveAngleCallback callback) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                if (cameraType == 1) {//可见光
                    detailPlayer.saveFrame(bitmapToFile(false), true, new GSYVideoShotSaveListener() {
                        @Override
                        public void result(boolean success, File file) {
                            if (success) {
                                Uri uri = UriUtils.file2Uri(file);
                                LocationDetailsDao locationDetailsDao = queryLDDao();
                                if (locationDetailsDao != null) {
                                    ShootAngleDao shootAngleDao = new ShootAngleDao();
                                    shootAngleDao.setSerialNo(serialNumber);
                                    shootAngleDao.setTowerNo(locationDetailsDao.getTowerNo());
                                    shootAngleDao.setTowerType(locationDetailsDao.getTowerType());
                                    shootAngleDao.setDirection(locationDetailsDao.getDirection());
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
                                callback.onFailure("保存本地图片失败");
                            }


                        }
                    });
                } else {//红外
                    File mFile = bitmapToFile(true);
                    Uri uriA = UriUtils.file2Uri(mFile);
                    boolean success = mDev.saveBMP(0, uriA.toString());

                    if (success) {
                        Uri uri = UriUtils.file2Uri(mFile);
                        LocationDetailsDao locationDetailsDao = queryLDDao();
                        if (locationDetailsDao != null) {
                            ShootAngleDao shootAngleDao = new ShootAngleDao();
                            shootAngleDao.setSerialNo(serialNumber);
                            shootAngleDao.setTowerNo(locationDetailsDao.getTowerNo());
                            shootAngleDao.setTowerType(locationDetailsDao.getTowerType());
                            shootAngleDao.setDirection(locationDetailsDao.getDirection());
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
                        callback.onFailure("保存本地图片失败");
                    }

                }

            }
        });
    }

    /**
     * 查询当前拍摄点已配置的角度信息
     *
     * @param threadPool
     * @param serialNumber
     */
    public void queryAngleDetail(ExecutorService threadPool, String serialNumber, final IQueryAngleCallback callback) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                LocationDetailsDao locationDetailsDao = queryLDDao();
                if (locationDetailsDao != null) {
                    Query<ShootAngleDao> queryBuilder = getAngleDao().queryBuilder()
                            .where(ShootAngleDaoDao.Properties.SerialNo.eq(locationDetailsDao.getSerialNo()),
                                    ShootAngleDaoDao.Properties.TowerNo.eq(locationDetailsDao.getTowerNo()),
                                    ShootAngleDaoDao.Properties.Direction.eq(locationDetailsDao.getDirection()))
                            .build();
                    List<ShootAngleDao> list = queryBuilder.list();
                    if (list != null) {
                        if (list.size() > 0) {
                            callback.onSuccess(list);
                        } else {
                            callback.onFailure("暂无数据");
                        }

                    } else {
                        callback.onFailure("暂无数据");
                    }

                } else {
                    callback.onFailure("暂无数据");
                }
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
            if (locationDetailsDaos.size() > 0) {
                return locationDetailsDaos.get(locationDetailsDaos.size() - 1);
            } else {
                return null;
            }

        }
        return null;
    }

    private File bitmapToFile(boolean isHw) {
        String fileName = null;
        String nowDate = getNowDate();
        File filesDir = Environment.getExternalStorageDirectory();
        File appDir = new File(filesDir, "a_track");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        if (isHw){
            fileName = nowDate + ".BMP";
        }else {
             fileName = nowDate + ".png";
        }
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
}
