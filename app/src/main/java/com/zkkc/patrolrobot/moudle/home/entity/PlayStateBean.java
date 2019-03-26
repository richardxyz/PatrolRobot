package com.zkkc.patrolrobot.moudle.home.entity;

/**
 * Created by ShiJunRan on 2019/3/14
 */
public class PlayStateBean {
    private boolean isPlayState;

    public PlayStateBean(boolean isPlayState) {
        this.isPlayState = isPlayState;
    }

    public boolean isPlayState() {
        return isPlayState;
    }

    public void setPlayState(boolean playState) {
        isPlayState = playState;
    }
}
