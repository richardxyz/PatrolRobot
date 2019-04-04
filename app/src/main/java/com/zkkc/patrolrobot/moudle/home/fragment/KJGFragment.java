package com.zkkc.patrolrobot.moudle.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.zkkc.patrolrobot.TrackConstant;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.base.BaseFragment;
import com.zkkc.patrolrobot.base.BasePresenter;
import com.zkkc.patrolrobot.base.BaseView;
import com.zkkc.patrolrobot.moudle.home.entity.PlayStateBean;
import com.zkkc.patrolrobot.widget.EmptyControlVideo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by ShiJunRan on 2019/3/14
 * 可见光实时影像
 */
public class KJGFragment extends BaseFragment {
    @BindView(R.id.detail_player)
    EmptyControlVideo detailPlayer;
    @BindView(R.id.llNoVideo)
    LinearLayout llNoVideo;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void stateEvent(PlayStateBean stateBean) {
        if (stateBean.isPlayState()) {
            llNoVideo.setVisibility(View.GONE);
            RTSPVideoPlay();//流播放

        } else {
            detailPlayer.onVideoPause();
            llNoVideo.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.kjg_fragment;
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
        initRTSPVideo();//初始化播放器

    }

    @Override
    public void onResume() {
        super.onResume();
        detailPlayer.onVideoResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        detailPlayer.onVideoPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GSYVideoManager.releaseAllVideos();
        EventBus.getDefault().unregister(this);
    }

    private void RTSPVideoPlay() {
        detailPlayer.setUp(TrackConstant.KJG_VIDEO_URL, false, "");
        detailPlayer.startPlayLogic();//视频播放

    }

    private void initRTSPVideo() {
        VideoOptionModel videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "rtsp_transport", "tcp");
        List<VideoOptionModel> list = new ArrayList<>();
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "rtsp_flags", "prefer_tcp");
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "allowed_media_types", "video"); //根据媒体类型来配置
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "timeout", 5000);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "buffer_size", 1316);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "infbuf", 1);  // 无限读
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzemaxduration", 100);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probesize", 200);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "flush_packets", 1);
        list.add(videoOptionModel);
        //  关闭播放器缓冲，这个必须关闭，否则会出现播放一段时间后，一直卡主，控制台打印 FFP_MSG_BUFFERING_START
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "packet-buffering", 0);
        list.add(videoOptionModel);


        // 视频帧处理不过来的时候丢弃一些帧达到同步的效果
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max-fps", 0);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "fps", 30);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);
        list.add(videoOptionModel);

        GSYVideoManager.instance().setOptionModelList(list);
        detailPlayer.setVideoAllCallBack(new GSYSampleCallBack() {

            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                LogUtils.eTag("SJR", "onPrepared-->" + url);

            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
                LogUtils.eTag("SJR", "onAutoComplete");
                //停止Service后回调
                llNoVideo.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPlayError(String url, Object... objects) {
                super.onPlayError(url, objects);
                LogUtils.eTag("SJR", "onPlayError");
                //服务端未开启
                llNoVideo.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        EventBus.getDefault().register(this);
        return rootView;
    }
}
