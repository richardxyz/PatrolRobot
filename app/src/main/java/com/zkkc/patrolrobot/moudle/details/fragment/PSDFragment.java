package com.zkkc.patrolrobot.moudle.details.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.zkkc.green.gen.ShootAngleDaoDao;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.base.BaseFragment;
import com.zkkc.patrolrobot.base.BasePresenter;
import com.zkkc.patrolrobot.base.BaseView;
import com.zkkc.patrolrobot.common.GreenDaoManager;
import com.zkkc.patrolrobot.entity.LocationDetailsDao;
import com.zkkc.patrolrobot.entity.ShootAngleDao;
import com.zkkc.patrolrobot.moudle.details.adapter.PSDAdapter;
import com.zkkc.patrolrobot.moudle.home.activity.PictureShowAct;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

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
    private ExecutorService threadPool;
    private List<ShootAngleDao> mList = new ArrayList<>();
    PSDAdapter psdAdapter;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void xqEvent(final LocationDetailsDao dao) {
        final String serialNo = dao.getSerialNo();
        final String towerNo = dao.getTowerNo();
        int towerType = dao.getTowerType();
        final int direction = dao.getDirection();
        int fzcNum = dao.getFzcNum();
        int inCharge = dao.getInCharge();
        tvTH.setText(towerNo);
        if (towerType == 0) {
            tvTX.setText("直线塔");
        } else {
            tvTX.setText("耐张塔");
        }
        if (direction == 0) {
            tvVZ.setText("塔前");
        } else {
            tvVZ.setText("塔后");
        }
        tvFZC.setText("" + fzcNum);
        if (inCharge == 0) {
            tvCDZ.setText("存在");
        } else {
            tvCDZ.setText("不存在");
        }

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                Query<ShootAngleDao> queryBuilder = getAngleDao().queryBuilder()
                        .where(ShootAngleDaoDao.Properties.SerialNo.eq(serialNo),
                                ShootAngleDaoDao.Properties.TowerNo.eq(towerNo),
                                ShootAngleDaoDao.Properties.Direction.eq(direction))
                        .build();
                mList = queryBuilder.list();
                psdAdapter.setNewData(mList);
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

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
        threadPool = ThreadPoolHelp.Builder
                .cached()
                .builder();
        psdAdapter = new PSDAdapter(R.layout.item_psd_show, mList);
        rvShowPic.setLayoutManager(new GridLayoutManager(mContext, 2));
        rvShowPic.setAdapter(psdAdapter);

        psdAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().postSticky(mList.get(position));
                startActivity(new Intent(getActivity(), PictureShowAct.class));
            }
        });
    }

    //角度
    private ShootAngleDaoDao getAngleDao() {
        return GreenDaoManager.getInstance().getSession().getShootAngleDaoDao();
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
