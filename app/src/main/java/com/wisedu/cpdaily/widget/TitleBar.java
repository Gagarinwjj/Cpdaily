package com.wisedu.cpdaily.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wisedu.cpdaily.R;
import com.wisedu.cpdaily.utils.CommonUtils;


/**
 * 带返回的标题栏
 * Created by wjj on 2017/07/21 10:32:46.
 */
public class TitleBar extends RelativeLayout {
    private ImageView ivBack;
    private TextView tvLeft, tvTitle, tvRight;
    private View divider;
    private RightActionClickListener listener;
    private float density;
    private boolean currentShowState = true;
    private int titleTextLength, backId;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        density = context.getResources().getDisplayMetrics().density;
        //加载配置
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.titleBar,
                defStyleAttr, 0);
        //1 左边返回按钮
        //1.1 左操作（返回按钮）是否显示，默认true显示
        Boolean leftActionShow = typedArray.getBoolean(R.styleable.titleBar_leftActionShow, true);
        //1.2 返回的图标
        backId = typedArray.getResourceId(R.styleable.titleBar_leftActionIcon, R.drawable
                .black_back_icon);
        //1.3 返回的文本
        String leftActionName = typedArray.getString(R.styleable.titleBar_leftActionName);
        //1.4 返回文字大小,默认单位就是sp,所以这里用整型
        int leftActionTextSize = typedArray.getInt(R.styleable.titleBar_leftActionTextSize,
                16);
        //1.5 返回文本颜色
        int leftActionColor = typedArray.getInt(R.styleable.titleBar_leftActionTextColor,
                getResources().getColor(R.color.dark_gray));


        //2 右边操作文本
        //2.1 右操作是否显示，默认false不显示
        Boolean rightActionShow = typedArray.getBoolean(R.styleable.titleBar_rightActionShow,
                false);
        //2.2 右操作名称
        String rightActionName = typedArray.getString(R.styleable.titleBar_rightActionName);
        //2.3 右操作图标
        int rightActionIcon = typedArray.getResourceId(R.styleable.titleBar_rightActionIcon, R
                .drawable.action_more);
        //2.4 右操作图标，默认false不显示
        Boolean rightActionIconShow = typedArray.getBoolean(R.styleable
                .titleBar_rightActionIconShow, false);
        //2.5 右操作图标是否显示在左边，默认true
        Boolean rightActionIconLeft = typedArray.getBoolean(R.styleable
                .titleBar_rightActionIconLeft, true);
        //2.6 右操作文字大小,默认单位就是sp,所以这里用整型
        int rightActionTextSize = typedArray.getInt(R.styleable.titleBar_rightActionTextSize,
                16);
        //2.7 右操作颜色
        int rightActionColor = typedArray.getInt(R.styleable.titleBar_rightActionTextColor,
                getResources().getColor(R.color.gray_dark_bg));

        //3 标题文本
        //3.1 标题是否显示
        Boolean titleShow = typedArray.getBoolean(R.styleable.titleBar_titleShow, true);
        //3.2 标题名称
        String titleName = typedArray.getString(R.styleable.titleBar_titleName);
        //3.3 标题文字大小,默认单位就是sp,所以这里用整型
        int titleTextSize = typedArray.getInt(R.styleable.titleBar_titleTextSize, 18);
        //3.4 标题文字最大长度,默认15(默认0则不限制)
        titleTextLength = typedArray.getInt(R.styleable.titleBar_titleTextLength, 15);
        //3.5 标题颜色
        int titleTextColor = typedArray.getInt(R.styleable.titleBar_titleTextColor, getResources
                ().getColor(R.color.black));

        //4 控件背景
        int titleBarBg = typedArray.getColor(R.styleable.titleBar_titleBarBg, context.getResources()
                .getColor(R.color.white));
        this.setBackgroundColor(titleBarBg);//布局中的background值被覆盖

        //5 分割线
        //5.1 分割线是否显示，默认false不显示
        boolean dividerShow = typedArray.getBoolean(R.styleable.titleBar_dividerShow, false);
        //5.2 分割线颜色，默认灰色
        int dividerColor = typedArray.getColor(R.styleable.titleBar_dividerColor, context
                .getResources().getColor(R.color.title_bar_divider));
        //5.3 分割线高度
        int dividerHeight = typedArray.getDimensionPixelSize(R.styleable
                .titleBar_dividerHeight, (int) density);
        //释放配置
        typedArray.recycle();

        //返回按钮
        ivBack = new ImageView(context);
        ivBack.setId(R.id.title_bar_back);
        ivBack.setImageResource(backId);//开放出去
        ivBack.setPadding(0, 10, 30, 10);//放大点击边界
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams
                .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        layoutParams.leftMargin = context.getResources().getDimensionPixelSize(R.dimen
                .titleBar_backArrow_padding);//系统的dp转px 或 自己的10*density;
        ivBack.setLayoutParams(layoutParams);
        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {//默认实现
               /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ((Activity) context).finishAfterTransition();
                } else {
                    ((Activity) context).finish();
                    ((Activity) context).overridePendingTransition(R.anim.push_left_in, R.anim
                            .push_right_out);
                }*/
                ((Activity) context).finish();
               /* ((Activity) context).overridePendingTransition(R.anim.push_left_in, R.anim
                        .push_right_out);*/
            }
        });
        ivBack.setVisibility(leftActionShow ? View.VISIBLE : View.GONE);
        if (leftActionShow && !TextUtils.isEmpty(leftActionName)) {
            tvLeft = new TextView(context);
            tvLeft.setText(leftActionName);
            tvLeft.setTextSize(leftActionTextSize);//默认单位就是sp
            tvLeft.setTextColor(leftActionColor);
            LayoutParams layoutParamsLeft = new LayoutParams(ViewGroup.LayoutParams
                    .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParamsLeft.addRule(RelativeLayout.RIGHT_OF, ivBack.getId());
            layoutParamsLeft.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            layoutParamsLeft.leftMargin = this.getResources().getDimensionPixelSize(R.dimen
                    .titleBar_left_margin);//dp转px
            tvLeft.setLayoutParams(layoutParamsLeft);
        }

        //中间文字
        tvTitle = new TextView(context);
        tvTitle.setMaxLines(1);
        tvTitle.setEllipsize(TextUtils.TruncateAt.END);
        if (titleTextLength > 0 && !TextUtils.isEmpty(titleName) && titleName.length() >
                titleTextLength) {
            titleName = titleName.substring(0, titleTextLength) + "...";
        }
        tvTitle.setText(titleName);//开放出去
        tvTitle.setTextSize(titleTextSize);//默认单位就是sp
        tvTitle.setTextColor(titleTextColor);
        LayoutParams layoutParamsTitle = new LayoutParams(ViewGroup.LayoutParams
                .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsTitle.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        tvTitle.setLayoutParams(layoutParamsTitle);
        tvTitle.setVisibility(titleShow ? View.VISIBLE : View.GONE);

        //右侧文字
        tvRight = new TextView(context);
        tvRight.setText(rightActionName);//开放出去
        tvRight.setTextSize(rightActionTextSize);//默认单位就是sp
        tvRight.setTextColor(rightActionColor);
        if (rightActionIconShow) {
            if (rightActionIconLeft)
                tvRight.setCompoundDrawablesWithIntrinsicBounds(rightActionIcon, 0, 0, 0);
            else
                tvRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightActionIcon, 0);
        }
        LayoutParams layoutParamsRight = new LayoutParams(ViewGroup.LayoutParams
                .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        layoutParamsRight.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        layoutParamsRight.rightMargin = this.getResources().getDimensionPixelSize(
                R.dimen.titleBar_right_margin);//dp转px
        tvRight.setLayoutParams(layoutParamsRight);
        tvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    //ToastUtils.show(TitleBar.this.getContext(), "被点击了");
                    listener.onClick(v);//实现转交外部接口
                }
            }
        });
        tvRight.setVisibility(rightActionShow ? View.VISIBLE : View.GONE);
        //分割线
        divider = new View(context);
        divider.setBackgroundColor(dividerColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //状态栏
            View statusBar = new View(context);
            statusBar.setId(R.id.title_bar_status);
            statusBar.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT, (int) (24 * density)));
            //statusBar.setBackgroundColor(Color.parseColor("#55889988"));//自定义标题栏背景色
            this.addView(statusBar);
            //包裹容器
            RelativeLayout rl = new RelativeLayout(context);
            LayoutParams rlLayoutParams = new LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT, (int) (48 * density));
            rlLayoutParams.addRule(RelativeLayout.BELOW, statusBar.getId());
            rl.setLayoutParams(rlLayoutParams);
            rl.addView(ivBack);
            if (tvLeft != null) {
                rl.addView(tvLeft);
            }
            rl.addView(tvTitle);
            rl.addView(tvRight);
            if (dividerShow) {
                LayoutParams layoutParamsDivider = new LayoutParams(ViewGroup
                        .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParamsDivider.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout
                        .TRUE);//当SDK_INT ≤18时，导致撑满全屏，所以单独判断
                layoutParamsDivider.height = dividerHeight;
                divider.setLayoutParams(layoutParamsDivider);
                rl.addView(divider);
            }
            this.addView(rl);
        } else {
            ViewGroup.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT, (int) (48 * density));
            this.setLayoutParams(lp);//布局已经添加到界面，所以再次设置无效？
            this.setMinimumHeight((int) (48 * density));
            this.addView(ivBack);
            if (tvLeft != null) {
                this.addView(tvLeft);
            }
            this.addView(tvTitle);
            this.addView(tvRight);
            if (dividerShow) {
                LayoutParams layoutParamsDivider = new LayoutParams(ViewGroup
                        .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //layoutParamsDivider.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout
                //.TRUE);//当SDK_INT ≤18时，导致撑满全屏，所以单独判断==>换成topMargin
                layoutParamsDivider.height = dividerHeight;
                layoutParamsDivider.topMargin = (int) (48 * density) - dividerHeight;
                divider.setLayoutParams(layoutParamsDivider);
                this.addView(divider);
            }
        }
    }

    public void setOnBackClickListener(OnClickListener listener) {
        ivBack.setOnClickListener(listener);
    }

    /**
     * 设置标题名
     *
     * @param name
     */
    public void setTitleName(String name) {
        if (titleTextLength > 0 && !TextUtils.isEmpty(name) && name.length() >
                titleTextLength) {
            name = name.substring(0, titleTextLength) + "...";
        }
        tvTitle.setText(name);
    }

    public void setTitleName(int resId) {
        setTitleName(getResources().getString(resId));
    }

    /**
     * 设置标题文本颜色
     *
     * @param color 颜色
     */
    public void setTitleColor(int color) {
        tvTitle.setTextColor(color);
    }

    /**
     * 设置右侧文本颜色，同时考虑到边界图片
     *
     * @param color 颜色
     */
    public void setRightTextColor(int color) {
        tvRight.setTextColor(color);
        Drawable[] drawables = tvRight.getCompoundDrawables();
        if (drawables.length == 4) {
            if (drawables[0] != null || drawables[1] != null || drawables[2] != null ||
                    drawables[3] != null) {
                for (int i = 0; i < drawables.length; i++) {
                    if (drawables[i] != null) {
                        drawables[i] = CommonUtils.tintDrawable(drawables[i], ColorStateList
                                .valueOf(color));
                    }
                }
                tvRight.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1],
                        drawables[2],
                        drawables[3]);
            }
        }
    }

    /**
     * 设置返回图片的颜色
     */
    public void setIvBackColor(int color) {
        //Drawable originalDrawable = ivBack.getDrawable();//无法着色
        Drawable originalDrawable = getResources().getDrawable(backId);
        ivBack.setImageDrawable(CommonUtils.tintDrawable(originalDrawable, ColorStateList
                .valueOf(color)));
    }

    /**
     * 设置主题色，包括左侧返回按钮，中间标题栏，右侧文本
     *
     * @param color 颜色
     */
    public void setThemeColor(int color) {
        setTitleColor(color);
        setRightTextColor(color);
        setIvBackColor(color);
    }

    /**
     * 设置右侧按钮名
     *
     * @param name
     */
    public void setRightActionName(String name) {
        tvRight.setText(name);
    }

    public void setRightActionName(int resId) {
        tvRight.setText(resId);
    }

    public void setRightActionShow(boolean isShow) {
        tvRight.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public boolean isShowing() {
        return currentShowState;
    }

    public void hide() {
        if (!currentShowState)
            return;
        this.animate().translationYBy(-48 * density).setDuration(400).start();
        currentShowState = false;

    }

    public void show() {
        if (currentShowState)
            return;
        this.animate().translationYBy(48 * density).setDuration(400).start();
        currentShowState = true;
    }

    public void setRightActionClickListener(RightActionClickListener listener) {
        this.listener = listener;
    }


    public interface RightActionClickListener {
        void onClick(View view);
    }
}
