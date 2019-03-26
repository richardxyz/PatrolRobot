package com.zkkc.patrolrobot;

public class TrackConstant {
    public final static String BASE_URL = "172.16.1.152:8182";
    //    public final static String KJG_VIDEO_URL = "rtsp://admin:zkkc4200@192.168.1.216:554/h264/ch0/main/av_stream";
//    public final static String KJG_VIDEO_URL = "rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov";
    public final static String KJG_VIDEO_URL = "rtsp://172.16.1.194:8554/stream1";
    //测试环境
    //    public final static String LOCAL_HOST = "192.168.1.251";
    //    public final static int HOST_PORT = 4399;
    //csp
    public final static String LOCAL_HOST = "172.16.1.195";
    public final static int HOST_PORT = 61613;

    /**
     * 定义协议
     */
    //机器实时状态（订阅）
    public final static String DEVICE_RT_STATE = "DeviceRTState";
    //机器模式及配置状态
    public final static String DEVICE_STATE = "DeviceState";

    //机器人控制(发布)
    public final static String DEVICE_OP = "DeviceOP";


}
