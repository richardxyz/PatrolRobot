package com.zkkc.patrolrobot.moudle.home.entity;

/**
 * Created by ShiJunRan on 2019/3/9
 */
public class HostBasicDetails {


    /**
     * Electric : 50
     * Humidity : 46
     * Temperature : 24
     * Balance : 0
     * GPS : {"Date":"2018 - 05 - 31","Time ":"12: 00: 00 ","Latitude":"121.12345 ","Longitude":"121.12345"}
     * Peripheral : {"Camera":0,"Ir":0,"Radar":0}
     * Arm : {"Left":0,"Right":0,"OCDevice":0}
     * Advance : {"Tower":14,"Direction":0}
     */

    private String SerialNum;
    private int Electric;
    private int Humidity;
    private int Temperature;
    private int Balance;
    private GPSBean GPS;
    private PeripheralBean Peripheral;
    private ArmBean Arm;
    private AdvanceBean Advance;

    public String getSerialNum() {
        return SerialNum;
    }

    public void setSerialNum(String serialNum) {
        SerialNum = serialNum;
    }

    public int getElectric() {
        return Electric;
    }

    public void setElectric(int Electric) {
        this.Electric = Electric;
    }

    public int getHumidity() {
        return Humidity;
    }

    public void setHumidity(int Humidity) {
        this.Humidity = Humidity;
    }

    public int getTemperature() {
        return Temperature;
    }

    public void setTemperature(int Temperature) {
        this.Temperature = Temperature;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int Balance) {
        this.Balance = Balance;
    }

    public GPSBean getGPS() {
        return GPS;
    }

    public void setGPS(GPSBean GPS) {
        this.GPS = GPS;
    }

    public PeripheralBean getPeripheral() {
        return Peripheral;
    }

    public void setPeripheral(PeripheralBean Peripheral) {
        this.Peripheral = Peripheral;
    }

    public ArmBean getArm() {
        return Arm;
    }

    public void setArm(ArmBean Arm) {
        this.Arm = Arm;
    }

    public AdvanceBean getAdvance() {
        return Advance;
    }

    public void setAdvance(AdvanceBean Advance) {
        this.Advance = Advance;
    }

    public static class GPSBean {
        /**
         * Date : 2018 - 05 - 31
         * Time  : 12: 00: 00
         * Latitude : 121.12345
         * Longitude : 121.12345
         */

        private String Date;
        private String Time;
        private String Latitude;
        private String Longitude;

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String Latitude) {
            this.Latitude = Latitude;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String Longitude) {
            this.Longitude = Longitude;
        }
    }

    public static class PeripheralBean {
        /**
         * Camera : 0
         * Ir : 0
         * Radar : 0
         */

        private int Camera;
        private int Ir;
        private int Radar;

        public int getCamera() {
            return Camera;
        }

        public void setCamera(int Camera) {
            this.Camera = Camera;
        }

        public int getIr() {
            return Ir;
        }

        public void setIr(int Ir) {
            this.Ir = Ir;
        }

        public int getRadar() {
            return Radar;
        }

        public void setRadar(int Radar) {
            this.Radar = Radar;
        }
    }

    public static class ArmBean {
        /**
         * Left : 0
         * Right : 0
         * OCDevice : 0
         */

        private int Left;
        private int Right;
        private int OCDevice;

        public int getLeft() {
            return Left;
        }

        public void setLeft(int Left) {
            this.Left = Left;
        }

        public int getRight() {
            return Right;
        }

        public void setRight(int Right) {
            this.Right = Right;
        }

        public int getOCDevice() {
            return OCDevice;
        }

        public void setOCDevice(int OCDevice) {
            this.OCDevice = OCDevice;
        }
    }

    public static class AdvanceBean {
        /**
         * Tower : 14
         * Direction : 0
         */

        private int Tower;
        private int Direction;

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
    }
}
