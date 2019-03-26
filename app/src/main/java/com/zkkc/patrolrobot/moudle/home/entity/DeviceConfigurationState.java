package com.zkkc.patrolrobot.moudle.home.entity;

/**
 * Created by ShiJunRan on 2019/3/26
 */
public class DeviceConfigurationState {

    /**
     * SerialNum : xxxxxxxx
     * Module : 1
     * Op : 1
     */

    private String SerialNum;
    private int Module;
    private int Op;

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

    public int getOp() {
        return Op;
    }

    public void setOp(int Op) {
        this.Op = Op;
    }
}
