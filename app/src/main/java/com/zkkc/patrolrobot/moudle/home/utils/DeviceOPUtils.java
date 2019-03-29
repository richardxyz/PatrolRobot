package com.zkkc.patrolrobot.moudle.home.utils;


import com.zkkc.patrolrobot.moudle.home.activity.HomeAct;
import com.zkkc.patrolrobot.moudle.home.entity.DeviceConfigurationState;

import org.fusesource.mqtt.client.CallbackConnection;

/**
 * Created by ShiJunRan on 2019/3/29
 */
public class DeviceOPUtils {
    /**
     * 查询当前配置状态
     *
     * @param mContext
     * @param connection
     */
    public static void queryPZZT(HomeAct mContext, CallbackConnection connection) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum("");
        state.setModule(0);
        state.setOp(2);
        mContext.getPresenter().sendPublishData(state, connection);
    }
    /**
     * 查询线路配置信息
     *
     * @param mContext
     * @param connection
     */
    public static void queryXLPZXX(HomeAct mContext, CallbackConnection connection) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum("");
        state.setModule(0);
        state.setOp(1);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 进入配置模式
     *
     * @param mContext
     * @param connection
     */
    public static void inPZMS(HomeAct mContext, CallbackConnection connection) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum("");
        state.setModule(5);
        state.setOp(0);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 正常前行
     *
     * @param mContext
     * @param connection
     */
    public static void deviceUp(HomeAct mContext, CallbackConnection connection) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum("");
        state.setModule(2);
        state.setOp(2);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 慢速前行
     *
     * @param mContext
     * @param connection
     */
    public static void deviceUpLow(HomeAct mContext, CallbackConnection connection) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum("");
        state.setModule(2);
        state.setOp(2);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 停止
     *
     * @param mContext
     * @param connection
     */
    public static void deviceStop(HomeAct mContext, CallbackConnection connection) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum("");
        state.setModule(2);
        state.setOp(5);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 正常后退
     *
     * @param mContext
     * @param connection
     */
    public static void deviceDown(HomeAct mContext, CallbackConnection connection) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum("");
        state.setModule(2);
        state.setOp(4);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 慢速后退
     *
     * @param mContext
     * @param connection
     */
    public static void deviceDownLow(HomeAct mContext, CallbackConnection connection) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum("");
        state.setModule(2);
        state.setOp(4);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 摄像头左转（红外和可见光）
     *
     * @param mContext
     * @param connection
     */
    public static void cameraLeft(HomeAct mContext, CallbackConnection connection,boolean isHW) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum("");
        if (isHW) {
            state.setModule(6);
        } else {
            state.setModule(7);
        }
        state.setOp(2);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 摄像头右转（红外和可见光）
     *
     * @param mContext
     * @param connection
     */
    public static void cameraRight(HomeAct mContext, CallbackConnection connection,boolean isHW) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum("");
        if (isHW) {
            state.setModule(6);
        } else {
            state.setModule(7);
        }
        state.setOp(3);
        mContext.getPresenter().sendPublishData(state, connection);
    }
    /**
     * 摄像头上转（红外和可见光）
     *
     * @param mContext
     * @param connection
     */
    public static void cameraUp(HomeAct mContext, CallbackConnection connection,boolean isHW) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum("");
        if (isHW) {
            state.setModule(6);
        } else {
            state.setModule(7);
        }
        state.setOp(0);
        mContext.getPresenter().sendPublishData(state, connection);
    }
    /**
     * 摄像头下转（红外和可见光）
     *
     * @param mContext
     * @param connection
     */
    public static void cameraDown(HomeAct mContext, CallbackConnection connection,boolean isHW) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum("");
        if (isHW) {
            state.setModule(6);
        } else {
            state.setModule(7);
        }
        state.setOp(1);
        mContext.getPresenter().sendPublishData(state, connection);
    }
    /**
     * 摄像头停止转动（红外和可见光）
     *
     * @param mContext
     * @param connection
     */
    public static void cameraStop(HomeAct mContext, CallbackConnection connection,boolean isHW) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum("");
        if (isHW) {
            state.setModule(6);
        } else {
            state.setModule(7);
        }
        state.setOp(4);
        mContext.getPresenter().sendPublishData(state, connection);
    }
}
