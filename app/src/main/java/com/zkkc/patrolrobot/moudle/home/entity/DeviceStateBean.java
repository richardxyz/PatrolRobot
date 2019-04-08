package com.zkkc.patrolrobot.moudle.home.entity;

/**
 * Created by ShiJunRan on 2019/3/25
 * 机器模式及配置状态
 */
public class DeviceStateBean {


    /**
     * serialNum : 1000-1001-008-SXXX-171229
     * module : 1
     * mainState : 0
     * subState : 0
     */

    private String serialNum;
    private int module;
    private int mainState;
    private int subState;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public int getMainState() {
        return mainState;
    }

    public void setMainState(int mainState) {
        this.mainState = mainState;
    }

    public int getSubState() {
        return subState;
    }

    public void setSubState(int subState) {
        this.subState = subState;
    }
}
