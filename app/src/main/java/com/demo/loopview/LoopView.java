package com.demo.loopview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * description: 垂直自动翻滚
 * created by kalu on 2017/12/7 9:21
 */
public class LoopView extends ViewFlipper implements View.OnLongClickListener {

    private List<View> views;

    public LoopView(Context context) {
        this(context, null);
    }

    public LoopView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 2.动画差值器
        final AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
        // 3.进入动画
        final AnimationSet animIn = new AnimationSet(false);
        animIn.setInterpolator(interpolator);
        animIn.setDuration(400);
        final AlphaAnimation alphaIn = new AlphaAnimation(0f, 1f);
        animIn.addAnimation(alphaIn);
        alphaIn.setDuration(400);
        final TranslateAnimation transIn = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 1f,
                Animation.RELATIVE_TO_PARENT, 0);
        animIn.addAnimation(transIn);
        transIn.setDuration(400);
        setInAnimation(animIn);
        // 4.离开动画
        final AnimationSet animOut = new AnimationSet(false);
        animOut.setInterpolator(interpolator);
        animOut.setDuration(400);
        final AlphaAnimation alphaOut = new AlphaAnimation(1f, 0f);
        animOut.addAnimation(alphaOut);
        alphaOut.setDuration(400);
        final TranslateAnimation transOut = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, -1f);
        animOut.addAnimation(transOut);
        transOut.setDuration(400);
        setOutAnimation(animOut);
    }

    /**
     * 设置循环滚动的View数组
     */
    public void setLoopView(List<View> views) {

        if (null == views || views.size() == 0) return;

        this.views = views;

        if (getChildCount() != 0) {
            removeAllViews();
        }

        for (int i = 0; i < views.size(); i++) {

            final View view = views.get(i);
            if (null == view) continue;

            view.setOnLongClickListener(this);
            addView(view);
        }
        startFlipping();
    }

    public void setOnLoopItemClickListener(final OnLoopItemClickListener onLoopItemClickListener) {

        if (null == views || views.size() == 0 || null == onLoopItemClickListener) return;

        for (int i = 0; i < views.size(); i++) {

            final View view = views.get(i);
            if (null == view) continue;

            final int position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLoopItemClickListener.onLoopItemClick(position, view);
                }
            });
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        for (int i = 0; i < views.size(); i++) {

            final View view = views.get(i);
            if (null == view) continue;

            view.setOnLongClickListener(null);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        // Log.e("kalu", "onLongClick");
        return true;
    }

    public interface OnLoopItemClickListener {
        void onLoopItemClick(int position, View view);
    }
}
