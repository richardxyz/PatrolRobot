package com.zkkc.patrolrobot.moudle.details.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
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
import com.zkkc.patrolrobot.moudle.details.entity.PZBean;
import com.zkkc.patrolrobot.moudle.details.fragment.PSD2Fragment;
import com.zkkc.patrolrobot.moudle.details.fragment.PSDFragment;

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


    private List<PZBean> pzBeans = new ArrayList<>();
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
        //测试数据
        for (int i = 0; i < 10; i++) {
            PZBean pzBean = new PZBean();
            pzBean.setThNum("塔号" + (i + 1));
            pzBean.setPzName("张三");
            pzBean.setPsdNum("" + (34 - i));
            pzBean.setWcDate("2019-03-19 12:35:14");
            pzBeans.add(pzBean);
        }
        //测试数据

        pzAdapter = new PZAdapter(R.layout.item_details_left, locationDetailsDaos);
        rvPZ.setLayoutManager(new LinearLayoutManager(this));
        rvPZ.setAdapter(pzAdapter);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                locationDetailsDaos = queryPSDData();
                if (locationDetailsDaos != null) {
                    if (locationDetailsDaos.size() > 0) {
                        pzAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.showShort("暂无数据");
                    }
                } else {
                    ToastUtils.showShort("暂无数据");
                }

            }
        });


        String[] arr = {"拍摄点1", "拍摄点2"};
        ArrayList<Fragment> fragments = new ArrayList<>();
        PSDFragment psdFragment = new PSDFragment();
        PSD2Fragment psd2Fragment = new PSD2Fragment();
        fragments.add(psdFragment);
        fragments.add(psd2Fragment);
        stl.setViewPager(viewPager, arr, this, fragments);


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
