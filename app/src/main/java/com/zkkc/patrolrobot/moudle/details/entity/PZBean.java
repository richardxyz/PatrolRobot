package com.zkkc.patrolrobot.moudle.details.entity;

/**
 * Created by ShiJunRan on 2019/3/19
 */
public class PZBean {
     private String thNum;
     private String pzName;
     private String psdNum;
     private String wcDate;

    public PZBean() {

    }

    public String getPzName() {
        return pzName;
    }

    public void setPzName(String pzName) {
        this.pzName = pzName;
    }

    public String getPsdNum() {
        return psdNum;
    }

    public void setPsdNum(String psdNum) {
        this.psdNum = psdNum;
    }

    public String getWcDate() {
        return wcDate;
    }

    public void setWcDate(String wcDate) {
        this.wcDate = wcDate;
    }

    public String getThNum() {
        return thNum;
    }

    public void setThNum(String thNum) {
        this.thNum = thNum;
    }
}
