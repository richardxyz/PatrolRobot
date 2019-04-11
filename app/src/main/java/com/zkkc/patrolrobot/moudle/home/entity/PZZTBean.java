package com.zkkc.patrolrobot.moudle.home.entity;

/**
 * Created by ShiJunRan on 2019/3/27
 */
public class PZZTBean {
    /**
     * serialNum :
     * msgCode : 200
     * resultMsg :
     * module : 8
     * op : 0
     * data : {"tower":"12","direction":1,"move":2,"x":12,"y":34,"z":4,"mainState":0,"subState":0,"state":0,"bigTowerDir":1,"lineNum":"1001","initialPoint":"12","endPoint":"45"}
     */

    private String serialNum;
    private int msgCode;
    private String resultMsg;
    private int module;
    private int op;
    private DataBean data;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
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
         * tower : 12
         * direction : 1
         * move : 2
         * x : 12
         * y : 34
         * z : 4
         * mainState : 0
         * subState : 0
         * state : 0
         * bigTowerDir : 1
         * lineNum : 1001
         * initialPoint : 12
         * endPoint : 45
         */

        private String tower;
        private int direction;
        private int move;
        private int x;
        private int y;
        private int z;
        private int mainState;
        private int subState;
        private int state;
        private int bigTowerDir;
        private int installState;

        private String lineNum;
        private String initialPoint;
        private String endPoint;
        private String installResult;//0-失败 1-成功

        public int getInstallState() {
            return installState;
        }

        public void setInstallState(int installState) {
            this.installState = installState;
        }

        public String getInstallResult() {
            return installResult;
        }

        public void setInstallResult(String installResult) {
            this.installResult = installResult;
        }

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

        public int getMove() {
            return move;
        }

        public void setMove(int move) {
            this.move = move;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getZ() {
            return z;
        }

        public void setZ(int z) {
            this.z = z;
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

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
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
