package com.zkkc.patrolrobot.moudle.home.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zkkc.patrolrobot.Constant;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.base.BaseActivity;
import com.zkkc.patrolrobot.base.BasePresenter;
import com.zkkc.patrolrobot.base.BaseView;
import com.zkkc.patrolrobot.entity.BatteryStateBean;
import com.zkkc.patrolrobot.moudle.home.adapter.XQAdapter;
import com.zkkc.patrolrobot.moudle.home.entity.HostBasicDetails;
import com.zkkc.patrolrobot.moudle.home.entity.XQBean;
import com.zkkc.patrolrobot.receiver.BatteryChangedReceiver;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class MainAct extends BaseActivity {

    /**
     * 配置信息
     */
    @BindView(R.id.lla)
    LinearLayout lla;
    /**
     * 切换红外
     */
    @BindView(R.id.tvQh)
    TextView tvQh;
    @BindView(R.id.llb)
    LinearLayout llb;
    /**
     * 激光雷达
     */
    @BindView(R.id.ivLd)
    ImageView ivLd;
    @BindView(R.id.tvLd)
    TextView tvLd;
    @BindView(R.id.llc)
    LinearLayout llc;
    /**
     * 可见光
     */
    @BindView(R.id.ivKjg)
    ImageView ivKjg;
    @BindView(R.id.tvKjg)
    TextView tvKjg;
    @BindView(R.id.lld)
    LinearLayout lld;
    /**
     * 红外
     */
    @BindView(R.id.ivHw)
    ImageView ivHw;
    @BindView(R.id.tvHw)
    TextView tvHw;
    @BindView(R.id.lle)
    LinearLayout lle;
    /**
     * 平衡状态
     */
    @BindView(R.id.ivPh)
    ImageView ivPh;
    @BindView(R.id.tvPh)
    TextView tvPh;
    @BindView(R.id.llf)
    LinearLayout llf;
    /**
     * 湿度
     */
    @BindView(R.id.llg)
    LinearLayout llg;
    @BindView(R.id.tvSu)
    TextView tvSu;
    /**
     * 温度
     */
    @BindView(R.id.llh)
    LinearLayout llh;
    @BindView(R.id.tvWd)
    TextView tvWd;
    /**
     * GPS
     */
    @BindView(R.id.lli)
    LinearLayout lli;
    /**
     * 连接状态
     */
    @BindView(R.id.ivConn)
    ImageView ivConn;
    @BindView(R.id.tvConn)
    TextView tvConn;
    @BindView(R.id.llj)
    LinearLayout llj;
    /**
     * 终端电量
     */
    @BindView(R.id.ivZD)
    ImageView ivZD;
    @BindView(R.id.tvZDNum)
    TextView tvZDNum;
    @BindView(R.id.ivZDcd)
    ImageView ivZDcd;
    @BindView(R.id.llk)
    LinearLayout llk;
    /**
     * 主机电量
     */
    @BindView(R.id.ivZJ)
    ImageView ivZJ;
    @BindView(R.id.tvZJNum)
    TextView tvZJNum;
    @BindView(R.id.ivZJcd)
    ImageView ivZJcd;
    @BindView(R.id.lll)
    LinearLayout lll;
    /**
     * 关闭
     */
    @BindView(R.id.llm)
    LinearLayout llm;
    /**
     * 控件布局
     */
    @BindView(R.id.llKJREL)
    RelativeLayout llKJREL;


    /**
     * 线路
     */
    @BindView(R.id.llXL)
    LinearLayout llXL;
    @BindView(R.id.ivXL)
    ImageView ivXL;
    //弹出popup
    @BindView(R.id.llXLPopup)
    LinearLayout llXLPopup;
    @BindView(R.id.etXLNum)
    EditText etXLNum;
    @BindView(R.id.etXLQ)
    EditText etXLQ;
    @BindView(R.id.etXLZ)
    EditText etXLZ;
    @BindView(R.id.btnXLOk)
    Button btnXLOk;
    /**
     * 详情
     */
    @BindView(R.id.llXQ)
    LinearLayout llXQ;
    @BindView(R.id.ivXQ)
    ImageView ivXQ;

    @BindView(R.id.llXQPopup)
    LinearLayout llXQPopup;
    @BindView(R.id.rvXQ)
    RecyclerView rvXQ;


    /**
     * 行进控制
     */
    @BindView(R.id.llXJ)
    LinearLayout llXJ;
    @BindView(R.id.ivXJUp)
    ImageView ivXJUp;
    @BindView(R.id.ivXJDown)
    ImageView ivXJDown;
    /**
     * 摄像头控制
     */
    @BindView(R.id.llKJGH)
    LinearLayout llKJGH;
    @BindView(R.id.ivKJGLeft)
    ImageView ivKJGLeft;
    @BindView(R.id.ivKJGRight)
    ImageView ivKJGRight;
    @BindView(R.id.llKJGV)
    LinearLayout llKJGV;
    @BindView(R.id.ivKJGUp)
    ImageView ivKJGUp;
    @BindView(R.id.ivKJGDown)
    ImageView ivKJGDown;
    /**
     * 位置确认
     */
    @BindView(R.id.btnAffirm)
    Button btnAffirm;
    /**
     * 完成
     */
    @BindView(R.id.llWc)
    LinearLayout llWc;
    @BindView(R.id.ivWc)
    ImageView ivWc;
    /**
     * 调焦
     */
    @BindView(R.id.llTJ)
    LinearLayout llTJ;
    @BindView(R.id.ivTJAdd)
    ImageView ivTJAdd;
    @BindView(R.id.ivTJMinus)
    ImageView ivTJMinus;
    @BindView(R.id.tvCXT)
    TextView tvCXT;


    //电池广播接收数据
    private static final String BATTERY_STATUS_CHARGING = "BATTERY_STATUS_CHARGING";
    private static final String BATTERY_STATUS_FULL = "BATTERY_STATUS_FULL";
    private static final String BATTERY_STATUS_NOT_CHARGING = "BATTERY_STATUS_NOT_CHARGING";


    private BatteryChangedReceiver batteryChangedReceiver;
    private String batteryType;//充电状态
    private int powNum;//当前电量


    private MQTT mqtt;
    CallbackConnection connection;
    private short KEEP_ALIVE = 5;
    private long CONNECT_ATTEMPTS_MAX = -1;//重连次数
    private long RECONNECT_DELAY = 3;//在第一次重新连接尝试之前等待时长
    private long RECONNECT_DELAY_MAX = 3;//重新连接尝试之间等待的最长时间
    private boolean connectState = false;//mqtt连接状态

    private List<XQBean> xqBeans = new ArrayList<>();

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void batteryEvent(BatteryStateBean stateBean) {
        batteryType = stateBean.batteryType;
        powNum = stateBean.powStr;
        tvZJNum.setText(powNum + "%");
        if (powNum < 10) {
            ivZJ.setImageResource(R.mipmap.dianliang_a);
        } else if (powNum >= 10 && powNum < 20) {
            ivZJ.setImageResource(R.mipmap.dianliang_b);
        } else if (powNum >= 20 && powNum < 40) {
            ivZJ.setImageResource(R.mipmap.dianliang_c);
        } else if (powNum >= 40 && powNum < 90) {
            ivZJ.setImageResource(R.mipmap.dianliang_d);
        } else if (powNum >= 90) {
            ivZJ.setImageResource(R.mipmap.dianliang_e);
        }
        switch (batteryType) {
            case BATTERY_STATUS_CHARGING://充电中
                ivZJcd.setVisibility(View.VISIBLE);
                break;
            case BATTERY_STATUS_FULL://已充满
                ivZJcd.setVisibility(View.VISIBLE);
                break;
            case BATTERY_STATUS_NOT_CHARGING://停止充电
                ivZJcd.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullscreen(true);
        EventBus.getDefault().register(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_act;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        //动态权限
        permissionsSet();
        //电量广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
        batteryChangedReceiver = new BatteryChangedReceiver();
        registerReceiver(batteryChangedReceiver, filter);
        //创建MQTT和相关设置
        createMqttBean();

        for (int i = 0; i < 10; i++) {
            XQBean xqBean = new XQBean();
            xqBean.setJdNum(i + 1);
            xqBean.setThNum((i + 10) + "");
            xqBean.setJj("2.0");
            xqBean.setLeftJd((130 + i) + "°");
            xqBean.setRightJd((120 + i) + "°");
            xqBeans.add(xqBean);
        }


        XQAdapter adapter = new XQAdapter(R.layout.item_xq, xqBeans);
        rvXQ.setLayoutManager(new LinearLayoutManager(this));
        rvXQ.setAdapter(adapter);
    }

    private boolean XLPopupShow = false;
    private boolean XQPopupShow = false;

    @OnClick({R.id.lla, R.id.llb, R.id.llj, R.id.llm, R.id.llXL, R.id.llXQ, R.id.ivXJUp, R.id.ivXJDown,
            R.id.ivKJGLeft, R.id.ivKJGRight, R.id.ivKJGUp, R.id.ivKJGDown, R.id.btnAffirm, R.id.llWc,
            R.id.ivTJAdd, R.id.ivTJMinus, R.id.tvCXT})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lla:
                break;
            case R.id.llb:
                if (connection != null) {
                    sendPublishData(Constant.TEST_STR2);
                } else {
                    ToastUtils.showShort("请先连接主机");
                }

                break;
            case R.id.llj://连接
                showConnectDialog();
                break;
            case R.id.llm://关闭
                showCloseDialog();
                break;
            case R.id.llXL://线路
                if (XLPopupShow) {
                    llXLPopup.setVisibility(View.GONE);
                    XLPopupShow = false;
                    ivXL.setImageResource(R.mipmap.ic_xl);
                    //点击使其隐藏
                } else {
                    if (XQPopupShow) {
                        llXQPopup.setVisibility(View.GONE);
                        XQPopupShow = false;
                        ivXQ.setImageResource(R.mipmap.ic_xq_b);
                    }
                    llXLPopup.setVisibility(View.VISIBLE);
                    XLPopupShow = true;
                    ivXL.setImageResource(R.mipmap.ic_xl_a);
                    //点击使其显示

                }

                break;
            case R.id.llXQ://详情
                if (XQPopupShow) {
                    llXQPopup.setVisibility(View.GONE);
                    XQPopupShow = false;
                    ivXQ.setImageResource(R.mipmap.ic_xq_b);
                    //点击使其隐藏

                } else {
                    if (XLPopupShow) {
                        llXLPopup.setVisibility(View.GONE);
                        XLPopupShow = false;
                        ivXL.setImageResource(R.mipmap.ic_xl);
                    }
                    llXQPopup.setVisibility(View.VISIBLE);
                    XQPopupShow = true;
                    ivXQ.setImageResource(R.mipmap.ic_xq);
                    //点击使其显示
                }


                break;
            case R.id.ivXJUp://前进

                break;
            case R.id.ivXJDown://后退

                break;
            case R.id.ivKJGLeft://左

                break;
            case R.id.ivKJGRight://右

                break;
            case R.id.ivKJGUp://上

                break;
            case R.id.ivKJGDown://下

                break;
            case R.id.btnAffirm://位置确认

                break;
            case R.id.llWc://完成

                break;
            case R.id.ivTJAdd://加

                break;
            case R.id.ivTJMinus://减

                break;
            case R.id.tvCXT://调焦

                break;
        }
    }

    private void sendPublishData(String jsonStr) {
        connection.publish(Constant.SUBSCRIBE_TOPIC_STR_TEST, jsonStr.getBytes(), QoS.AT_LEAST_ONCE, false, new Callback<Void>() {
            public void onSuccess(Void v) {

                ToastUtils.showShort("操作成功");
                LogUtils.v("publish---onSuccess");
            }

            public void onFailure(Throwable value) {
                ToastUtils.showShort("操作失败，请稍后再试");
                LogUtils.v("publish---onFailure");
            }
        });

    }

    /**
     * 连接Dialog
     */
    Dialog connectDialog;
    ImageView ivClose;
    EditText etUserName;
    EditText etName;
    EditText etPW;
    Button btnConnect;
    private String USER_NAME = "USER_NAME";
    private String NAME = "NAME";
    private String PW = "PW";

    private void showConnectDialog() {
        View diaView = View.inflate(this, R.layout.dialog_connect, null);
        connectDialog = new Dialog(this);
        connectDialog.setContentView(diaView);
        ivClose = diaView.findViewById(R.id.ivClose);
        etUserName = diaView.findViewById(R.id.etUserName);
        etName = diaView.findViewById(R.id.etName);
        etPW = diaView.findViewById(R.id.etPW);
        btnConnect = diaView.findViewById(R.id.btnConnect);
        connectDialog.show();
        etUserName.setText(SPUtils.getInstance().getString(USER_NAME));
        etName.setText(SPUtils.getInstance().getString(NAME));
        updateUi(connectState);
        if (connectState) {
            etPW.setText(SPUtils.getInstance().getString(PW));
        } else {
            etPW.setText("");
        }


        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOk = checkStr();
                if (isOk) {
                    if (connectState) {
                        ToastUtils.showShort("当前已连接");
                    } else {

                        mqtt.setUserName(etName.getText().toString().trim());
                        mqtt.setPassword(etPW.getText().toString().trim());
                        connection = mqtt.callbackConnection();
                        connection.listener(new Listener() {
                            @Override
                            public void onConnected() {
                                connectState = true;
                                updateUi(true);
                                if (connectDialog != null) {
                                    connectDialog.dismiss();
                                }

                                LogUtils.v("listener---onConnected");
                            }

                            @Override
                            public void onDisconnected() {
                                connectState = false;
                                updateUi(false);
                                LogUtils.v("listener---onDisconnected");
                            }

                            @Override
                            public void onPublish(UTF8Buffer topic, Buffer body, Runnable ack) {
//                              ack.run();
                                switch (topic.toString()) {
                                    case Constant.SUBSCRIBE_TOPIC_STR_TEST:
                                        HostBasicDetails hostBasicDetails = GsonUtils.getGson().fromJson(body.ascii().toString(), HostBasicDetails.class);
                                        final int electric = hostBasicDetails.getRTstate().getElectric();//电量
                                        final int humidity = hostBasicDetails.getRTstate().getHumidity();//湿度
                                        final int temperature = hostBasicDetails.getRTstate().getTemperature();//温度
                                        final int balance = hostBasicDetails.getRTstate().getBalance();//平衡状态 正常-0 警告-1 危险-2
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                //TODO
                                                tvZDNum.setText(electric + "%");
                                                tvSu.setText(humidity + "%");
                                                tvWd.setText(temperature + "℃");
                                                if (balance == 0) {
                                                    tvPh.setText("正常");
                                                    tvPh.setTextColor(getResources().getColor(R.color.green));
                                                } else if (balance == 1) {
                                                    tvPh.setText("警告");
                                                    tvPh.setTextColor(getResources().getColor(R.color.colorAccent));
                                                } else if (balance == 2) {
                                                    tvPh.setText("危险");
                                                    tvPh.setTextColor(getResources().getColor(R.color.red));
                                                }


                                            }
                                        });

                                        LogUtils.v("listener---onPublish1---" + body.ascii().toString());
                                        break;
                                    case Constant.SUBSCRIBE_TOPIC_STR_TEST2:
                                        LogUtils.v("listener---onPublish2---" + body.ascii().toString());
                                        break;
                                }

                            }

                            @Override
                            public void onFailure(Throwable value) {
                                LogUtils.v("listener---onFailure");
                            }
                        });
                        connection.connect(new Callback<Void>() {
                            @Override
                            public void onSuccess(Void value) {
                                connectState = true;
                                LogUtils.v("connect---onSuccess");
                                subscribeAllTopic();//订阅Topics
                            }

                            @Override
                            public void onFailure(Throwable value) {
                                connectState = false;
                                updateUi(false);
                                LogUtils.v("connect---onFailure---" + value);
                            }
                        });
                    }
                } else {
                    ToastUtils.showShort("用户名或密码不能为空");
                }


            }
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connectDialog != null) {
                    connectDialog.dismiss();
                }
            }
        });
    }

    private void updateUi(final boolean isConnect) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (btnConnect != null) {
                    if (isConnect) {
                        etUserName.setFocusable(false);
                        etUserName.setFocusableInTouchMode(false);
                        etName.setFocusable(false);
                        etName.setFocusableInTouchMode(false);
                        etPW.setFocusable(false);
                        etPW.setFocusableInTouchMode(false);

                        btnConnect.setClickable(false);
                        btnConnect.setText("已接入主机");
                    } else {
                        etUserName.setFocusable(true);
                        etUserName.setFocusableInTouchMode(true);
                        etName.setFocusable(true);
                        etName.setFocusableInTouchMode(true);
                        etPW.setFocusable(true);
                        etPW.setFocusableInTouchMode(true);
                        etPW.requestFocus();
                        btnConnect.setClickable(true);
                        btnConnect.setText("连接");
                    }

                }
            }
        });
    }

    /**
     * //订阅Topics
     */
    private void subscribeAllTopic() {
        Topic testTopic = new Topic(Constant.SUBSCRIBE_TOPIC_STR_TEST, QoS.AT_LEAST_ONCE);
        Topic testTopic2 = new Topic(Constant.SUBSCRIBE_TOPIC_STR_TEST2, QoS.AT_LEAST_ONCE);
        Topic[] topics = {testTopic, testTopic2};//Topic
        connection.subscribe(topics, new Callback<byte[]>() {
            public void onSuccess(byte[] qoses) {
                // The result of the subcribe request.
                LogUtils.v("subscribe---onSuccess");
                ToastUtils.showShort("连接主机成功");
            }

            public void onFailure(Throwable value) {
                LogUtils.v("subscribe---onFailure");
                //如果订阅失败的话，就主动中断当前连接并且进行重连
                connection.disconnect(new Callback<Void>() {
                    @Override
                    public void onSuccess(Void value) {

                    }

                    @Override
                    public void onFailure(Throwable value) {

                    }
                });
            }
        });
    }

    /**
     * 创建MQTT和相关设置
     */
    private void createMqttBean() {
        try {
            //创建mqtt连接
            mqtt = new MQTT();
            mqtt.setHost(Constant.LOCAL_HOST, Constant.HOST_PORT);
            //相关属性设置
            mqtt.setKeepAlive(KEEP_ALIVE);
//            mqtt.setUserName(etName.getText().toString().trim());
//            mqtt.setPassword(etPW.getText().toString().trim());
            mqtt.setConnectAttemptsMax(CONNECT_ATTEMPTS_MAX);//默认为-1，无限次重连
            mqtt.setReconnectDelay(RECONNECT_DELAY);//在第一次重新连接尝试之前等待时长
            mqtt.setReconnectDelayMax(RECONNECT_DELAY_MAX);//重新连接尝试之间等待的最长时间
//            mqtt.setReconnectBackOffMultiplier();//重新连接尝试之间使用指数退避。设置为1可禁用指数退避。默认为2。


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    /**
     * 存储登录名和密码
     *
     * @return
     */
    private boolean checkStr() {
        String userName = etUserName.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String pW = etPW.getText().toString().trim();
        if (!"".equals(userName) && !"".equals(name) && !"".equals(pW)) {
            SPUtils.getInstance().put(USER_NAME, userName);
            SPUtils.getInstance().put(NAME, name);
            SPUtils.getInstance().put(PW, pW);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryChangedReceiver);
        EventBus.getDefault().unregister(this);
        if (connectDialog != null) {
            connectDialog.dismiss();
            connectDialog = null;
        }
    }

}
