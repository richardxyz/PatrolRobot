package com.zkkc.patrolrobot.moudle.home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.widget.MagSurfaceView;

import cn.com.magnity.sdk.MagDevice;

public class VideoFragment extends Fragment implements MagDevice.IFrameCallBack {
    private MagSurfaceView mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View root = inflater.inflate(R.layout.fragment_video, container, false);
        mView = (MagSurfaceView)root.findViewById(R.id.video);
        return root;
    }


    @Override
    public void newFrame(int cameraTemperature, int FFCCounterdown, int camState, int streamType) {
        /* notify drawing image */
        if (mView != null) {
            mView.invalidate_();
        }
    }

    public void startDrawingThread(MagDevice dev) {
        if (mView != null) {
            mView.startDrawingThread(dev);
        }
    }

    public void stopDrawingThread() {
        if (mView != null) {
            mView.stopDrawingThread();
        }
    }
}
