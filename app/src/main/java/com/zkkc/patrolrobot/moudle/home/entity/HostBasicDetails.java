package com.zkkc.patrolrobot.moudle.home.entity;

/**
 * Created by ShiJunRan on 2019/3/9
 */
public class HostBasicDetails {

    /**
     * serialNum : 1000-1001-008-SXXX-171229
     * electric : 50
     * humidity : 46
     * temperature : 24
     * balance : 0
     * gps : {"date":"2018 - 05 - 31","time ":"12: 00: 00 ","latitude":"121.12345 ","longitude":"121.12345"}
     * peripheral : {"camera":0,"ir":0,"radar":0}
     * arm : {"left":0,"right":0,"ocDevice":0}
     * advance : {"tower":"14","direction":0}
     */

    private String serialNum;
    private int electric;
    private int humidity;
    private int temperature;
    private int balance;
    private GpsBean gps;
    private PeripheralBean peripheral;
    private ArmBean arm;
    private AdvanceBean advance;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public int getElectric() {
        return electric;
    }

    public void setElectric(int electric) {
        this.electric = electric;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public GpsBean getGps() {
        return gps;
    }

    public void setGps(GpsBean gps) {
        this.gps = gps;
    }

    public PeripheralBean getPeripheral() {
        return peripheral;
    }

    public void setPeripheral(PeripheralBean peripheral) {
        this.peripheral = peripheral;
    }

    public ArmBean getArm() {
        return arm;
    }

    public void setArm(ArmBean arm) {
        this.arm = arm;
    }

    public AdvanceBean getAdvance() {
        return advance;
    }

    public void setAdvance(AdvanceBean advance) {
        this.advance = advance;
    }

    public static class GpsBean {
        /**
         * date : 2018 - 05 - 31
         * time  : 12: 00: 00
         * latitude : 121.12345
         * longitude : 121.12345
         */

        private String date;
        private String time;
        private String latitude;
        private String longitude;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }

    public static class PeripheralBean {
        /**
         * camera : 0
         * ir : 0
         * radar : 0
         */

        private int camera;
        private int ir;
        private int radar;

        public int getCamera() {
            return camera;
        }

        public void setCamera(int camera) {
            this.camera = camera;
        }

        public int getIr() {
            return ir;
        }

        public void setIr(int ir) {
            this.ir = ir;
        }

        public int getRadar() {
            return radar;
        }

        public void setRadar(int radar) {
            this.radar = radar;
        }
    }

    public static class ArmBean {
        /**
         * left : 0
         * right : 0
         * ocDevice : 0
         */

        private int left;
        private int right;
        private int ocDevice;

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public int getOcDevice() {
            return ocDevice;
        }

        public void setOcDevice(int ocDevice) {
            this.ocDevice = ocDevice;
        }
    }

    public static class AdvanceBean {
        /**
         * tower : 14
         * direction : 0
         */

        private String tower;
        private int direction;

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
    }
}
