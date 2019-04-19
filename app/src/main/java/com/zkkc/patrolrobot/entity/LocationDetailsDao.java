package com.zkkc.patrolrobot.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ShiJunRan on 2019/4/15
 * 拍摄点
 */
@Entity
public class LocationDetailsDao {
    @Id(autoincrement = true)
    private Long id;
    private String serialNo;//序列号
    private String towerNo;//塔号
    private int towerType;//塔型 1直线塔  2耐张塔
    private int direction;//方向
    private int inCharge;//是否存在充电桩
    private int fzcNum;//防震锤数量
    private String mDate;//录入时间
    private String mCZR;//录入时间
    @Generated(hash = 1868982343)
    public LocationDetailsDao(Long id, String serialNo, String towerNo,
            int towerType, int direction, int inCharge, int fzcNum, String mDate,
            String mCZR) {
        this.id = id;
        this.serialNo = serialNo;
        this.towerNo = towerNo;
        this.towerType = towerType;
        this.direction = direction;
        this.inCharge = inCharge;
        this.fzcNum = fzcNum;
        this.mDate = mDate;
        this.mCZR = mCZR;
    }
    @Generated(hash = 1748225134)
    public LocationDetailsDao() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSerialNo() {
        return this.serialNo;
    }
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
    public String getTowerNo() {
        return this.towerNo;
    }
    public void setTowerNo(String towerNo) {
        this.towerNo = towerNo;
    }
    public int getTowerType() {
        return this.towerType;
    }
    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }
    public int getDirection() {
        return this.direction;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int getInCharge() {
        return this.inCharge;
    }
    public void setInCharge(int inCharge) {
        this.inCharge = inCharge;
    }
    public int getFzcNum() {
        return this.fzcNum;
    }
    public void setFzcNum(int fzcNum) {
        this.fzcNum = fzcNum;
    }
    public String getMDate() {
        return this.mDate;
    }
    public void setMDate(String mDate) {
        this.mDate = mDate;
    }
    public String getMCZR() {
        return this.mCZR;
    }
    public void setMCZR(String mCZR) {
        this.mCZR = mCZR;
    }

}
