package com.zkkc.patrolrobot;

public class Constant {

    public final static boolean IS_DEBUG = false;
    //    public final static String LOCAL_HOST = "192.168.1.189";
    public final static String LOCAL_HOST = "172.16.1.159";
    public final static int HOST_PORT = 61613;
    public final static String SUBSCRIBE_TOPIC_STR_TEST = "Test";
    public final static String SUBSCRIBE_TOPIC_STR_TEST2= "Test2";
    public final static String PUBLISH_TOPIC_STR_TEST = "PublishTest";


    public final static String TEST_STR2 = "{\n" +
            "    \"basic\":{\n" +
            "        \"serialnum\":\"1000-1001-008-SXXX-171229\",\n" +
            "        \"version\":\"cspid-ES-01\"\n" +
            "    },\n" +
            "    \"conf\":{\n" +
            "        \"linenum\":1001,\n" +
            "        \"initial point\":12,\n" +
            "        \"end point\":45\n" +
            "    },\n" +
            "    \"RTstate\":{\n" +
            "        \"electric\":50,\n" +
            "        \"humidity\":46,\n" +
            "        \"temperature\":24,\n" +
            "        \"balance\":0,\n" +
            "        \"GPS\":{\n" +
            "            \"date\": \"2018 - 05 - 31\",\n" +
            "            \"time \": \"12: 00: 00 \",\n" +
            "            \"latitude\": \"121.12345 \",\n" +
            "            \"longitude\": \"121.12345\"\n" +
            "        },\n" +
            "        \"peripheral\":{\n" +
            "            \"camera\":0,\n" +
            "            \"ir\":0,\n" +
            "            \"radar\":0\n" +
            "        },\n" +
            "        \"arm\":{\n" +
            "            \"left\":0,\n" +
            "            \"right\":0,\n" +
            "            \"OC device\":0\n" +
            "        },\n" +
            "        \"advance\":{\n" +
            "            \"tower\":14,\n" +
            "            \"direction\":0\n" +
            "        }\n" +
            "    }\n" +
            "}";
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
