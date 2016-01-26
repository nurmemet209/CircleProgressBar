package com.example.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;

/**
 * Created by nurmemet on 2016/1/23.
 */
public class CircularProgressBarCustom extends View

{
    private float mRingWidth;
    private int mRingColor;
    private int mTextColor;
    private float mProgress;
    private RectF mCircleRect=new RectF();
    private Paint mCirclePaint;
    private Paint mTextPaint;
    public CircularProgressBarCustom(Context context) {
        super(context);
    }

    public CircularProgressBarCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.CircularProgressBarCustom,defStyleAttr,0);
        mRingColor =a.getColor(R.styleable.CircularProgressBarCustom_ring_color, Color.RED);
        mRingWidth =a.getDimension(R.styleable.CircularProgressBarCustom_ring_width,10);
        mProgress =a.getFloat(R.styleable.CircularProgressBarCustom_progress_,0.2f);
        mTextColor =a.getColor(R.styleable.CircularProgressBarCustom_text_color,Color.BLUE);
        init();
        a.recycle();
    }

    public CircularProgressBarCustom(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    @Override
    protected void onDraw(Canvas canvas) {
       // super.onDraw(canvas);

        float x=this.getWidth()/2;
        float y=this.getHeight()/2;
        float d=360* mProgress;
        canvas.translate(x,y);
        canvas.drawArc(mCircleRect,0,d,false,mCirclePaint);
        RectF rf=getTextRect();
        String str=getPercent();
        canvas.drawText(str,rf.left,rf.top,mTextPaint);

    }

    private String getPercent(){
        StringBuilder builder=new StringBuilder();
        float percent= mProgress *100;
        //DecimalFormat df=new   java.text.DecimalFormat("##.00");
        DecimalFormat df=new   DecimalFormat("00.00");
        builder.append(df.format(percent)).append("%");
        return builder.toString();
    }

    private RectF getTextRect(){
        RectF rc=new RectF();
        float  width=mTextPaint.measureText(getPercent());
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float height= fm.ascent - fm.descent;
        rc.set(-width/2,-height/2,width/2,height/2);
        return rc;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final  int  width=this.getMeasuredWidth();
        final int height=this.getMeasuredHeight();
        final float diameter=Math.min(width,height);
        float innerRadius=diameter/2- mRingWidth /2;
        mCircleRect.set(-innerRadius,-innerRadius,innerRadius,innerRadius);


    }

    private void init(){
        mCirclePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(mRingColor);
        mCirclePaint.setStrokeWidth(mRingWidth);
        mCirclePaint.setStyle(Paint.Style.STROKE);

        mTextPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        int textSize=sp2px(this.getContext(),16) ;
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(mTextColor);
    }


    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 0.0~1.0
     * @param progress
     */
    public void updateProgress(float progress){
        this.mProgress =progress;
        invalidate();
    }

    public void setProgress(float progress){
        updateProgress(progress);
    }




}
