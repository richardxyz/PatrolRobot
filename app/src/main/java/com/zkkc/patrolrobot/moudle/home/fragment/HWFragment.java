package com.zkkc.patrolrobot.moudle.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.base.BaseFragment;
import com.zkkc.patrolrobot.base.BasePresenter;
import com.zkkc.patrolrobot.base.BaseView;
import com.zkkc.patrolrobot.moudle.home.entity.PlayStateBean;
import com.zkkc.patrolrobot.widget.MagSurfaceView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import cn.com.magnity.sdk.MagDevice;
import cn.com.magnity.sdk.MagService;
import cn.com.magnity.sdk.types.EnumerationInfo;

/**
 * Created by ShiJunRan on 2019/3/14
 */
public class HWFragment extends BaseFragment implements MagDevice.IUsbConnCallback, MagDevice.IFrameCallBack {
    @BindView(R.id.hwVideo)
    MagSurfaceView hwVideo;
    @BindView(R.id.tvNoVideo)
    TextView tvNoVideo;
    private MagDevice mDev;
    private EnumerationInfo[] mDevices;


    ScheduledExecutorService executorService;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void stateEvent(PlayStateBean stateBean) {
        if (stateBean.isPlayState()) {


        } else {


        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.hw_fragment;
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
        /* 构造Device对象 */
        mDev = new MagDevice();
        mDevices = new EnumerationInfo[128];
         executorService = ThreadPoolHelp.Builder
                .schedule(1)
                .scheduleBuilder();
        tvNoVideo.setVisibility(View.VISIBLE);
        updateDeviceList();
    }

    private void updateDeviceList() {
        /* 枚举在线热像仪 */
        MagService.enumCameras();
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
        }
        int num = MagService.getDevices(mContext, 33596, 2, mDevices);
        if (num <= 0) {
            return;
        }
        tvNoVideo.setVisibility(View.GONE);
        restore();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        /* 停止传输图像 */
        /* disconnect camera when app exited */
        if (mDev.isPlaying()) {
            mDev.stop();
            if (hwVideo != null) {
                hwVideo.stopDrawingThread();
            }
        }
        if (mDev.isConnected()) {
            mDev.disconnect();
        }
        mDev = null;
        stop();
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void newFrame(int cameraTemperature, int FFCCounterdown, int camState, int streamType) {
        /* notify drawing image */
        if (hwVideo != null) {
            hwVideo.invalidate_();
        }

    }

    @Override
    public void connectResult(int result) {
        if (result == MagDevice.CONN_SUCC) {
            /* 连接成功 */
            ToastUtils.showShort("connectResult_连接成功");
//            play();
        } else if (result == MagDevice.CONN_FAIL) {
            /* 连接失败 */
            ToastUtils.showShort("connectResult_连接失败");
        } else if (result == MagDevice.CONN_DETACHED) {
            /* 设备拔出*/
            ToastUtils.showShort("connectResult_设备拔出");
        }

    }

    public void restore() {
        /* 连接枚举到的第一个热像仪 */
        EnumerationInfo mSelectedDev = mDevices[0];
        int r = 0;
        if (mSelectedDev.intCameraType == MagService.TYPE_NET) {
            r = mDev.connect(mSelectedDev.intCameraIpOrUsbId);
        }
        if (r == MagDevice.CONN_SUCC) {
            ToastUtils.showShort("连接成功");
            executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    play();
                }
            },50, TimeUnit.MILLISECONDS);
//            play();
        } else if (r == MagDevice.CONN_FAIL) {
            tvNoVideo.setVisibility(View.VISIBLE);
            /* 连接失败 */
            ToastUtils.showShort("连接失败");
            return;
        } else if (r == MagDevice.CONN_PENDING) {
            tvNoVideo.setVisibility(View.GONE);
            /* 需要在回调函数中判断是否连接成功 */
            ToastUtils.showShort("需要在回调函数中判断是否连接成功");
            return;
        }

    }

    public void stop() {
        if (mDev != null) {
            /* 停止传输 */
            mDev.stop();
            /* 断开连接 */
            mDev.disconnect();
        }
        if (hwVideo != null) {
            hwVideo.stopDrawingThread();
        }
    }

    private void play() {
        /* 设置调色板样式 */
        mDev.setColorPalette(MagDevice.ColorPalette.PaletteIronBow);
        if (mDev.play(this, 0, 0, MagDevice.StreamType.StreamTemperature)) {
            if (hwVideo != null) {
                hwVideo.startDrawingThread(mDev);
                tvNoVideo.setVisibility(View.GONE);
            }
        }


    }

}
