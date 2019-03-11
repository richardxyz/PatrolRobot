package com.zkkc.patrolrobot.widget;

import android.content.Context;
import android.view.View;

import com.zkkc.patrolrobot.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by ShiJunRan on 2019/3/11
 */
public class XLPopup extends BasePopupWindow {

    public XLPopup(Context context) {
        super(context);
    }

    public XLPopup(Context context, boolean delayInit) {
        super(context, delayInit);
    }

    public XLPopup(Context context, int width, int height) {
        super(context, width, height);
    }

    public XLPopup(Context context, int width, int height, boolean delayInit) {
        super(context, width, height, delayInit);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_xian_lu);
    }

}
