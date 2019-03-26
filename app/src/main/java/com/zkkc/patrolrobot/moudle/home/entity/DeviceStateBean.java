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
    private int MianState;
    private int Substate;

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

    public int getMianState() {
        return MianState;
    }

    public void setMianState(int MianState) {
        this.MianState = MianState;
    }

    public int getSubstate() {
        return Substate;
    }

    public void setSubstate(int Substate) {
        this.Substate = Substate;
    }
}
