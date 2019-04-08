package com.zkkc.patrolrobot.moudle.home.entity;

/**
 * Created by ShiJunRan on 2019/3/28
 * 配置参数
 */
public class PZCSBean {

    /**
     * serialNum :
     * module : 8
     * op : 0
     * data : {"tower":"14","direction":1,"pdz":3,"charge":0,"type":1,"shootPointTotal":2,"speed":14,"bigTowerDir":1,"lineNum":"1001","initialPoint":"12","endPoint":"45"}
     */

    private String serialNum;
    private int module;
    private int op;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * tower : 14
         * direction : 1
         * pdz : 3
         * charge : 0
         * type : 1
         * shootPointTotal : 2
         * speed : 14
         * bigTowerDir : 1
         * lineNum : 1001
         * initialPoint : 12
         * endPoint : 45
         */

        private String tower;
        private int direction;
        private int pdz;
        private int charge;
        private int type;
        private int shootPointTotal;
        private int speed;
        private int bigTowerDir;
        private String lineNum;
        private String initialPoint;
        private String endPoint;

        public String getTower() {
            return tower;
        }

        public void setTower(String tower) {
            this.tower = tower;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getPdz() {
            return pdz;
        }

        public void setPdz(int pdz) {
            this.pdz = pdz;
        }

        public int getCharge() {
            return charge;
        }

        public void setCharge(int charge) {
            this.charge = charge;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getShootPointTotal() {
            return shootPointTotal;
        }

        public void setShootPointTotal(int shootPointTotal) {
            this.shootPointTotal = shootPointTotal;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public int getBigTowerDir() {
            return bigTowerDir;
        }

        public void setBigTowerDir(int bigTowerDir) {
            this.bigTowerDir = bigTowerDir;
        }

        public String getLineNum() {
            return lineNum;
        }

        public void setLineNum(String lineNum) {
            this.lineNum = lineNum;
        }

        public String getInitialPoint() {
            return initialPoint;
        }

        public void setInitialPoint(String initialPoint) {
            this.initialPoint = initialPoint;
        }

        public String getEndPoint() {
            return endPoint;
        }

        public void setEndPoint(String endPoint) {
            this.endPoint = endPoint;
        }
    }
}
