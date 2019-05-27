package com.zkkc.patrolrobot;

import android.app.Application;
import android.content.Context;

import com.kongzue.dialog.v2.DialogSettings;
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.zkkc.patrolrobot.common.GreenDaoManager;

import static com.kongzue.dialog.v2.DialogSettings.THEME_DARK;
import static com.kongzue.dialog.v2.DialogSettings.THEME_LIGHT;


/**
 * Created by ShiJunRan on 2019/2/14
 * Application类
 */
public class TrackApp extends Application {
    private static Context mContext;//全局上下文对象

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        //greenDao全局配置,只希望有一个数据库操作对象
        GreenDaoManager.getInstance();
        initVideoPlayer();
        initMyDialog();
    }

    private void initMyDialog() {
        // DialogSettings.STYLE_KONGZUE   DialogSettings.STYLE_IOS
        DialogSettings.style = DialogSettings.STYLE_IOS;
        DialogSettings.tip_theme = THEME_LIGHT;          //设置提示框主题为亮色主题
        DialogSettings.dialog_theme = THEME_LIGHT;       //设置对话框主题为暗色主题
        DialogSettings.use_blur = true;                 //设置是否启用模糊


    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * GSYVideo全局设置
     */
    public void initVideoPlayer() {
        //videoplayer
        //EXOPlayer内核，支持格式更多
//        PlayerFactory.setPlayManager(Exo2PlayerManager.class);
        //系统内核模式
//        PlayerFactory.setPlayManager(SystemPlayerManager.class);
        //ijk内核，默认模式
        PlayerFactory.setPlayManager(IjkPlayerManager.class);

        //exo缓存模式，支持m3u8，只支持exo
//        CacheFactory.setCacheManager(ExoPlayerCacheManager.class);
        //代理缓存模式，支持所有模式，不支持m3u8等，默认
//        CacheFactory.setCacheManager(ProxyCacheManager.class);
        //切换渲染模式
        GSYVideoType.setShowType(GSYVideoType.SCREEN_MATCH_FULL);
//    //默认显示比例
//        GSYVideoType.SCREEN_TYPE_DEFAULT = 0;
//    //16:9
//        GSYVideoType.SCREEN_TYPE_16_9 = 1;
//    //4:3
//        GSYVideoType.SCREEN_TYPE_4_3 = 2;
//    //全屏裁减显示，为了显示正常 CoverImageView 建议使用FrameLayout作为父布局
//        GSYVideoType.SCREEN_TYPE_FULL = 4;
//        //全屏拉伸显示，使用这个属性时，surface_container建议使用FrameLayout
//        GSYVideoType.SCREEN_MATCH_FULL = -4;
//切换绘制模式
//        GSYVideoType.setRenderType(GSYVideoType.SUFRACE);
        GSYVideoType.setRenderType(GSYVideoType.GLSURFACE);
//        GSYVideoType.setRenderType(GSYVideoType.TEXTURE);

        GSYVideoType.enableMediaCodecTexture();
    }

}
