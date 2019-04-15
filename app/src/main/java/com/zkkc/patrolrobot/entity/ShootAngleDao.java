package com.zkkc.patrolrobot.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ShiJunRan on 2019/4/15
 * 拍摄角度
 */
@Entity
public class ShootAngleDao {
    @Id(autoincrement = true)
    private Long id;
    private String serialNo;//序列号
    private String towerNo;//塔号
    private int towerType;//塔型 1直线塔  2耐张塔
    private int cameraType;//摄像头类型 1可见光 2红外
    private int cameraX;
    private int cameraY;
    private int cameraZ;//焦距
    private String pictureUri;//图片uri
    @Generated(hash = 1146379807)
    public ShootAngleDao(Long id, String serialNo, String towerNo, int towerType,
            int cameraType, int cameraX, int cameraY, int cameraZ,
            String pictureUri) {
        this.id = id;
        this.serialNo = serialNo;
        this.towerNo = towerNo;
        this.towerType = towerType;
        this.cameraType = cameraType;
        this.cameraX = cameraX;
        this.cameraY = cameraY;
        this.cameraZ = cameraZ;
        this.pictureUri = pictureUri;
    }
    @Generated(hash = 1398842969)
    public ShootAngleDao() {
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
    public int getCameraType() {
        return this.cameraType;
    }
    public void setCameraType(int cameraType) {
        this.cameraType = cameraType;
    }
    public int getCameraX() {
        return this.cameraX;
    }
    public void setCameraX(int cameraX) {
        this.cameraX = cameraX;
    }
    public int getCameraY() {
        return this.cameraY;
    }
    public void setCameraY(int cameraY) {
        this.cameraY = cameraY;
    }
    public int getCameraZ() {
        return this.cameraZ;
    }
    public void setCameraZ(int cameraZ) {
        this.cameraZ = cameraZ;
    }
    public String getPictureUri() {
        return this.pictureUri;
    }
    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }
}
