package com.zkkc.patrolrobot.moudle.home.entity;

/**
 * Created by ShiJunRan on 2019/3/28
 * 配置参数
 */
public class PZCSBean {

    /**
     * SerialNum :
     * Module : 10
     * Op : 3
     * Data : {"LineNum":1001,"InitialPoint":12,"EndPoint":45,"Tower":14,"Direction":1,"Pdz":3,"Charge":0,"Type":1,"Speed":14}
     */

    private String SerialNum;
    private int Module;
    private int Op;
    private DataBean Data;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * LineNum : 1001
         * InitialPoint : 12
         * EndPoint : 45
         * Tower : 14
         * Direction : 1
         * Pdz : 3
         * Charge : 0
         * Type : 1
         * Speed : 14
         */

        private int LineNum;
        private int InitialPoint;
        private int EndPoint;
        private int Tower;
        private int Direction;
        private int Pdz;
        private int Charge;
        private int Type;
        private int Speed;

        public int getLineNum() {
            return LineNum;
        }

        public void setLineNum(int LineNum) {
            this.LineNum = LineNum;
        }

        public int getInitialPoint() {
            return InitialPoint;
        }

        public void setInitialPoint(int InitialPoint) {
            this.InitialPoint = InitialPoint;
        }

        public int getEndPoint() {
            return EndPoint;
        }

        public void setEndPoint(int EndPoint) {
            this.EndPoint = EndPoint;
        }

        public int getTower() {
            return Tower;
        }

        public void setTower(int Tower) {
            this.Tower = Tower;
        }

        public int getDirection() {
            return Direction;
        }

        public void setDirection(int Direction) {
            this.Direction = Direction;
        }

        public int getPdz() {
            return Pdz;
        }

        public void setPdz(int Pdz) {
            this.Pdz = Pdz;
        }

        public int getCharge() {
            return Charge;
        }

        public void setCharge(int Charge) {
            this.Charge = Charge;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public int getSpeed() {
            return Speed;
        }

        public void setSpeed(int Speed) {
            this.Speed = Speed;
        }
    }
}
