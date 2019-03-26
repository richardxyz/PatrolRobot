package com.zkkc.patrolrobot.moudle.details.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.base.BaseFragment;
import com.zkkc.patrolrobot.base.BasePresenter;
import com.zkkc.patrolrobot.base.BaseView;
import com.zkkc.patrolrobot.moudle.details.adapter.PSDAdapter;
import com.zkkc.patrolrobot.moudle.details.entity.PSDBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ShiJunRan on 2019/3/19
 */
public class PSDFragment extends BaseFragment {
    @BindView(R.id.tvTH)
    TextView tvTH;
    @BindView(R.id.tvTX)
    TextView tvTX;
    @BindView(R.id.tvVZ)
    TextView tvVZ;
    @BindView(R.id.tvFZC)
    TextView tvFZC;
    @BindView(R.id.tvCDZ)
    TextView tvCDZ;
    @BindView(R.id.rvShowPic)
    RecyclerView rvShowPic;

    private List<PSDBean> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.psd_fragment;
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
        for (int i = 0; i < 20; i++) {
            PSDBean bean = new PSDBean();
            mList.add(bean);
        }
        PSDAdapter psdAdapter = new PSDAdapter(R.layout.item_psd_show,mList);
        rvShowPic.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvShowPic.setAdapter(psdAdapter);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
