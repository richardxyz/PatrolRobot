package com.zkkc.patrolrobot;

public class Constant {

    public final static boolean IS_DEBUG = false;
    //    public final static String LOCAL_HOST = "192.168.1.189";
    public final static String LOCAL_HOST = "172.16.1.159";
    public final static int HOST_PORT = 61613;
    public final static String SUBSCRIBE_TOPIC_STR_TEST = "Test";
    public final static String SUBSCRIBE_TOPIC_STR_TEST2= "Test2";
    public final static String PUBLISH_TOPIC_STR_TEST = "PublishTest";


    public final static String TEST_STR = "{\n" +
            "    \"employees\": [\n" +
            "        {\n" +
            "            \"firstName\": \"Bill\",\n" +
            "            \"lastName\": \"Gates\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"firstName\": \"George\",\n" +
            "            \"lastName\": \"Bush\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"firstName\": \"Thomas\",\n" +
            "            \"lastName\": \"Carter\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

}
