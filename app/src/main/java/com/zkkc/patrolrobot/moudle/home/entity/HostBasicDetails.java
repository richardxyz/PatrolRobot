package com.zkkc.patrolrobot.moudle.home.entity;

/**
 * Created by ShiJunRan on 2019/3/9
 */
public class HostBasicDetails {

    /**
     * basic : {"serialnum":"1000-1001-008-SXXX-171229","version":"cspid-ES-01"}
     * conf : {"linenum":1001,"initialpoint":12,"endpoint":45}
     * RTstate : {"electric":50,"humidity":46,"temperature":24,"balance":0,"GPS":{"date":"2018 - 05 - 31","time":"12: 00: 00 ","latitude":"121.12345 ","longitude":"121.12345"},"peripheral":{"camera":0,"ir":0,"radar":0},"arm":{"left":0,"right":0,"OCdevice":0},"advance":{"tower":14,"direction":0}}
     */

    private BasicBean basic;
    private ConfBean conf;
    private RTstateBean RTstate;

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public ConfBean getConf() {
        return conf;
    }

    public void setConf(ConfBean conf) {
        this.conf = conf;
    }

    public RTstateBean getRTstate() {
        return RTstate;
    }

    public void setRTstate(RTstateBean RTstate) {
        this.RTstate = RTstate;
    }

    public static class BasicBean {
        /**
         * serialnum : 1000-1001-008-SXXX-171229
         * version : cspid-ES-01
         */

        private String serialnum;
        private String version;

        public String getSerialnum() {
            return serialnum;
        }

        public void setSerialnum(String serialnum) {
            this.serialnum = serialnum;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static class ConfBean {
        /**
         * linenum : 1001
         * initialpoint : 12
         * endpoint : 45
         */

        private int linenum;
        private int initialpoint;
        private int endpoint;

        public int getLinenum() {
            return linenum;
        }

        public void setLinenum(int linenum) {
            this.linenum = linenum;
        }

        public int getInitialpoint() {
            return initialpoint;
        }

        public void setInitialpoint(int initialpoint) {
            this.initialpoint = initialpoint;
        }

        public int getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(int endpoint) {
            this.endpoint = endpoint;
        }
    }

    public static class RTstateBean {
        /**
         * electric : 50
         * humidity : 46
         * temperature : 24
         * balance : 0
         * GPS : {"date":"2018 - 05 - 31","time":"12: 00: 00 ","latitude":"121.12345 ","longitude":"121.12345"}
         * peripheral : {"camera":0,"ir":0,"radar":0}
         * arm : {"left":0,"right":0,"OCdevice":0}
         * advance : {"tower":14,"direction":0}
         */

        private int electric;
        private int humidity;
        private int temperature;
        private int balance;
        private GPSBean GPS;
        private PeripheralBean peripheral;
        private ArmBean arm;
        private AdvanceBean advance;

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

        public GPSBean getGPS() {
            return GPS;
        }

        public void setGPS(GPSBean GPS) {
            this.GPS = GPS;
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

        public static class GPSBean {
            /**
             * date : 2018 - 05 - 31
             * time : 12: 00: 00
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
             * OCdevice : 0
             */

            private int left;
            private int right;
            private int OCdevice;

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

            public int getOCdevice() {
                return OCdevice;
            }

            public void setOCdevice(int OCdevice) {
                this.OCdevice = OCdevice;
            }
        }

        public static class AdvanceBean {
            /**
             * tower : 14
             * direction : 0
             */

            private int tower;
            private int direction;

            public int getTower() {
                return tower;
            }

            public void setTower(int tower) {
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
}
