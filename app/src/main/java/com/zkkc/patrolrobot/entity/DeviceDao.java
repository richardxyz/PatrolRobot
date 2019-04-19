package com.zkkc.patrolrobot.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ShiJunRan on 2019/4/18
 */
@Entity
public class DeviceDao {
    @Id(autoincrement = true)
    private Long id;

    private String dName;
    private String dSer;
    private String dWifi;
    private int isCheck;//0-false   1-true
    private int isShowXb;//0-false   1-true
    @Generated(hash = 1547339380)
    public DeviceDao(Long id, String dName, String dSer, String dWifi, int isCheck,
            int isShowXb) {
        this.id = id;
        this.dName = dName;
        this.dSer = dSer;
        this.dWifi = dWifi;
        this.isCheck = isCheck;
        this.isShowXb = isShowXb;
    }
    @Generated(hash = 1468206029)
    public DeviceDao() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDName() {
        return this.dName;
    }
    public void setDName(String dName) {
        this.dName = dName;
    }
    public String getDSer() {
        return this.dSer;
    }
    public void setDSer(String dSer) {
        this.dSer = dSer;
    }
    public String getDWifi() {
        return this.dWifi;
    }
    public void setDWifi(String dWifi) {
        this.dWifi = dWifi;
    }
    public int getIsCheck() {
        return this.isCheck;
    }
    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }
    public int getIsShowXb() {
        return this.isShowXb;
    }
    public void setIsShowXb(int isShowXb) {
        this.isShowXb = isShowXb;
    }

}
