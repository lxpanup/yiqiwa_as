package com.ssd.yiqiwa.model;

import android.content.Context;

import com.ssd.yiqiwa.model.entity.HomeBottom;
import com.ssd.yiqiwa.model.entity.HomeTop;
import com.ssd.yiqiwa.presenter.OnLoadListener;


/**
 * @author Smile Wei
 * @since 2016/9/22.
 */

public interface HomeLoadModel extends LoadModel {

    void load(OnLoadListener<HomeTop> listener, Context context, int type);

    void load(OnLoadListener<HomeBottom> listener, Context context, int type, int page, int pageSize);
}
