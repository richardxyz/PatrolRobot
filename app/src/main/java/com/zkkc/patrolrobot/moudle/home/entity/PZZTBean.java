package com.zkkc.patrolrobot.moudle.home.entity;

/**
 * Created by ShiJunRan on 2019/3/27
 */
public class PZZTBean {

    /**
     * SerialNum :
     * MsgCode : 200
     * ResultMsg :
     * Module : 8
     * Op : 3
     * Data : {"Tower":12,"Direction":1,"Move":2,"x":12,"y":34,"z":4,"MianState":0,"Substate":0,"State":0,"LineNum":1001,"InitialPoint":12,"EndPoint":45}
     */

    private String SerialNum;
    private int MsgCode;
    private String ResultMsg;
    private int Module;
    private int Op;
    private DataBean Data;

    public String getSerialNum() {
        return SerialNum;
    }

    public void setSerialNum(String SerialNum) {
        this.SerialNum = SerialNum;
    }

    public int getMsgCode() {
        return MsgCode;
    }

    public void setMsgCode(int MsgCode) {
        this.MsgCode = MsgCode;
    }

    public String getResultMsg() {
        return ResultMsg;
    }

    public void setResultMsg(String ResultMsg) {
        this.ResultMsg = ResultMsg;
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
         * Tower : 12
         * Direction : 1
         * Move : 2
         * x : 12
         * y : 34
         * z : 4
         * MianState : 0
         * Substate : 0
         * State : 0
         * LineNum : 1001
         * InitialPoint : 12
         * EndPoint : 45
         */

        private int Tower;
        private int Direction;
        private int Move;
        private int x;
        private int y;
        private int z;
        private int MianState;
        private int Substate;
        private int State;
        private int LineNum;
        private int InitialPoint;
        private int EndPoint;

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

        public int getMove() {
            return Move;
        }

        public void setMove(int Move) {
            this.Move = Move;
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

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

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
    }
}
