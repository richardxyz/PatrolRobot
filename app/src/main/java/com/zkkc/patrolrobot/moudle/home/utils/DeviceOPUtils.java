package com.zkkc.patrolrobot.moudle.home.utils;


import com.blankj.utilcode.util.ToastUtils;
import com.zkkc.patrolrobot.moudle.home.activity.MainAct;
import com.zkkc.patrolrobot.moudle.home.entity.DeviceConfigurationState;
import com.zkkc.patrolrobot.moudle.home.entity.PZCSBean;

import org.fusesource.mqtt.client.CallbackConnection;

/**
 * Created by ShiJunRan on 2019/3/29
 */
public class DeviceOPUtils {
    /**
     * 查询当前机器状态
     *
     * @param mContext
     * @param connection
     */
    public static void queryJQZT(MainAct mContext, CallbackConnection connection, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
        state.setModule(0);
        state.setOp(0);
        mContext.getPresenter().sendPublishData(state, connection);
    }


    /**
     * 查询当前配置状态
     *
     * @param mContext
     * @param connection
     */
    public static void queryPZZT(MainAct mContext, CallbackConnection connection, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
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
    public static void queryXLPZXX(MainAct mContext, CallbackConnection connection, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
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
    public static void inPZMS(MainAct mContext, CallbackConnection connection, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
        state.setModule(5);
        state.setOp(0);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 一键安装
     *
     * @param mContext
     * @param connection
     */
    public static void inYJAZ(MainAct mContext, CallbackConnection connection, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
        state.setModule(1);
        state.setOp(0);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 拍摄点信息录入
     *
     * @param mContext
     * @param connection
     */
    public static void inPSDXX(MainAct mContext, CallbackConnection connection, String serialNumber, PZCSBean.DataBean bean) {
        PZCSBean state = new PZCSBean();
        state.setSerialNum(serialNumber);
        state.setModule(8);
        state.setOp(0);
        state.setData(bean);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 角度确认
     *
     * @param mContext
     * @param connection
     */
    public static void inJDQR(MainAct mContext, CallbackConnection connection, boolean isHw, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
        if (isHw) {//红外
            state.setModule(6);
            state.setOp(5);
        } else {//可见光
            state.setModule(7);
            state.setOp(7);
        }
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 配置完成指令
     *
     * @param mContext
     * @param connection
     */
    public static void inPZOK(MainAct mContext, CallbackConnection connection, String serialNumber) {
        PZCSBean state = new PZCSBean();
        state.setSerialNum(serialNumber);
        state.setModule(8);
        state.setOp(1);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 行进方向设置
     *
     * @param mContext
     * @param connection
     */
    public static void inXJFX(MainAct mContext, CallbackConnection connection, int num, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
        state.setModule(2);
        state.setOp(num);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 正常前行
     *
     * @param mContext
     * @param connection
     */
    public static void deviceUp(MainAct mContext, CallbackConnection connection, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
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
    public static void deviceUpLow(MainAct mContext, CallbackConnection connection, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
        state.setModule(2);
        state.setOp(1);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 停止
     *
     * @param mContext
     * @param connection
     */
    public static void deviceStop(MainAct mContext, CallbackConnection connection, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
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
    public static void deviceDown(MainAct mContext, CallbackConnection connection, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
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
    public static void deviceDownLow(MainAct mContext, CallbackConnection connection, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
        state.setModule(2);
        state.setOp(3);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 摄像头左转（红外和可见光）
     *
     * @param mContext
     * @param connection
     */
    public static void cameraLeft(MainAct mContext, CallbackConnection connection, boolean isHW, String serialNumber, PZCSBean.DataBean bean) {
        PZCSBean state = new PZCSBean();
        state.setSerialNum(serialNumber);
        if (isHW) {
            state.setModule(6);
        } else {
            state.setModule(7);
        }
        state.setOp(2);
        state.setData(bean);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 摄像头右转（红外和可见光）
     *
     * @param mContext
     * @param connection
     */
    public static void cameraRight(MainAct mContext, CallbackConnection connection, boolean isHW, String serialNumber, PZCSBean.DataBean bean) {
        PZCSBean state = new PZCSBean();
        state.setSerialNum(serialNumber);
        if (isHW) {
            state.setModule(6);
        } else {
            state.setModule(7);
        }
        state.setOp(3);
        state.setData(bean);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 摄像头上转（红外和可见光）
     *
     * @param mContext
     * @param connection
     */
    public static void cameraUp(MainAct mContext, CallbackConnection connection, boolean isHW, String serialNumber, PZCSBean.DataBean bean) {
        PZCSBean state = new PZCSBean();
        state.setSerialNum(serialNumber);
        if (isHW) {
            state.setModule(6);
        } else {
            state.setModule(7);
        }
        state.setOp(0);
        state.setData(bean);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 摄像头下转（红外和可见光）
     *
     * @param mContext
     * @param connection
     */
    public static void cameraDown(MainAct mContext, CallbackConnection connection, boolean isHW, String serialNumber, PZCSBean.DataBean bean) {
        PZCSBean state = new PZCSBean();
        state.setSerialNum(serialNumber);
        if (isHW) {
            state.setModule(6);
        } else {
            state.setModule(7);
        }
        state.setOp(1);
        state.setData(bean);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 摄像头停止转动（红外和可见光）
     *
     * @param mContext
     * @param connection
     */
    public static void cameraStop(MainAct mContext, CallbackConnection connection, boolean isHW, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
        if (isHW) {
            state.setModule(6);
        } else {
            state.setModule(7);
        }
        state.setOp(4);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 可见光摄像头调焦
     *
     * @param mContext
     * @param connection
     */
    public static void cameraFocus(MainAct mContext, CallbackConnection connection, boolean adjust, String serialNumber, PZCSBean.DataBean bean) {
        PZCSBean state = new PZCSBean();
        state.setSerialNum(serialNumber);
        state.setModule(7);
        if (adjust) {
            state.setOp(5);//放大
        } else {
            state.setOp(6);//缩小
        }
        state.setData(bean);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 配置模式暂停
     *
     * @param mContext
     * @param connection
     */
    public static void pzmsStop(MainAct mContext, CallbackConnection connection, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
        state.setModule(5);
        state.setOp(1);
        mContext.getPresenter().sendPublishData(state, connection);
    }
    /**
     * 退出配置模式
     *
     * @param mContext
     * @param connection
     */
    public static void pzmsOut(MainAct mContext, CallbackConnection connection, String serialNumber) {
        DeviceConfigurationState state = new DeviceConfigurationState();
        state.setSerialNum(serialNumber);
        state.setModule(5);
        state.setOp(2);
        mContext.getPresenter().sendPublishData(state, connection);
    }

    /**
     * 机器人悬臂控制
     *
     * @param mContext
     * @param connection
     */
    public static void xbKZ(MainAct mContext, CallbackConnection connection, String serialNumber, int num) {
        if (connection != null) {
            DeviceConfigurationState state = new DeviceConfigurationState();
            state.setSerialNum(serialNumber);
            state.setModule(3);
            state.setOp(num);
            mContext.getPresenter().sendPublishData(state, connection);
        } else {
            ToastUtils.showShort("当前未登录");
        }

    }

    /**
     * 唤醒
     *
     * @param mContext
     * @param connection
     */
    public static void hx(MainAct mContext, CallbackConnection connection, String serialNumber) {
        if (connection != null) {
            DeviceConfigurationState state = new DeviceConfigurationState();
            state.setSerialNum(serialNumber);
            state.setModule(2);
            state.setOp(0);
            mContext.getPresenter().sendPublishData(state, connection);
        } else {
            ToastUtils.showShort("当前未登录");
        }

    }
    /**
     * 手动模式和自动模式
     * @param mContext
     * @param connection
     * @param serialNumber
     * @param ms   0-手动模式    1-自动模式
     */
    public static void sdAndZd(MainAct mContext, CallbackConnection connection, String serialNumber, int ms) {
        if (connection != null) {
            DeviceConfigurationState state = new DeviceConfigurationState();
            state.setSerialNum(serialNumber);
            state.setModule(4);
            state.setOp(ms);
            mContext.getPresenter().sendPublishData(state, connection);
        } else {
            ToastUtils.showShort("当前未登录");
        }

    }

}
