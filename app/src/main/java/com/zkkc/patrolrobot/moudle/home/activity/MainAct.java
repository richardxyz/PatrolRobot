package com.zkkc.patrolrobot.moudle.home.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.cazaea.sweetalert.SweetAlertDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cy.dialog.BaseDialog;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.TrackConstant;
import com.zkkc.patrolrobot.base.BaseActivity;
import com.zkkc.patrolrobot.entity.BatteryStateBean;
import com.zkkc.patrolrobot.entity.ShootAngleDao;
import com.zkkc.patrolrobot.moudle.details.activity.DetailsAct;
import com.zkkc.patrolrobot.moudle.devices.DeviceAct;
import com.zkkc.patrolrobot.moudle.home.adapter.XQAdapter;
import com.zkkc.patrolrobot.moudle.home.contract.MainContract;
import com.zkkc.patrolrobot.moudle.home.entity.DeviceStateBean;
import com.zkkc.patrolrobot.moudle.home.entity.HostBasicDetails;
import com.zkkc.patrolrobot.moudle.home.entity.PZCSBean;
import com.zkkc.patrolrobot.moudle.home.entity.PZZTBean;
import com.zkkc.patrolrobot.moudle.home.entity.PlayStateBean;
import com.zkkc.patrolrobot.moudle.home.fragment.KJGFragment;
import com.zkkc.patrolrobot.moudle.home.fragment.VideoFragment;
import com.zkkc.patrolrobot.moudle.home.presenter.MainPresenter;
import com.zkkc.patrolrobot.moudle.home.utils.DeviceOPUtils;
import com.zkkc.patrolrobot.receiver.BatteryChangedReceiver;
import com.zkkc.patrolrobot.widget.VerticalProgressBar;
import com.zkkc.patrolrobot.widget.seekbar.VerticalSeekBar;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.magnity.sdk.MagDevice;
import cn.com.magnity.sdk.MagService;
import cn.com.magnity.sdk.types.EnumerationInfo;

/**
 * Created by ShiJunRan on 2019/4/23
 */
public class MainAct extends BaseActivity<MainContract.View, MainContract.Presenter> implements MainContract.View {

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
    @BindView(R.id.ivQH)
    ImageView ivQH;
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
     * 我的设备
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
    @BindView(R.id.pbDC_a)
    VerticalProgressBar pbDC_a;
    @BindView(R.id.tvZDNum)
    TextView tvZDNum;
    @BindView(R.id.ivZDcd)
    ImageView ivZDcd;
    @BindView(R.id.llk)
    LinearLayout llk;
    /**
     * 主机电量
     */

    @BindView(R.id.pbDC)
    ProgressBar pbDC;
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
    @BindView(R.id.tvXL)
    TextView tvXL;
    //弹出popup
    @BindView(R.id.llXLPopup)
    LinearLayout llXLPopup;
    @BindView(R.id.spinnerDTFX)
    Spinner spinnerDTFX;
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
    @BindView(R.id.tvXQ)
    TextView tvXQ;
    //弹出popup
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
    @BindView(R.id.btnXJSD)
    Button btnXJSD;//档速


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
    @BindView(R.id.btnWc)
    Button btnWc;
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
    @BindView(R.id.vsbZS)
    VerticalSeekBar vsbZS;

    /**
     * 视频播放器
     */
    @BindView(R.id.flVideo)
    FrameLayout flVideo;

    //摄像头转动速度
    @BindView(R.id.tvZS)
    TextView tvZS;
    //机器模式
    @BindView(R.id.tvJQMS)
    TextView tvJQMS;
    /**
     * 隐藏展开按钮
     */
    @BindView(R.id.btnInPZMS)
    Button btnInPZMS;
    @BindView(R.id.rlCD)
    RelativeLayout rlCD;
    @BindView(R.id.btn_a)
    Button btn_a;
    @BindView(R.id.btn_b)
    Button btn_b;
    @BindView(R.id.btn_c)
    Button btn_c;
    @BindView(R.id.btn_d)
    Button btn_d;

    //悬臂
    @BindView(R.id.llXB)
    LinearLayout llXB;
    @BindView(R.id.ivLeftYJ)
    ImageView ivLeftYJ;
    @BindView(R.id.ivLeftFS)
    ImageView ivLeftFS;
    @BindView(R.id.ivRightYJ)
    ImageView ivRightYJ;
    @BindView(R.id.ivRightFS)
    ImageView ivRightFS;
    @BindView(R.id.ivKZ)
    ImageView ivKZ;
    @BindView(R.id.ivSS)
    ImageView ivSS;
    //视频刷新
    @BindView(R.id.ivSXSP)
    ImageView ivSXSP;

    public static final String USER_NAME = "USER_NAME";
    public static final String NAME = "NAME";
    public static final String PW = "PW";
    public static final String DTFX = "DTFX";
    public static final String XL_NUM = "XL_NUM";
    public static final String XL_Q = "XL_Q";
    public static final String XL_Z = "XL_Z";
    public static final String TOWER_NO = "TOWER_NO";
    public static final String TOWER_TOTAL = "TOWER_TOTAL";
    //电池广播接收数据
    private static final String BATTERY_STATUS_CHARGING = "BATTERY_STATUS_CHARGING";
    private static final String BATTERY_STATUS_FULL = "BATTERY_STATUS_FULL";
    private static final String BATTERY_STATUS_NOT_CHARGING = "BATTERY_STATUS_NOT_CHARGING";
    private BatteryChangedReceiver batteryChangedReceiver;
    private String batteryType;//充电状态
    private int powNum;//当前电量


    public static String SERIAL_NUMBER = "";
    public static String XB_STATE = "";

    //mqtt相关
    private MQTT mqtt;
    private short KEEP_ALIVE = 5;
    private long CONNECT_ATTEMPTS_MAX = -1;//重连次数
    private long RECONNECT_DELAY = 4;//在第一次重新连接尝试之前等待时长
    private long RECONNECT_DELAY_MAX = 4;//重新连接尝试之间等待的最长时间
    private boolean connectState = false;//mqtt连接状态
    CallbackConnection connection;
    //详情Popup
    XQAdapter xqAdapter;
    List<ShootAngleDao> lists = new ArrayList<>();
    //fragment
    private FragmentManager manager;
    private KJGFragment kjgFragment;
    private VideoFragment mVideoFragment;
    private boolean isHW = false;//是否为红外
    private boolean isZC = false;//是否为正常速度
    //线路Popup
    private int dTFX = 0;//选择的大塔方向

    private boolean affirmState = true;//当前是否为 位置确认 按钮

    public static final String IS_ZZT = "IS_ZZT";//是否为终止塔

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void batteryEvent(BatteryStateBean stateBean) {
        batteryType = stateBean.batteryType;
        powNum = stateBean.powStr;
        tvZJNum.setText("终端" + powNum + "%");
        pbDC.setProgress(powNum);
        pbDC_a.setCurrMode(2);
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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void xbEvent(String str) {
        if (str.equals("xb_show_state")) {
            SERIAL_NUMBER = SPUtils.getInstance().getString("SERIAL_NUMBER");
            XB_STATE = SPUtils.getInstance().getString("XB_STATE");
            if (XB_STATE.equals("0")) {
                llXB.setVisibility(View.GONE);
            } else {
                llXB.setVisibility(View.VISIBLE);
            }
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
    public MainContract.Presenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public MainContract.View createView() {
        return this;
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
        //设置seekBar的样式和大小相关
        vsbZS.setSelectColor(getResources().getColor(R.color.yellow));
        vsbZS.setThumb(R.mipmap.bg_seekbar);
        vsbZS.setThumbSize(35, 25);
        vsbZS.setProgress(30);
        //转向速度调节
        setOnClicked();
        //详情Popup
        rvXQ.setLayoutManager(new LinearLayoutManager(this));
        xqAdapter = new XQAdapter(R.layout.item_xq, lists);
        rvXQ.setAdapter(xqAdapter);
        xqAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().postSticky(lists.get(position));
                startActivity(new Intent(MainAct.this, PictureShowAct.class));
            }
        });
        //隐藏控制按钮
        widgetHideAndShow(false, false, false, false, false);
//        widgetHideAndShow(true, true, true, true, true);
        //加载fragment
        switchoverCamera(false);
        //创建mqtt连接
        createMqttBean();
        //线路信息录入spinner
        spinnerDTFX.setDropDownVerticalOffset(ConvertUtils.dp2px(30));
        spinnerDTFX.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView positionTv = (TextView) view;
                positionTv.setTextSize(13f);
                positionTv.setTypeface(Typeface.DEFAULT_BOLD);
                positionTv.setTextColor(getResources().getColor(R.color.black));
                positionTv.setGravity(Gravity.CENTER);
                switch (position) {
                    case 0://正向
                        dTFX = 0;
                        break;
                    case 1://反向
                        dTFX = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //控制类按钮监听
        kzTouchListener();
    }

    @OnClick({R.id.lli, R.id.lla, R.id.llb, R.id.llj, R.id.llm, R.id.llXL, R.id.llXQ, R.id.ivXJUp, R.id.ivXJDown,
            R.id.ivKJGLeft, R.id.ivKJGRight, R.id.ivKJGUp, R.id.ivKJGDown, R.id.btnAffirm, R.id.btnWc,
            R.id.tvCXT, R.id.btnXLOk, R.id.btnXJSD, R.id.btnInPZMS, R.id.ivLeftYJ, R.id.ivLeftFS,
            R.id.ivRightYJ, R.id.ivRightFS, R.id.ivKZ, R.id.ivSS, R.id.ivSXSP, R.id.btn_a, R.id.btn_b, R.id.btn_c, R.id.btn_d})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lli://我的设备
                startActivity(new Intent(MainAct.this, DeviceAct.class));
                break;
            case R.id.lla://配置信息
                startActivity(new Intent(MainAct.this, DetailsAct.class));
                break;
            case R.id.llb://可见光切换
                if (isHW) {
                    switchoverCamera(false);
                } else {
                    switchoverCamera(true);
                }
                break;
            case R.id.llj://连接
                showConnectDialog();
                break;
            case R.id.llm://关闭
                showCloseDialog();
                break;
            case R.id.llXL://线路
                if (llXLPopup.getVisibility() == View.GONE) {
                    //查询线路配置信息
                    if (connectState) {
                        updateXLPopupShow(true);
                        showBaseProDialog(MainAct.this, "正在查询线路信息");
                        DeviceOPUtils.queryXLPZXX(MainAct.this, connection, SERIAL_NUMBER);
                    } else {
                        ToastUtils.showShort("当前未登录设备");
                    }

                } else {
                    updateXLPopupShow(false);
                }

                break;
            case R.id.llXQ://详情
                if (llXQPopup.getVisibility() == View.VISIBLE) {
                    updateXQPopupShow(false);
                } else {
                    if (connectState) {
                        updateXQPopupShow(true);
                        //点击使其显示
                        //TODO 数据变化更新
                        getPresenter().queryAngleDetail(SERIAL_NUMBER);
                    } else {
                        ToastUtils.showShort("当前未登录设备");
                    }

                }
                break;
            case R.id.btnAffirm://位置确认
                if (affirmState) {
                    showPSDialog();//拍摄点信息录入
                } else {
                    showQzPsJdDialog();//拍摄点角度录入
                }

                break;
            case R.id.btnWc://完成
                if (isHW) {
                    int anInt = SPUtils.getInstance().getInt(IS_ZZT);
                    if (anInt == 1) {
                        showZZTDialog();//终止塔完成
                    } else {
                        showPzwcDialog();//当前拍摄点角度全部完成
                    }

                } else {
                    showqhHwDialog();//可见光完成后dialog
                }


                break;
            case R.id.tvCXT://调焦

                break;
            case R.id.btnXLOk://确认添加（线路）

                if (connectState) {
                    //添加数据
                    final String xlNum = etXLNum.getText().toString().trim();
                    final String XLQ = etXLQ.getText().toString().trim();
                    final String XLZ = etXLZ.getText().toString().trim();
                    btnXLOk.setBackground(getResources().getDrawable(R.drawable.edit_shape_e));
                    btnXLOk.setFocusableInTouchMode(false);
                    getPresenter().addXL(SERIAL_NUMBER, dTFX + "", xlNum, XLQ, XLZ, connection);
                } else {
                    ToastUtils.showShort("当前未登录设备");
                }


                break;
            case R.id.btnXJSD://行进档速
                if (isZC) {
                    isZC = false;
                    btnXJSD.setText("慢速");
                } else {
                    isZC = true;
                    btnXJSD.setText("正常");
                }
                break;

            case R.id.btnInPZMS://隐藏展开按钮
                if (rlCD.getVisibility() == View.VISIBLE) {
                    rlCD.setVisibility(View.GONE);
                } else {
                    if (connectState) {
                        rlCD.setVisibility(View.VISIBLE);
                        if (deviceStateMainDian == 2) {
                            btn_a.setBackground(getResources().getDrawable(R.drawable.edit_shape_e));
                            btn_a.setFocusableInTouchMode(false);
                            btn_b.setBackground(getResources().getDrawable(R.drawable.edit_shape_c));
                            btn_b.setFocusableInTouchMode(true);
                            btn_c.setBackground(getResources().getDrawable(R.drawable.edit_shape_c));
                            btn_c.setFocusableInTouchMode(true);
                            btn_d.setBackground(getResources().getDrawable(R.drawable.edit_shape_c));
                            btn_d.setFocusableInTouchMode(true);
                        } else {
                            btn_a.setBackground(getResources().getDrawable(R.drawable.edit_shape_c));
                            btn_a.setFocusableInTouchMode(true);
                            btn_b.setBackground(getResources().getDrawable(R.drawable.edit_shape_c));
                            btn_b.setFocusableInTouchMode(true);
                            btn_c.setBackground(getResources().getDrawable(R.drawable.edit_shape_e));
                            btn_c.setFocusableInTouchMode(false);
                            btn_d.setBackground(getResources().getDrawable(R.drawable.edit_shape_e));
                            btn_d.setFocusableInTouchMode(false);
                        }
                    } else {
                        ToastUtils.showShort("当前未登录设备");
                    }

                }


                break;

            case R.id.ivLeftYJ://左悬臂压紧
                ToastUtils.showShort("左悬臂压紧");
                DeviceOPUtils.xbKZ(MainAct.this, connection, SERIAL_NUMBER, 0);
                break;
            case R.id.ivLeftFS://左悬臂放松
                ToastUtils.showShort("左悬臂放松");
                DeviceOPUtils.xbKZ(MainAct.this, connection, SERIAL_NUMBER, 1);
                break;
            case R.id.ivRightYJ://右悬臂压紧
                ToastUtils.showShort("右悬臂压紧");
                DeviceOPUtils.xbKZ(MainAct.this, connection, SERIAL_NUMBER, 2);
                break;
            case R.id.ivRightFS://右悬臂放松
                ToastUtils.showShort("右悬臂放松");
                DeviceOPUtils.xbKZ(MainAct.this, connection, SERIAL_NUMBER, 3);
                break;
            case R.id.ivKZ://悬臂扩展
                ToastUtils.showShort("悬臂扩展");
                DeviceOPUtils.xbKZ(MainAct.this, connection, SERIAL_NUMBER, 4);
                break;
            case R.id.ivSS://悬臂收缩
                ToastUtils.showShort("悬臂收缩");
                DeviceOPUtils.xbKZ(MainAct.this, connection, SERIAL_NUMBER, 5);
                break;
            case R.id.ivSXSP://视频刷新
                if (isHW) {
                    hwStop();
                    initHWCamera();
                } else {
                    EventBus.getDefault().postSticky(new PlayStateBean(connectState));//通知播放实时视频
                }
                break;
            case R.id.btn_a://配置暂停
                if (isPZMS) {
                    showPZMSStopDialog();//配置模式暂停提示Dialog
                } else {
                    ToastUtils.showShort("当前非配置模式，无法暂停");
                }
                break;
            case R.id.btn_b://唤醒
                DeviceOPUtils.hx(MainAct.this, connection, SERIAL_NUMBER);
                break;
            case R.id.btn_c://手动模式
                showBaseProDialog(MainAct.this, "正在切换到手动模式");
                DeviceOPUtils.sdAndZd(MainAct.this, connection, SERIAL_NUMBER, 0);
                break;
            case R.id.btn_d://自动模式
                showBaseProDialog(MainAct.this, "正在切换到自动模式");
                DeviceOPUtils.sdAndZd(MainAct.this, connection, SERIAL_NUMBER, 1);
                break;

        }
    }

    /**
     * 配置模式暂停提示Dialog
     */
    BaseDialog pzmssStopDialog;

    private void showPZMSStopDialog() {
        pzmssStopDialog = new BaseDialog(this);
        pzmssStopDialog.contentView(R.layout.dialog_pzmss_stop)
                .canceledOnTouchOutside(false).show();
        Button btnCancel = pzmssStopDialog.findViewById(R.id.btnCancel);
        Button btnOk = pzmssStopDialog.findViewById(R.id.btnOk);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pzmssStopDialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pzmssStopDialog.dismiss();
                //配置模式暂停
                DeviceOPUtils.pzmsStop(MainAct.this, connection, SERIAL_NUMBER);
                //TODO ---抹除当前未配置完成的拍摄点信息----

            }
        });

    }

    /**
     * 终止塔完成
     */
    BaseDialog zztDialog;
    Button zztOk;

    private void showZZTDialog() {
        zztDialog = new BaseDialog(this);
        zztDialog.contentView(R.layout.dialog_zzt)
                .canceledOnTouchOutside(false).show();
        Button btnCancel = zztDialog.findViewById(R.id.btnCancel);
        zztOk = zztDialog.findViewById(R.id.btnOk);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zztDialog.dismiss();
            }
        });
        zztOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //终止塔完成
                btnPzwcOk.setBackground(getResources().getDrawable(R.drawable.edit_shape_e));
                btnPzwcOk.setFocusableInTouchMode(false);
                // 退出配置模式，点确认（终止塔）
                DeviceOPUtils.pzmsOut(MainAct.this, connection, SERIAL_NUMBER);//退出配置模式

                DeviceOPUtils.inPZOK(MainAct.this, connection, SERIAL_NUMBER);//点配置完成
            }
        });

    }

    /**
     * 当前拍摄点配置角度全部完成Dialog
     */
    BaseDialog pzwcDialog;
    Button btnPzwcOk;

    private void showPzwcDialog() {
        pzwcDialog = new BaseDialog(this);
        pzwcDialog.contentView(R.layout.dialog_pzwc)
                .canceledOnTouchOutside(false).show();
        Button btnCancel = pzwcDialog.findViewById(R.id.btnCancel);
        btnPzwcOk = pzwcDialog.findViewById(R.id.btnOk);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pzwcDialog.dismiss();
            }
        });
        btnPzwcOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 当前拍摄点配置完成，等待机器到达下个拍摄点
                btnPzwcOk.setBackground(getResources().getDrawable(R.drawable.edit_shape_e));
                btnPzwcOk.setFocusableInTouchMode(false);
                DeviceOPUtils.inPZOK(MainAct.this, connection, SERIAL_NUMBER);

            }
        });

    }

    /**
     * 可见光配置完成，请求进行红外配置Dialog
     */
    BaseDialog qhHwDialog;

    private void showqhHwDialog() {
        qhHwDialog = new BaseDialog(this);
        qhHwDialog.contentView(R.layout.dialog_qh_hw)
                .canceledOnTouchOutside(false).show();
        Button btnCancel = qhHwDialog.findViewById(R.id.btnCancel);
        Button btnOk = qhHwDialog.findViewById(R.id.btnOk);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qhHwDialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qhHwDialog.dismiss();
                switchoverCamera(true);

            }
        });
    }

    /**
     * 确认添加拍摄角度Dialog
     */
    BaseDialog qzPsJdDialog;

    private void showQzPsJdDialog() {
        qzPsJdDialog = new BaseDialog(this);
        qzPsJdDialog.contentView(R.layout.dialog_qz_psjd)
                .canceledOnTouchOutside(false).show();
        Button btnCancel = qzPsJdDialog.findViewById(R.id.btnCancel);
        Button btnOk = qzPsJdDialog.findViewById(R.id.btnOk);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qzPsJdDialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qzPsJdDialog.dismiss();
                //角度确认添加
                showBaseProDialog(MainAct.this, "角度确认中，请稍后...");
                DeviceOPUtils.inJDQR(MainAct.this, connection, isHW, SERIAL_NUMBER);
            }
        });

    }

    /**
     * 拍摄点信息录入dialog
     */
    Dialog dialogPsd;
    private int inDirection;
    private int inCharge;
    private int inType;
    private String dqNum;
    private String fzcNum;
    private int isZZT = 0;//是否为终止塔
    Button btnGo;

    private void showPSDialog() {
        View view = View.inflate(this, R.layout.dialog_psd, null);
        dialogPsd = new Dialog(this);
        dialogPsd.setContentView(view);
        btnGo = view.findViewById(R.id.btnGo);
        final EditText etDQNum = view.findViewById(R.id.etDQNum);
        final EditText etFZCNum = view.findViewById(R.id.etFZCNum);
        Spinner spinner_ZZT = view.findViewById(R.id.spinner_ZZT);
        spinner_ZZT.setDropDownVerticalOffset(ConvertUtils.dp2px(30));
        spinner_ZZT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView positionTv = (TextView) view;
                positionTv.setTextColor(getResources().getColor(R.color.black));
                positionTv.setGravity(Gravity.CENTER);
                switch (position) {
                    case 0://否
                        isZZT = 0;
                        break;
                    case 1://是
                        isZZT = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spinner_CDZ = view.findViewById(R.id.spinner_CDZ);
        spinner_CDZ.setDropDownVerticalOffset(ConvertUtils.dp2px(30));
        spinner_CDZ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView positionTv = (TextView) view;
                positionTv.setTextColor(getResources().getColor(R.color.black));
                positionTv.setGravity(Gravity.CENTER);
                switch (position) {
                    case 0://存在
                        inCharge = 0;
                        break;
                    case 1://不存在
                        inCharge = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner spinner_XDWZ = view.findViewById(R.id.spinner_XDWZ);
        spinner_XDWZ.setDropDownVerticalOffset(ConvertUtils.dp2px(30));
        spinner_XDWZ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView positionTv = (TextView) view;
                positionTv.setTextColor(getResources().getColor(R.color.black));
                positionTv.setGravity(Gravity.CENTER);
                switch (position) {
                    case 0://塔前
                        inDirection = 0;
                        break;
                    case 1://塔后
                        inDirection = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner spinner_TX = view.findViewById(R.id.spinner_TX);
        spinner_TX.setDropDownVerticalOffset(ConvertUtils.dp2px(30));
        spinner_TX.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView positionTv = (TextView) view;
                positionTv.setTextColor(getResources().getColor(R.color.black));
                positionTv.setGravity(Gravity.CENTER);
                switch (position) {
                    case 0://直线塔
                        inType = 0;
                        break;
                    case 1://耐张塔
                        inType = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        dialogPsd.setCanceledOnTouchOutside(false);
        dialogPsd.show();
        Window window = dialogPsd.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setContentView(view);
        //设置dialog弹窗宽高
        WindowManager.LayoutParams params = window.getAttributes();
        //dialog宽高
        params.height = ConvertUtils.dp2px(360);
        params.width = ConvertUtils.dp2px(280);
        window.setAttributes(params);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PZCSBean.DataBean bean = new PZCSBean.DataBean();
                dqNum = etDQNum.getText().toString().trim();
                fzcNum = etFZCNum.getText().toString().trim();
                if (dqNum.equals("")) {
                    ToastUtils.showShort("塔号不能为空");
                } else {
                    bean.setTower(dqNum);
                    bean.setDirection(inDirection);
                    if (fzcNum.equals("")) {
                        fzcNum = "0";
                    }
                    bean.setPdz(Integer.parseInt(fzcNum));
                    bean.setCharge(inCharge);
                    bean.setType(inType);
                    btnGo.setBackground(getResources().getDrawable(R.drawable.edit_shape_e));
                    btnGo.setFocusableInTouchMode(false);
                    SPUtils.getInstance().put(IS_ZZT, isZZT);
                    DeviceOPUtils.inPSDXX(MainAct.this, connection, SERIAL_NUMBER, bean);
                }


            }
        });
    }

    /**
     * 连接Dialog
     */
    private BaseDialog connectDialog;
    ImageView ivClose;
    EditText etUserName;
    EditText etName;
    EditText etPW;
    Button btnConnect;

    private void showConnectDialog() {
        connectDialog = new BaseDialog(this);
        connectDialog.contentView(R.layout.dialog_connect)
                .canceledOnTouchOutside(false).show();
        ivClose = connectDialog.findViewById(R.id.ivClose);
        etUserName = connectDialog.findViewById(R.id.etUserName);
        etName = connectDialog.findViewById(R.id.etName);
        etPW = connectDialog.findViewById(R.id.etPW);
        btnConnect = connectDialog.findViewById(R.id.btnConnect);
        etUserName.setText(SPUtils.getInstance().getString(USER_NAME));
        etName.setText(SPUtils.getInstance().getString(NAME));
        etPW.setText(SPUtils.getInstance().getString(PW));
        updateUi(connectState);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String pW = etPW.getText().toString().trim();
                showBaseProDialog(MainAct.this, "登录中，请稍后...");
                getPresenter().MQTTConnHost(userName, name, pW, mqtt);
                connectDialog.dismiss();
            }
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectDialog.dismiss();
            }
        });

    }


    private int sxtZS = 0;//摄像头转速
    private int mrProgress = 30;//摄像头默认转速为30%最高转速

    private void setOnClicked() {
        vsbZS.setOnSlideChangeListener(new VerticalSeekBar.SlideChangeListener() {
            @Override
            public void onStart(VerticalSeekBar slideView, int progress) {
            }

            @Override
            public void onProgress(VerticalSeekBar slideView, int progress) {
                mrProgress = progress;
                tvZS.setText(mrProgress + "");
            }

            @Override
            public void onStop(VerticalSeekBar slideView, int progress) {
            }
        });
    }

    /**
     * 操作控件的显示和隐藏
     *
     * @param xj  前行 后退
     * @param sxt 摄像头方向和位置确认按钮
     * @param tj  调焦按钮
     * @param wc  完成按钮
     * @param xb  悬臂
     */
    private void widgetHideAndShow(boolean xj, boolean sxt, boolean tj, boolean wc, boolean xb) {
        if (xj) {//行进
            llXJ.setVisibility(View.VISIBLE);
            btnXJSD.setVisibility(View.VISIBLE);
        } else {
            llXJ.setVisibility(View.GONE);
            btnXJSD.setVisibility(View.GONE);
        }
        if (sxt) {//摄像头和位置确认
            llKJGH.setVisibility(View.VISIBLE);
            llKJGV.setVisibility(View.VISIBLE);
            btnAffirm.setVisibility(View.VISIBLE);
            vsbZS.setVisibility(View.VISIBLE);
            tvZS.setVisibility(View.VISIBLE);

        } else {
            llKJGH.setVisibility(View.GONE);
            llKJGV.setVisibility(View.GONE);
            btnAffirm.setVisibility(View.GONE);
            vsbZS.setVisibility(View.GONE);
            tvZS.setVisibility(View.GONE);
        }
        if (tj) {//调焦
            llTJ.setVisibility(View.VISIBLE);
        } else {
            llTJ.setVisibility(View.GONE);
        }
        if (wc) {//完成
            btnWc.setVisibility(View.VISIBLE);
        } else {
            btnWc.setVisibility(View.GONE);
        }
        if (xb) {
            SERIAL_NUMBER = SPUtils.getInstance().getString("SERIAL_NUMBER");
            XB_STATE = SPUtils.getInstance().getString("XB_STATE");
            if (XB_STATE.equals("0")) {
                llXB.setVisibility(View.GONE);
            } else {
                llXB.setVisibility(View.VISIBLE);
            }
        } else {
            llXB.setVisibility(View.GONE);
        }

    }

    //切换摄像头
    private void switchoverCamera(boolean isH) {
        manager = getSupportFragmentManager();
        if (isH) {
            isHW = true;
            tvQh.setText("红外");
            ivQH.setImageResource(R.mipmap.tab_qh_hw);
            if (mVideoFragment == null) {
                mVideoFragment = new VideoFragment();
            }
            FragmentUtils.replace(manager, mVideoFragment, R.id.flVideo);
            initHWCamera();
        } else {
            hwStop();
            if (kjgFragment == null) {
                kjgFragment = new KJGFragment();
            }
            FragmentUtils.replace(manager, kjgFragment, R.id.flVideo);
            isHW = false;
            tvQh.setText("可见光");
            ivQH.setImageResource(R.mipmap.tab_qh_kjg);
        }
        manager = null;
    }

    /**
     * 红外摄像头相关
     */
    //non-const
    private MagDevice mDev;
    private EnumerationInfo[] mDevices;
    private EnumerationInfo mSelectedDev;
    Timer timer;

    public void initHWCamera() {
        /* new object */
        mDev = new MagDevice();
        mDevices = new EnumerationInfo[128];
        /* restore list status */
        MagService.enumCameras();
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
        }
        int num = MagService.getDevices(this, 33596, 2, mDevices);
        if (num > 0) {
            mSelectedDev = mDevices[0];
        }
        if (mSelectedDev == null) {
            return;
        }
        if (mSelectedDev.intCameraType == MagService.TYPE_NET) {
            int connResult = mDev.connect(mSelectedDev.intCameraIpOrUsbId);
            if (connResult == MagDevice.CONN_SUCC) {
                ToastUtils.showShort("红外连接成功");
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        hwPlay();
                    }
                }, 400);

            } else if (connResult == MagDevice.CONN_FAIL) {
                ToastUtils.showShort("红外连接失败");

            } else if (connResult == MagDevice.CONN_PENDING) {
                ToastUtils.showShort("红外连接状态未知");
            }
        }
    }

    private void hwPlay() {
        mDev.setColorPalette(MagDevice.ColorPalette.PaletteIronBow);
        if (mDev.play(mVideoFragment, 0, 0, MagDevice.StreamType.StreamTemperature)) {
            mVideoFragment.startDrawingThread(mDev);
        }
    }

    private void hwStop() {
        if (mDev != null) {
            //停止红外
            mDev.stop();
            mDev.disconnect();
            mVideoFragment.stopDrawingThread();
            mDev = null;
        }

    }

    /**
     * 创建MQTT和相关设置
     */
    private void createMqttBean() {
        try {
            //创建mqtt连接
            mqtt = new MQTT();
            mqtt.setHost(TrackConstant.LOCAL_HOST, TrackConstant.HOST_PORT);
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
     * 控制类按钮onTouch
     */
    PZCSBean.DataBean beanZS;

    private void kzTouchListener() {
        ivXJUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: //正常前行
                        if (isZC) {
                            DeviceOPUtils.deviceUp(MainAct.this, connection, SERIAL_NUMBER);
                        } else {
                            DeviceOPUtils.deviceUpLow(MainAct.this, connection, SERIAL_NUMBER);
                        }

                        break;
                    case MotionEvent.ACTION_UP://停止
                        DeviceOPUtils.deviceStop(MainAct.this, connection, SERIAL_NUMBER);
                        break;
                }

                return false;
            }
        });
        ivXJDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://正常后退
                        if (isZC) {
                            DeviceOPUtils.deviceDown(MainAct.this, connection, SERIAL_NUMBER);
                        } else {
                            DeviceOPUtils.deviceDownLow(MainAct.this, connection, SERIAL_NUMBER);
                        }

                        break;
                    case MotionEvent.ACTION_UP://停止
                        DeviceOPUtils.deviceStop(MainAct.this, connection, SERIAL_NUMBER);
                        break;
                }
                return false;
            }
        });
        ivKJGLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://摄像头左转
                        if (isHW) {
                            sxtZS = (int) (mrProgress * 2.55);
                        } else {
                            sxtZS = mrProgress / 10;
                        }
                        beanZS = new PZCSBean.DataBean();
                        beanZS.setSpeed(sxtZS);
                        ToastUtils.showShort(beanZS.getSpeed() + "");
                        DeviceOPUtils.cameraLeft(MainAct.this, connection, isHW, SERIAL_NUMBER, beanZS);
                        break;
                    case MotionEvent.ACTION_UP://停止
                        DeviceOPUtils.cameraStop(MainAct.this, connection, isHW, SERIAL_NUMBER);
                        break;
                }
                return false;
            }
        });
        ivKJGRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://摄像头右转
                        if (isHW) {
                            sxtZS = (int) (mrProgress * 2.55);
                        } else {
                            sxtZS = mrProgress / 10;
                        }
                        beanZS = new PZCSBean.DataBean();
                        beanZS.setSpeed(sxtZS);
                        ToastUtils.showShort(beanZS.getSpeed() + "");
                        DeviceOPUtils.cameraRight(MainAct.this, connection, isHW, SERIAL_NUMBER, beanZS);

                        break;
                    case MotionEvent.ACTION_UP://停止
                        DeviceOPUtils.cameraStop(MainAct.this, connection, isHW, SERIAL_NUMBER);
                        break;
                }
                return false;
            }
        });
        ivKJGUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://摄像头向上转
                        if (isHW) {
                            sxtZS = (int) (mrProgress * 2.55);
                        } else {
                            sxtZS = mrProgress / 10;
                        }
                        beanZS = new PZCSBean.DataBean();
                        beanZS.setSpeed(sxtZS);
                        ToastUtils.showShort(beanZS.getSpeed() + "");
                        DeviceOPUtils.cameraUp(MainAct.this, connection, isHW, SERIAL_NUMBER, beanZS);

                        break;
                    case MotionEvent.ACTION_UP://停止
                        DeviceOPUtils.cameraStop(MainAct.this, connection, isHW, SERIAL_NUMBER);
                        break;
                }
                return false;
            }
        });
        ivKJGDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://摄像头向下转
                        if (isHW) {
                            sxtZS = (int) (mrProgress * 2.55);
                        } else {
                            sxtZS = mrProgress / 10;
                        }
                        beanZS = new PZCSBean.DataBean();
                        beanZS.setSpeed(sxtZS);
                        ToastUtils.showShort(beanZS.getSpeed() + "");
                        DeviceOPUtils.cameraDown(MainAct.this, connection, isHW, SERIAL_NUMBER, beanZS);
                        break;
                    case MotionEvent.ACTION_UP://停止
                        DeviceOPUtils.cameraStop(MainAct.this, connection, isHW, SERIAL_NUMBER);
                        break;
                }
                return false;
            }
        });
        ivTJAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://调焦加
                        PZCSBean.DataBean bean = new PZCSBean.DataBean();
                        bean.setSpeed(10);
                        DeviceOPUtils.cameraFocus(MainAct.this, connection, true, SERIAL_NUMBER, bean);
                        break;
                    case MotionEvent.ACTION_UP://停止
                        DeviceOPUtils.cameraStop(MainAct.this, connection, false, SERIAL_NUMBER);
                        break;
                }

                return false;
            }
        });
        ivTJMinus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://调焦减
                        PZCSBean.DataBean beanTwo = new PZCSBean.DataBean();
                        beanTwo.setSpeed(10);
                        DeviceOPUtils.cameraFocus(MainAct.this, connection, false, SERIAL_NUMBER, beanTwo);
                        break;
                    case MotionEvent.ACTION_UP://停止
                        DeviceOPUtils.cameraStop(MainAct.this, connection, false, SERIAL_NUMBER);
                        break;
                }

                return false;
            }
        });
    }

    /**
     * 机器模式及配置状态  消息处理
     */
    private void switchDeviceState(DeviceStateBean deviceStateBean) {
        String serialNum = deviceStateBean.getSerialNum();
        int module = deviceStateBean.getModule();
        int mainState = deviceStateBean.getMainState();
        int subState = deviceStateBean.getSubState();
        switch (module) {
            case 0://机器状态
                deviceMainState = mainState;
                switch (mainState) {
                    case 0://手动
                        tvJQMS.setText("手动模式");
                        dismisssBaseProDialog();
                        widgetHideAndShow(true, true, true, true, true);
                        break;
                    case 1://自动
                        tvJQMS.setText("自动模式");
                        dismisssBaseProDialog();
                        widgetHideAndShow(false, false, false, false, false);
                        break;
                    case 2://配置
                        tvJQMS.setText("配置模式");
                        ToastUtils.showShort("进入配置模式成功");
                        dismisssBaseProDialog();
                        isPZMS = true;
                        if (deviceStateNow == 0) {//未
                            updateXLPopupShow(true);//显示线路信息录入Popup
                            LogUtils.i("SJR", "意外弹出了线路信息录入Popup");
                        } else if (deviceStateNow == 1) {//中
                            showBaseProDialog(MainAct.this, "设备正在前往拍摄点");
                        } else if (deviceStateNow == 3) {//完成

                        }

                        break;
                    case 3://过障
                        tvJQMS.setText("过障模式");
                        break;
                    case 4://充电
                        tvJQMS.setText("充电模式");
                        break;
                    case 5://低电
                        tvJQMS.setText("低电模式");
                        break;
                    case 6://信息采集
                        tvJQMS.setText("信息采集");
                        break;
                    case 7://待机
                        tvJQMS.setText("待机模式");
                        break;
                    case 8://数据上传
                        tvJQMS.setText("数据上传");
                        break;
                }
                break;
            case 1://配置状态
                switch (mainState) {
                    case 0://未配置
                        break;
                    case 1://配置中
                        switch (subState) {
                            case 0://默认
                                break;
                            case 1://点配置
                                dismisssBaseProDialog();
                                showDDPSDDialog();//到达拍摄点
                                break;
                            case 2://角度配置
                                break;
                            case 3://配置校验
                                break;
                            case 4://配置暂停
                                break;
                        }
                        break;
                    case 2://配置完成
                        SPUtils.getInstance().put(IS_ZZT, 0);
                        widgetHideAndShow(false, false, false, false, false);
                        affirmState = true;
                        btnAffirm.setText("位置确认");
                        switchoverCamera(false);


                        break;
                    case 3://配置修改
                        switch (subState) {
                            case 0://配置覆盖
                                break;
                            case 1://单点修改
                                break;
                            case 2://恢复出厂
                                break;
                        }
                        break;
                }
                break;
        }


    }

    /**
     * 到达拍摄点dialog
     */
    Dialog dialog;
    AlertDialog.Builder builder;

    private void showDDPSDDialog() {
        if (dialog == null) {
            builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_ddpsd, null);
            Button btnGo = view.findViewById(R.id.btnGo);
            dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            Window window = dialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setContentView(view);
            //设置dialog弹窗宽高
            WindowManager.LayoutParams params = window.getAttributes();
            //dialog宽高
            params.height = ConvertUtils.dp2px(200);
            params.width = ConvertUtils.dp2px(300);
            window.setAttributes(params);
            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    dialog = null;
                    widgetHideAndShow(true, true, true, true, true);
                    affirmState = true;
                    btnAffirm.setText("位置确认");
                }
            });
        }

    }


    /**
     * Result消息处理
     *
     * @param pzztBean
     */
    private int deviceMainState = -1;//主状态（0-手动模式 1-自动模式 2-配置 3-过障模式 4-充电模式 5-低电模式 6-信息采集 7-待机模式 8-数据上传）
    private int deviceStateNow = -1;//配置信息（0-未配置 1-配置中 2-配置完成 3-配置修改）
    private int deviceStateDian = -1;//子配置状态（0-行走中 1-点配置 2-可见光角度配置 3-红外角度配置  4-配置暂停  5-线路信息未配置完成）
    private int deviceStateMainDian = -1;//配置状态（0-未配置 1-配置中 2-配置完成 3-配置修改）
    private boolean isPZMS = false;//当前是否是配置模式

    private void switchData(PZZTBean pzztBean) {
        int module = pzztBean.getModule();
        int op = pzztBean.getOp();
        PZZTBean.DataBean data = pzztBean.getData();
        switch (module) {
            case 0://机器查询
                switch (op) {
                    case 0://机器状态
                        dismisssBaseProDialog();
                        //查询线路配置信息
                        showBaseProDialog(MainAct.this, "正在查询线路信息");
                        DeviceOPUtils.queryXLPZXX(MainAct.this, connection, SERIAL_NUMBER);
                        deviceMainState = data.getMainState();
                        switch (data.getMainState()) {
                            case 0://手动
                                tvJQMS.setText("手动模式");
                                break;
                            case 1://自动
                                tvJQMS.setText("自动模式");
                                break;
                            case 2://配置
                                tvJQMS.setText("配置");
                                break;
                            case 3://过障
                                tvJQMS.setText("过障模式");
                                break;
                            case 4://充电
                                tvJQMS.setText("充电模式");
                                break;
                            case 5://低电
                                tvJQMS.setText("低电模式");
                                break;
                            case 6://信息采集
                                tvJQMS.setText("信息采集");
                                break;
                            case 7://待机
                                tvJQMS.setText("待机模式");
                                break;
                            case 8://数据上传
                                tvJQMS.setText("数据上传");
                                break;
                        }


                        break;
                    case 1://线路配置信息
                        if (data != null) {
                            dismisssBaseProDialog();
                            int state = data.getState();
                            int bigTowerDir = data.getBigTowerDir();
                            String lineNum = data.getLineNum();
                            String initialPoint = data.getInitialPoint();
                            String endPoint = data.getEndPoint();
                            int installState = data.getInstallState();
                            deviceStateNow = state;
                            if (installState == 1) {//未安装
                                showTSAZDialog();//提示执行安装Dialog
                            } else {
                                doXLXX(state, bigTowerDir, lineNum, initialPoint, endPoint);//处理配置信息
                            }
                        }

                        break;
                    case 2://配置状态
                        dismisssBaseProDialog();
                        int mainState = data.getMainState();
                        int subState = data.getSubState();
                        int installState = data.getInstallState();
                        deviceStateMainDian = mainState;
                        switch (mainState) {
                            case 0://未配置
                                break;
                            case 1://配置中
                                deviceStateDian = subState;
                                switch (subState) {
                                    case 0://默认(行走中)
                                        //TODO  有疑问 到底是什么状态  未知啊
                                        ToastUtils.showLong("设备正在前往拍摄点,请稍后...");
                                        showBaseProDialog(MainAct.this, "设备正在前往拍摄点");
                                        isPZMS = true;
                                        widgetHideAndShow(false, false, false, false, false);
                                        break;
                                    case 1://点配置
                                        isPZMS = true;
                                        affirmState = true;
                                        btnAffirm.setText("位置确认");
                                        //控制按钮显示和隐藏
                                        widgetHideAndShow(true, true, true, true, true);
                                        break;
                                    case 2://可见光角度配置
                                        isPZMS = true;
                                        affirmState = false;
                                        btnAffirm.setText("角度确认");
//                                        showQzPsJdDialog();
                                        if (isHW) {
                                            switchoverCamera(false);
                                        }
                                        //控制按钮显示和隐藏
                                        widgetHideAndShow(true, true, true, true, true);
                                        break;
                                    case 3://红外角度配置
                                        isPZMS = true;
                                        affirmState = false;
                                        btnAffirm.setText("角度确认");
                                        if (!isHW) {
                                            switchoverCamera(true);
                                        }
                                        //控制按钮显示和隐藏
                                        widgetHideAndShow(true, true, true, true, true);
                                        break;
                                    case 4://配置暂停，上次未配置完的数据被抹除掉了，本地删除上次未配置完成的数据
                                        ToastUtils.showShort("配置暂停");
                                        isPZMS = false;
                                        affirmState = true;
                                        btnAffirm.setText("位置确认");
                                        //控制按钮显示和隐藏
                                        widgetHideAndShow(false, false, false, false, false);
//                                        if (deviceMainState == 5) {//当前的状态无法进入配置模式（条件）
//                                            ToastUtils.showShort("当前设备为低电模式，请等待充电完成");
//                                        } else {
                                        //进入配置模式
                                        showBaseProDialog(MainAct.this, "正在进入配置模式");
                                        DeviceOPUtils.inPZMS(MainAct.this, connection, SERIAL_NUMBER);
//                                        }
                                        break;
                                    case 5://线路信息未配置完成
                                        isPZMS = true;
                                        if (installState == 1) {
                                            ToastUtils.showShort("线路信息未配置完成，请重新录入");
                                            updateXLPopupShow(true);//显示线路信息录入Popup
                                        }
                                        break;
                                }
                                break;
                            case 2://配置完成
                                ToastUtils.showLong("该设备配置已完成");

                                break;
                            case 3://配置修改
                                ToastUtils.showShort("配置修改");
                        }


                        break;
                    case 3://跨塔数量
                        break;
                }
                break;
            case 1://机器人安装
                switch (op) {
                    case 0://一键安装
                        if (data != null) {
                            String installResult = data.getInstallResult();
                            if (installResult != null && !installResult.equals("")) {
                                if (installResult.equals("0")) {
                                    dismisssBaseProDialog();
                                    ToastUtils.showShort("一键安装完成");
                                    //查询线路配置信息
                                    showBaseProDialog(MainAct.this, "正在查询线路信息");
                                    DeviceOPUtils.queryXLPZXX(MainAct.this, connection, SERIAL_NUMBER);

//                                    showLRXJFXDialog();//行进方向录入Dialog
                                } else if (installResult.equals("1")) {
                                    ToastUtils.showShort("一键安装失败,请检查机器设备是否安装正确！");
                                    //一键安装失败！重新进行一键安装
                                }
                            }
                        }


                        break;
                    case 1://一键拆卸
                        break;

                }
                break;
            case 2://机器人运动
                switch (op) {
                    case 0://唤醒
                        break;
                    case 1://慢速前进
                        break;
                    case 2://正常前进
                        break;
                    case 3://慢速后退
                        break;
                    case 4://正常后退
                        break;
                    case 5://停止
                        break;
                    case 6://行进方向（小号到大号）
                    case 7://行进方向（大号到小号）
                        if (lrxjfxDialog != null) {
                            btnConnect.setBackground(getResources().getDrawable(R.drawable.edit_shape_c));
                            btnConnect.setFocusableInTouchMode(true);
                            lrxjfxDialog.dismiss();
                            ToastUtils.showLong("操作成功，请等待设备到达预定拍摄点");
                            showBaseProDialog(MainAct.this, "等待设备到达预定拍摄点");
                        }
                        break;
                }
                break;
            case 3://机器人悬臂
                switch (op) {
                    case 0://左悬臂压紧
                        break;
                    case 1://左悬臂放松
                        break;
                    case 2://右悬臂压紧
                        break;
                    case 3://右悬臂放松
                        break;
                    case 4://悬臂扩展
                        break;
                    case 5://悬臂收缩
                        break;

                }


                break;
            case 4://机器人模式
                switch (op) {
                    case 0://手动模式
                        break;
                    case 1://自动模式
                        break;
                }
                break;
            case 5://配置模式
                switch (op) {
                    case 0://进入配置模式
                        break;
                    case 1://配置模式暂停
                        //TODO
                        ToastUtils.showShort("配置模式已暂停");
//                        if (pzmssStopDialog != null) {
//                            pzmssStopDialog.dismiss();
//                        }
                        break;
                    case 2://配置模式退出
                        break;
                }
                break;
            case 6://红外
                switch (op) {
                    case 0://云台上
                        break;
                    case 1://云台下
                        break;
                    case 2://云台左
                        break;
                    case 3://云台右
                        break;
                    case 4://云台停
                        break;
                    case 5://角度确认
                        if (data != null) {
                            dismisssBaseProDialog();
                            int x = data.getX();
                            int y = data.getY();
                            ToastUtils.showShort("红外摄像头拍摄角度添加成功-" + x + "-" + y);
                            //TODO 保存红外截图...
                            if (mVideoFragment != null && mDev != null) {
                                getPresenter().saveAngleDetail(null, mDev, SERIAL_NUMBER, 2, x, y, -1);
                            }
                        }

                        break;
                    case 6://快照
                        break;

                }
                break;
            case 7://可见光
                switch (op) {
                    case 0://云台上
                        break;
                    case 1://云台下
                        break;
                    case 2://云台左
                        break;
                    case 3://云台右
                        break;
                    case 4://云台停
                        break;
                    case 5://放大
                        break;
                    case 6://缩小
                        break;
                    case 7://角度确认
                        if (data != null) {
                            dismisssBaseProDialog();
                            int x = data.getX();
                            int y = data.getY();
                            int z = data.getZ();
                            ToastUtils.showShort("可见光拍摄角度添加成功->" + x + "-" + y + "-" + z);
                            //保存角度信息和截图...
                            if (kjgFragment != null && kjgFragment.detailPlayer != null) {
                                getPresenter().saveAngleDetail(kjgFragment.detailPlayer, null, SERIAL_NUMBER, 1, x, y, z);
                            }
                        }

                        break;
                    case 8://快照
                        break;
                }
                break;
            case 8://拍摄点
                switch (op) {
                    case 0://配置拍摄点
                        //保存拍摄点录入的信息
                        getPresenter().saveLocationDetails(SERIAL_NUMBER, dqNum, inType, inDirection, inCharge, Integer.parseInt(fzcNum));


                        break;
                    case 1://配置完成
                        if (SPUtils.getInstance().getInt(IS_ZZT) == 1) {
                            ToastUtils.showShort("全部配置完成");
                        } else {
                            if (pzwcDialog != null) {
                                btnPzwcOk.setBackground(getResources().getDrawable(R.drawable.edit_shape_c));
                                btnPzwcOk.setFocusableInTouchMode(true);
                                pzwcDialog.dismiss();
                                widgetHideAndShow(false, false, false, false, false);
                                switchoverCamera(false);
                                ToastUtils.showLong("当前拍摄点配置完成，请等待设备到达下个拍摄点");
                                showBaseProDialog(MainAct.this, "正在前往下一拍摄点，请稍后...");
                            }
                        }


                        break;
                    case 2://校验完成
                        break;
                    case 3://位置确认
                        break;
                }
                break;
            case 9://文件传输
                switch (op) {
                    case 0://文件传输请求
                        break;
                    case 1://文件传输开始
                        break;
                    case 2://文件传输结束
                        break;
                }
                break;
            case 10://配置指令
                switch (op) {
                    case 0://配置信息
                        btnXLOk.setBackground(getResources().getDrawable(R.drawable.edit_shape_c));
                        btnXLOk.setFocusableInTouchMode(true);
                        updateXLPopupShow(false);
                        //行进方向录入
                        showLRXJFXDialog();
                        break;
                    case 1://恢复出厂设置
                        break;

                }
                break;
        }

    }

    /**
     * 行进方向录入Dialog
     */
    BaseDialog lrxjfxDialog;
    int xjfxInfo = 6;

    private void showLRXJFXDialog() {
        lrxjfxDialog = new BaseDialog(this);
        lrxjfxDialog.contentView(R.layout.dialog_lrxjfx)
                .canceledOnTouchOutside(false).show();
        Spinner spinner_xjfx = lrxjfxDialog.findViewById(R.id.spinner_xjfx);
        btnConnect = lrxjfxDialog.findViewById(R.id.btnConnect);
        spinner_xjfx.setDropDownVerticalOffset(ConvertUtils.dp2px(30));
        spinner_xjfx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView positionTv = (TextView) view;
                positionTv.setGravity(Gravity.CENTER);
                positionTv.setTextColor(getResources().getColor(R.color.black));
                switch (position) {
                    case 0://正向
                        xjfxInfo = 6;
                        break;
                    case 1://反向
                        xjfxInfo = 7;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //行进方向设置
                btnConnect.setBackground(getResources().getDrawable(R.drawable.edit_shape_e));
                btnConnect.setFocusableInTouchMode(false);
                DeviceOPUtils.inXJFX(MainAct.this, connection, xjfxInfo, SERIAL_NUMBER);
            }
        });


    }

    /**
     * 更新线路Popup显示状态
     */
    private void updateXLPopupShow(boolean b) {
        if (b) {
            if (llXLPopup.getVisibility() == View.GONE) {
                llXLPopup.setVisibility(View.VISIBLE);
                ivXL.setImageResource(R.mipmap.tab_xl_a);
                tvXL.setTextColor(getResources().getColor(R.color.yellow));
            }
            if (llXQPopup.getVisibility() == View.VISIBLE) {
                llXQPopup.setVisibility(View.GONE);
                ivXQ.setImageResource(R.mipmap.tab_xq);
                tvXQ.setTextColor(getResources().getColor(R.color.white));
            }
        } else {
            if (llXLPopup.getVisibility() == View.VISIBLE) {
                llXLPopup.setVisibility(View.GONE);
                ivXL.setImageResource(R.mipmap.tab_xl);
                tvXL.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }

    /**
     * 更新详情Popup显示状态
     */
    private void updateXQPopupShow(boolean b) {
        if (b) {
            if (llXQPopup.getVisibility() == View.GONE) {
                llXQPopup.setVisibility(View.VISIBLE);
                ivXQ.setImageResource(R.mipmap.tab_xq_a);
                tvXQ.setTextColor(getResources().getColor(R.color.yellow));
            }
            if (llXLPopup.getVisibility() == View.VISIBLE) {
                llXLPopup.setVisibility(View.GONE);
                ivXL.setImageResource(R.mipmap.tab_xl);
                tvXL.setTextColor(getResources().getColor(R.color.white));
            }
        } else {
            if (llXQPopup.getVisibility() == View.VISIBLE) {
                llXQPopup.setVisibility(View.GONE);
                ivXQ.setImageResource(R.mipmap.tab_xq);
                tvXQ.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }

    /**
     * 处理线路信息
     *
     * @param state
     * @param bigTowerDir
     * @param lineNum
     * @param initialPoint
     * @param endPoint
     */
    private void doXLXX(int state, int bigTowerDir, String lineNum, String initialPoint, String endPoint) {
        switch (state) {
            case 0://未配置
                etXLNum.setText("");
                etXLQ.setText("");
                etXLZ.setText("");
                etXLNum.setFocusableInTouchMode(true);
                etXLQ.setFocusableInTouchMode(true);
                etXLZ.setFocusableInTouchMode(true);
                btnXLOk.setVisibility(View.VISIBLE);
                showWPZDialog();//提示未配置
                break;
            case 1://配置中
                //查询当前配置状态
                showBaseProDialog(MainAct.this, "正在查询当前配置状态");
                DeviceOPUtils.queryPZZT(MainAct.this, connection, SERIAL_NUMBER);
                if (bigTowerDir == 0) {
                    spinnerDTFX.setSelection(0);
                } else {
                    spinnerDTFX.setSelection(1);
                }
                etXLNum.setText(lineNum);
                etXLQ.setText(initialPoint);
                etXLZ.setText(endPoint);
                etXLNum.setFocusableInTouchMode(true);
                etXLQ.setFocusableInTouchMode(true);
                etXLZ.setFocusableInTouchMode(true);
                btnXLOk.setVisibility(View.VISIBLE);

                break;
            case 2://配置完成
                if (bigTowerDir == 0) {
                    spinnerDTFX.setSelection(0);
                } else {
                    spinnerDTFX.setSelection(1);
                }
                etXLNum.setText(lineNum);
                etXLQ.setText(initialPoint);
                etXLZ.setText(endPoint);
                etXLNum.setFocusableInTouchMode(false);
                etXLQ.setFocusableInTouchMode(false);
                etXLZ.setFocusableInTouchMode(false);
                btnXLOk.setVisibility(View.INVISIBLE);

                //TODO  asdasdasd  可进行自动模式 ，手动模式切换，唤醒。配置暂停置为灰色
                btn_a.setBackground(getResources().getDrawable(R.drawable.edit_shape_e));
                btn_a.setFocusableInTouchMode(false);
                btn_b.setBackground(getResources().getDrawable(R.drawable.edit_shape_c));
                btn_b.setFocusableInTouchMode(true);
                btn_c.setBackground(getResources().getDrawable(R.drawable.edit_shape_c));
                btn_c.setFocusableInTouchMode(true);
                btn_d.setBackground(getResources().getDrawable(R.drawable.edit_shape_c));
                btn_d.setFocusableInTouchMode(true);
                break;
            case 3://配置修改

                break;
        }

    }

    /**
     * 提示未配置弹窗
     */
    BaseDialog wpzDialog;

    private void showWPZDialog() {
        wpzDialog = new BaseDialog(this);
        wpzDialog.contentView(R.layout.dialog_wpz)
                .canceledOnTouchOutside(false).show();
        Button btnCancel = wpzDialog.findViewById(R.id.btnCancel);
        Button btnOk = wpzDialog.findViewById(R.id.btnOk);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wpzDialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wpzDialog.dismiss();
                //进入配置模式
                DeviceOPUtils.inPZMS(MainAct.this, connection, SERIAL_NUMBER);
                showBaseProDialog(MainAct.this, "正在进入配置模式");
            }
        });


    }

    /**
     * 提示安装Dialog
     */
    BaseDialog tsazDialog;

    private void showTSAZDialog() {
        tsazDialog = new BaseDialog(this);
        tsazDialog.contentView(R.layout.dialog_tsaz)
                .canceledOnTouchOutside(false).show();
        Button btnCancel = tsazDialog.findViewById(R.id.btnCancel);
        Button btnOk = tsazDialog.findViewById(R.id.btnOk);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tsazDialog.dismiss();
                //手动安装
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tsazDialog.dismiss();
                showBaseProDialog(MainAct.this, "正在执行一键安装");
                //一键安装
                DeviceOPUtils.inYJAZ(MainAct.this, connection, SERIAL_NUMBER);

            }
        });
    }

    @Override
    public void mFailure(String strErr) {
        ToastUtils.showShort(strErr);
        connectState = false;
    }

    @Override
    public void mConnected(CallbackConnection connection, String str) {
        this.connection = connection;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dismisssBaseProDialog();
            }
        });
        ToastUtils.showShort(str);
        connectState = true;
        Topic deviceRTState = new Topic(TrackConstant.DEVICE_RT_STATE, QoS.AT_LEAST_ONCE);
        Topic deviceState = new Topic(TrackConstant.DEVICE_STATE, QoS.AT_LEAST_ONCE);
        Topic deviceResult = new Topic(TrackConstant.DEVICE_RESULT, QoS.AT_LEAST_ONCE);
        Topic[] topics = {deviceRTState, deviceState, deviceResult};//Topics
        getPresenter().subscribeAllTopic(topics);
    }

    @Override
    public void mDisconnected(String str) {
        ToastUtils.showShort(str);
        connectState = false;
        updateUi(connectState);
    }

    @Override
    public void mPublish(final UTF8Buffer topic, final Buffer body) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (topic.toString()) {
                    case TrackConstant.DEVICE_RT_STATE://机器实时状态
                        HostBasicDetails hostBasicDetails = GsonUtils.getGson().fromJson(body.ascii().toString(), HostBasicDetails.class);
                        String serialNum = hostBasicDetails.getSerialNum();//机器序列号
                        int balance = hostBasicDetails.getBalance();//平衡状态 正常-0 警告-1 危险-2
                        int camera = hostBasicDetails.getPeripheral().getCamera();//可见光摄像头（关闭-0 开启-1）
                        int ir = hostBasicDetails.getPeripheral().getIr();//红外（关闭-0 开启-1）
                        int radar = hostBasicDetails.getPeripheral().getRadar();//雷达（关闭-0 开启-1）

                        tvZDNum.setText("设备" + hostBasicDetails.getElectric() + "%");//电量
                        pbDC_a.setProgress(hostBasicDetails.getElectric());
                        tvSu.setText(hostBasicDetails.getHumidity() + "%");//湿度
                        tvWd.setText(hostBasicDetails.getTemperature() + "℃");//温度
                        if (balance == 0) {
                            tvPh.setText("正常");
                            tvPh.setTextColor(getResources().getColor(R.color.white));
                            ivPh.setImageResource(R.mipmap.tab_ph_zc);
                        } else if (balance == 1) {
                            tvPh.setText("警告");
                            tvPh.setTextColor(getResources().getColor(R.color.yellow));
                            ivPh.setImageResource(R.mipmap.tab_ph_jg);
                        } else if (balance == 2) {
                            tvPh.setText("危险");
                            tvPh.setTextColor(getResources().getColor(R.color.red));
                            ivPh.setImageResource(R.mipmap.tab_ph_wx);
                        }
                        if (camera == 1) {
                            tvKjg.setText("开启");
                            ivKjg.setImageResource(R.mipmap.tab_kjg_on);
                        } else {
                            tvKjg.setText("关闭");
                            ivKjg.setImageResource(R.mipmap.tab_kjg_off);
                        }
                        if (ir == 1) {
                            tvHw.setText("开启");
                            ivHw.setImageResource(R.mipmap.tab_hw_on);
                        } else {
                            tvHw.setText("关闭");
                            ivHw.setImageResource(R.mipmap.tab_hw_off);
                        }
                        if (radar == 1) {
                            tvLd.setText("开启");
                            ivLd.setImageResource(R.mipmap.tab_jg_on);
                        } else {
                            tvLd.setText("关闭");
                            ivLd.setImageResource(R.mipmap.tab_jg_off);
                        }

                        break;
                    case TrackConstant.DEVICE_STATE://机器模式及配置状态
                        DeviceStateBean deviceStateBean = GsonUtils.getGson().fromJson(body.ascii().toString(), DeviceStateBean.class);
                        LogUtils.i("SJRSJR_2", body.ascii().toString());
                        switchDeviceState(deviceStateBean);
                        break;
                    case TrackConstant.DEVICE_RESULT://Result消息处理
                        PZZTBean pzztBean = GsonUtils.getGson().fromJson(body.ascii().toString(), PZZTBean.class);
                        int msgCode = pzztBean.getMsgCode();
                        if (msgCode == 200) {
                            LogUtils.i("SJRSJR", body.ascii().toString());
                            switchData(pzztBean);
                        } else {
                            ToastUtils.showShort(pzztBean.getResultMsg());
                        }

                        break;
                }
            }
        });
    }


    @Override
    public void subscribeOk() {
        EventBus.getDefault().postSticky(new PlayStateBean(connectState));//通知播放实时视频
        updateUi(true);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //查询当前机器状态
                DeviceOPUtils.queryJQZT(MainAct.this, connection, SERIAL_NUMBER);
                showBaseProDialog(MainAct.this, "正在查询当前机器状态");
            }
        });
    }

    @Override
    public void subscribeErr(String strErr) {
        ToastUtils.showShort(strErr);
    }

    @Override
    public void sendPublishErr(String strErr) {
        ToastUtils.showShort(strErr);
    }

    @Override
    public void addXLSuccess() {

    }

    @Override
    public void saveLDSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialogPsd != null) {
                    btnGo.setBackground(getResources().getDrawable(R.drawable.edit_shape_c));
                    btnGo.setFocusableInTouchMode(true);
                    dialogPsd.dismiss();
                    dialogPsd = null;
                    affirmState = false;
                    btnAffirm.setText("角度确认");
                    ToastUtils.showShort("拍摄点信息录入成功");
                }
            }
        });

    }

    @Override
    public void saveLDFailure(String err) {
        ToastUtils.showShort(err);
    }

    @Override
    public void saveAngleSuccess() {
        ToastUtils.showShort("拍摄角度信息和图片本地保存成功");
    }

    @Override
    public void saveAngleFailure(String err) {
        ToastUtils.showShort("拍摄角度信息和图片本地保存失败");
    }

    @Override
    public void queryAngleSuccess(List<ShootAngleDao> list) {
        lists = list;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                xqAdapter.setNewData(lists);
            }
        });
    }

    @Override
    public void queryAngleFailure(String err) {
        ToastUtils.showShort(err);
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
                        btnConnect.setText("已登录主机");
                        btnConnect.setVisibility(View.INVISIBLE);
                        ivConn.setImageResource(R.mipmap.tab_lj_on);
                        tvConn.setText("已连接");
                        tvConn.setTextColor(getResources().getColor(R.color.yellow));
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
                        btnConnect.setVisibility(View.VISIBLE);
                        ivConn.setImageResource(R.mipmap.tab_lj_off);
                        tvConn.setText("未连接");
                        tvConn.setTextColor(getResources().getColor(R.color.red));
                    }

                }
            }
        });
    }
}
