package com.zkkc.patrolrobot.moudle.details.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.zkkc.green.gen.LocationDetailsDaoDao;
import com.zkkc.patrolrobot.R;
import com.zkkc.patrolrobot.base.BaseActivity;
import com.zkkc.patrolrobot.base.BasePresenter;
import com.zkkc.patrolrobot.base.BaseView;
import com.zkkc.patrolrobot.common.GreenDaoManager;
import com.zkkc.patrolrobot.entity.LocationDetailsDao;
import com.zkkc.patrolrobot.moudle.details.adapter.PZAdapter;
import com.zkkc.patrolrobot.moudle.details.fragment.PSDFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by ShiJunRan on 2019/3/19
 * 配置详情页面
 */
public class DetailsAct extends BaseActivity {


    @BindView(R.id.tvXlbh)
    TextView tvXlbh;
    @BindView(R.id.tvQsth)
    TextView tvQsth;
    @BindView(R.id.tvZzth)
    TextView tvZzth;
    @BindView(R.id.tvTzs)
    TextView tvTzs;
    @BindView(R.id.tvPsdzs)
    TextView tvPsdzs;
    @BindView(R.id.rvPZ)
    RecyclerView rvPZ;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.stl)
    SlidingTabLayout stl;
    @BindView(R.id.viewPager)
    ViewPager viewPager;


    List<LocationDetailsDao> locationDetailsDaos = new ArrayList<>();
    PZAdapter pzAdapter;
    private ExecutorService threadPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullscreen(true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.details_act;
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
        mainData();

        pzAdapter = new PZAdapter(R.layout.item_details_left, locationDetailsDaos);
        rvPZ.setLayoutManager(new LinearLayoutManager(this));
        rvPZ.setAdapter(pzAdapter);
        pzAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().post(locationDetailsDaos.get(position));
                ToastUtils.showShort(position+"");
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                locationDetailsDaos = queryPSDData();
                if (locationDetailsDaos != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvPsdzs.setText(locationDetailsDaos.size() + "");
                        }
                    });
                    if (locationDetailsDaos.size() > 0) {
//                        pzAdapter.notifyDataSetChanged();
                        pzAdapter.setNewData(locationDetailsDaos);
                    } else {
                        ToastUtils.showShort("暂无数据");
                    }
                } else {
                    ToastUtils.showShort("暂无数据");
                }

            }
        });


        String[] arr = {"拍摄点详情"};
        ArrayList<Fragment> fragments = new ArrayList<>();
        PSDFragment psdFragment = new PSDFragment();
        fragments.add(psdFragment);
        stl.setViewPager(viewPager, arr, this, fragments);


    }

    private void mainData() {
        String xl_num = SPUtils.getInstance().getString("XL_NUM");
        String xl_q = SPUtils.getInstance().getString("XL_Q");
        String xl_z = SPUtils.getInstance().getString("XL_Z");
        int tower_total = SPUtils.getInstance().getInt("TOWER_TOTAL");
        if (xl_num.equals("")) {
            tvXlbh.setText("--");
        } else {
            tvXlbh.setText(xl_num);
        }
        if (xl_q.equals("")) {
            tvQsth.setText("--");
        } else {
            tvQsth.setText(xl_q);
        }
        if (xl_z.equals("")) {
            tvZzth.setText("--");
        } else {
            tvZzth.setText(xl_z);
        }
        tvTzs.setText((tower_total + 1) + "");
    }

    /**
     * 查询拍摄点数据
     */
    private List<LocationDetailsDao> queryPSDData() {
        return getLDDao().loadAll();
    }

    @OnClick({R.id.btnClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnClose:
                finish();
                break;
        }
    }

    //拍摄点
    private LocationDetailsDaoDao getLDDao() {
        return GreenDaoManager.getInstance().getSession().getLocationDetailsDaoDao();
    }
}
