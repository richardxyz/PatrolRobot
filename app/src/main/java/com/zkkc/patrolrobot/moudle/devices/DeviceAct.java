package com.zkkc.patrolrobot.moudle.devices;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.base.BaseActivity;
import com.zkkc.patrolrobot.base.BasePresenter;
import com.zkkc.patrolrobot.base.BaseView;
import com.zkkc.patrolrobot.moudle.home.activity.HomeAct;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ShiJunRan on 2019/3/26
 * 我的设备页面
 */
public class DeviceAct extends BaseActivity {

    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.rvShowDevice)
    RecyclerView rvShowDevice;
    @BindView(R.id.btnClose)
    Button btnClose;

    private static int REQUEST_CODE = 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullscreen(true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.device_act;
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


    }


    @OnClick({R.id.btnAdd, R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                Intent intent = new Intent(DeviceAct.this, CaptureActivity.class);
                /*ZxingConfig是配置类
                 *可以设置是否显示底部布局，闪光灯，相册，
                 * 是否播放提示音  震动
                 * 设置扫描框颜色等
                 * 也可以不传这个参数
                 * */
                ZxingConfig config = new ZxingConfig();
                config.setPlayBeep(true);//是否播放扫描声音 默认为true
                config.setShake(true);//是否震动  默认为true
                config.setDecodeBarCode(true);//是否扫描条形码 默认为true
                config.setReactColor(R.color.yellow);//设置扫描框四个角的颜色 默认为白色
                config.setFrameLineColor(R.color.transparent);//设置扫描框边框颜色 默认无色
                config.setScanLineColor(R.color.green);//设置扫描线的颜色 默认白色
                config.setFullScreenScan(false);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
                intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    private static final String SERIAL_NUMBER = "SERIAL_NUMBER";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                String[] split = content.split(",");
                String[] s = split[0].split(":");
                String[] s2 = split[1].split(":");
//                if (!s[1].equals("")) {
//                    HomeAct.SERIAL_NUMBER = s[1];
//                }
                SPUtils.getInstance().put(SERIAL_NUMBER, s[1]);
                finish();
//                ToastUtils.showLong(HomeAct.SERIAL_NUMBER);
            }
        }
    }
}
