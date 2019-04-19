package com.zkkc.patrolrobot.moudle.devices;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.cazaea.sweetalert.SweetAlertDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.suke.widget.SwitchButton;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;
import com.zkkc.green.gen.DeviceDaoDao;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.base.BaseActivity;
import com.zkkc.patrolrobot.base.BasePresenter;
import com.zkkc.patrolrobot.base.BaseView;
import com.zkkc.patrolrobot.common.GreenDaoManager;
import com.zkkc.patrolrobot.entity.DeviceDao;
import com.zkkc.patrolrobot.moudle.home.activity.HomeAct;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

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

    private ExecutorService threadPool;
    private static int REQUEST_CODE = 1024;
    private List<DeviceDao> mList = new ArrayList<>();
    DeviceAdapter adapter;
    private static final String SERIAL_NUMBER = "SERIAL_NUMBER";
    private static final String XB_STATE = "XB_STATE";

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void mEvent(EventStateBean bean) {
        mList= bean.getDatas();
        int position = bean.getUpdate_position();
        getDeviceDao().updateInTx(mList);
        if (bean.getUpdate_state().equals("connect_state")) {
            SPUtils.getInstance().put(SERIAL_NUMBER, mList.get(position).getDSer());
            SPUtils.getInstance().put(XB_STATE, mList.get(position).getIsShowXb() + "");
            adapter.notifyDataSetChanged();
            EventBus.getDefault().postSticky("xb_show_state");
        } else {
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).getIsCheck() == 1) {
                    if (i == position) {
                        SPUtils.getInstance().put(XB_STATE, mList.get(position).getIsShowXb() + "");
                        EventBus.getDefault().postSticky("xb_show_state");
                    }

                }
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
        threadPool = ThreadPoolHelp.Builder
                .cached()
                .builder();
        adapter = new DeviceAdapter(R.layout.item_device, mList);
        rvShowDevice.setLayoutManager(new LinearLayoutManager(this));
        rvShowDevice.setAdapter(adapter);
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showDelDialog(mList.get(position), position);
                return false;
            }
        });
    }

    private void showDelDialog(final DeviceDao dd, final int position) {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("删除当前设备信息？")
                .setContentText("设备本地存储的数据将丢失！")
                .setConfirmText("确定")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        delDevice(sDialog, dd, position);
                    }
                }).show();
    }

    private void delDevice(SweetAlertDialog sDialog, DeviceDao dd, int position) {
        getDeviceDao().delete(dd);
        mList.remove(position);
        sDialog.setTitleText("删除成功!")
                .setContentText("设备的本地数据已删除!")
                .setConfirmText("确定")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        adapter.notifyDataSetChanged();
                        sweetAlertDialog.cancel();
                    }
                })
                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
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

    @Override
    protected void onResume() {
        super.onResume();
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                queryDeviceDaos();
            }
        });

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    DeviceDao deviceDao;
    String[] s;
    String[] s2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                String[] split = content.split(",");
                s = split[0].split(":");
                s2 = split[1].split(":");
                showDialog();

            }
        }
    }

    private DeviceDaoDao getDeviceDao() {
        return GreenDaoManager.getInstance().getSession().getDeviceDaoDao();
    }

    private void queryDeviceDaos() {
        List<DeviceDao> deviceDaos = getDeviceDao().loadAll();
        LogUtils.i("DEVICEDAOS", deviceDaos.size() + "");
        if (deviceDaos != null) {
            if (deviceDaos.size() > 0) {
                mList = deviceDaos;
                Collections.reverse(mList);//对histories 集合中的数据进行倒叙排序
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setNewData(mList);
                    }
                });

            }
        }

    }

    Dialog deviceDialog;

    public void showDialog() {
        View dialogView = View.inflate(this, R.layout.dialog_device, null);
        deviceDialog = new Dialog(this);
        deviceDialog.setContentView(dialogView);
        Window window = deviceDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnOk = dialogView.findViewById(R.id.btnOk);
        final EditText mName = dialogView.findViewById(R.id.etName);
        final TextView tvSer = dialogView.findViewById(R.id.tvSer);
        final TextView tvWIFIName = dialogView.findViewById(R.id.tvWIFIName);
        if (!s[1].equals("")) {
            tvSer.setText(s[1]);
        }
        if (!s2[1].equals("")) {
            tvWIFIName.setText(s2[1]);
        }
        deviceDialog.show();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        deviceDao = new DeviceDao();
                        deviceDao.setDName(mName.getText().toString().trim());
                        deviceDao.setDSer(tvSer.getText().toString().trim());
                        deviceDao.setDWifi(tvWIFIName.getText().toString().trim());
                        deviceDao.setIsCheck(0);
                        deviceDao.setIsShowXb(0);
                        getDeviceDao().insert(deviceDao);
                        deviceDialog.dismiss();
                        queryDeviceDaos();

                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deviceDialog.dismiss();
            }
        });
    }


}
