package com.ssd.yiqiwa.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;


/**
 * 使ViewPager不能滑动
 */
public class NoRollGridView extends GridView {


    public NoRollGridView(Context context) {
        super(context);
    }

    public NoRollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoRollGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
