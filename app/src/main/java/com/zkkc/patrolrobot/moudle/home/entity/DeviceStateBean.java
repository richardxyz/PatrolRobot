package com.zkkc.patrolrobot.moudle.home.entity;

/**
 * Created by ShiJunRan on 2019/3/25
 * 机器模式及配置状态
 */
public class DeviceStateBean {

    /**
     * SerialNum : 1000-1001-008-SXXX-171229
     * Module : 1
     * MianState : 0
     * Substate : 0
     */

    private String SerialNum;
    private int Module;
    private int MainState;
    private int SubState;

    public int getMainState() {
        return MainState;
    }

    public void setMainState(int mainState) {
        MainState = mainState;
    }

    public String getSerialNum() {
        return SerialNum;
    }

    public void setSerialNum(String SerialNum) {
        this.SerialNum = SerialNum;
    }

    public int getModule() {
        return Module;
    }

    public void setModule(int Module) {
        this.Module = Module;
    }

    public int getSubState() {
        return SubState;
    }

    public void setSubState(int subState) {
        SubState = subState;
    }
}
