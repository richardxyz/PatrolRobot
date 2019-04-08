package com.zkkc.patrolrobot.moudle.home.entity;

/**
 * Created by ShiJunRan on 2019/3/26
 */
public class DeviceConfigurationState {

    /**
     * serialNum :
     * module : 1
     * op : 1
     */

    private String serialNum;
    private int module;
    private int op;

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

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }
}
