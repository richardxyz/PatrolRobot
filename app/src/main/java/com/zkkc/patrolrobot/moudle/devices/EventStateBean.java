package com.zkkc.patrolrobot.moudle.devices;

import com.zkkc.patrolrobot.entity.DeviceDao;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/4/19
 */
public class EventStateBean {
    private String update_state;
    private int update_position;
    private List<DeviceDao> datas;

    public List<DeviceDao> getDatas() {
        return datas;
    }

    public void setDatas(List<DeviceDao> datas) {
        this.datas = datas;
    }

    public String getUpdate_state() {
        return update_state;
    }

    public void setUpdate_state(String update_state) {
        this.update_state = update_state;
    }

    public int getUpdate_position() {
        return update_position;
    }

    public void setUpdate_position(int update_position) {
        this.update_position = update_position;
    }
}
