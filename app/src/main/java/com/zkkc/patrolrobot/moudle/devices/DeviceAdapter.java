package com.zkkc.patrolrobot.moudle.devices;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suke.widget.SwitchButton;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.entity.DeviceDao;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/4/18
 */
public class DeviceAdapter extends BaseQuickAdapter<DeviceDao, BaseViewHolder> {
    private EventStateBean bean;

    public DeviceAdapter(int layoutResId, @Nullable List<DeviceDao> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final DeviceDao item) {
        helper.setText(R.id.tvName, item.getDName())
                .setText(R.id.tvSerNo, item.getDSer())
                .setText(R.id.tvWIFIName, item.getDWifi())
                .addOnClickListener(R.id.btnLJ);
        final Button btnLJ = helper.getView(R.id.btnLJ);
        if (item.getIsCheck() == 1) {
            btnLJ.setText("已连接");
            btnLJ.setTextColor(mContext.getResources().getColor(R.color.black));
        } else {
            btnLJ.setText("未连接");
            btnLJ.setTextColor(mContext.getResources().getColor(R.color.white));
        }
        btnLJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getIsCheck() == 0) {
//                    item.setIsCheck(1);
//                    btnLJ.setText("已连接");
//                    btnLJ.setTextColor(mContext.getResources().getColor(R.color.black));
                    List<DeviceDao> data = getData();
                    for (int i = 0; i < data.size(); i++) {
                        if (i == helper.getAdapterPosition()) {
                            data.get(i).setIsCheck(1);
                            btnLJ.setText("已连接");
                            btnLJ.setTextColor(mContext.getResources().getColor(R.color.black));
                        } else {
                            data.get(i).setIsCheck(0);
                        }
                    }

                    bean = new EventStateBean();
                    bean.setUpdate_state("connect_state");
                    bean.setUpdate_position(helper.getAdapterPosition());
                    bean.setDatas(getData());
                    EventBus.getDefault().post(bean);
                }

            }
        });
        SwitchButton switchButton = helper.getView(R.id.switchButton);
        if (item.getIsShowXb() == 1) {
            switchButton.setChecked(true);
        } else {
            switchButton.setChecked(false);
        }
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    item.setIsShowXb(1);
                } else {
                    item.setIsShowXb(0);
                }
                bean = new EventStateBean();
                bean.setUpdate_state("update_state");
                bean.setUpdate_position(helper.getAdapterPosition());
                bean.setDatas(getData());
                EventBus.getDefault().post(bean);
            }
        });
    }
}
