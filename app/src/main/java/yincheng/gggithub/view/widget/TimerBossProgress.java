package yincheng.gggithub.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import yincheng.gggithub.R;

/**
 * Created by yincheng on 2018/6/14/13:43.
 * github:luoyincheng
 */
public class TimerBossProgress extends ViewGroup {
   private int circleRadius;
   private int circleMargin;
   private int circleNum;
   private int circlrColor;
   private Timer timer;
   private TimerTask timerTask;
   private Handler handler;
   private List<Animation> shrinkAnimationList;
   private List<Animation> expandAnimationList;
   private List<Animation.AnimationListener> shrinkAnimationListenerList;
   private List<AnimRunnable> animRunnableList;

   public TimerBossProgress(Context context) {
      this(context, null);
   }

   public TimerBossProgress(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public TimerBossProgress(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      Logger.i("---");
      final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TimerBossProgress,
            defStyleAttr, R.style.ti_BossProgress_default);// TODO: 2018/6/12 作用
      try {
         circleRadius = (int) array.getDimension(R.styleable
               .TimerBossProgress_ti_circleRadius, 15);
         circleMargin = (int) array.getDimension(R.styleable.TimerBossProgress_ti_circleMargin, 15);
         circleNum = array.getInt(R.styleable.TimerBossProgress_ti_circleNum, 3);
         circlrColor = array.getColor(R.styleable.TimerBossProgress_ti_circleColor, getResources()
               .getColor(R.color.color_333));
      } finally {
         array.recycle();
      }
      init();
   }

   @Override protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
      super.onVisibilityChanged(changedView, visibility);
      Logger.i("---");
      cancelTimer();
   }

   private void init() {
      Logger.i("---");
      addChildren();
      handler = new Handler(Looper.getMainLooper());// TODO: 2018/6/14  to un
      shrinkAnimationList = new ArrayList<>();
      expandAnimationList = new ArrayList<>();
      shrinkAnimationListenerList = new ArrayList<>();
      animRunnableList = new ArrayList<>();

      for (int i = 0; i < getChildCount(); i++) {
         int currentIndex = i;
         shrinkAnimationList.add(AnimationUtils.loadAnimation(this.getContext(), R.anim
               .anim_alpha_shrink));
         expandAnimationList.add(AnimationUtils.loadAnimation(this.getContext(), R.anim
               .anim_alpha_expand));
         shrinkAnimationListenerList.add(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {

            }

            @Override public void onAnimationEnd(Animation animation) {
               getChildAt(currentIndex).startAnimation(expandAnimationList.get(currentIndex));
            }

            @Override public void onAnimationRepeat(Animation animation) {

            }
         });
         animRunnableList.add(new AnimRunnable(i));
         shrinkAnimationList.get(i).setAnimationListener(shrinkAnimationListenerList.get(i));
      }
   }

   @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      Logger.i("---");
      setMeasuredDimension(circleRadius * circleNum * 2 + (circleNum - 1) * circleMargin,
            circleRadius * 2);
   }

   @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
      Logger.i("---");
      for (int i = 0; i < getChildCount(); i++) {
         final View child = getChildAt(i);
         final int childWidth = circleRadius * 2;
         final int childHeight = circleRadius * 2;
         child.layout((childWidth + circleMargin) * i, 0, (childWidth + circleMargin) *
               i + childWidth, childHeight);
      }
      startTimer();
   }

   @Override protected void onAttachedToWindow() {
      Logger.i("---");
      super.onAttachedToWindow();
   }

   private void addChildren() {
      for (int i = 0; i < circleNum; i++) addView(new TimerBossProgress.RoundedView(this));
   }

   @Override protected void onDetachedFromWindow() {
      Logger.i("---");
      super.onDetachedFromWindow();
      cancelTimer();
   }


   @Nullable @Override protected Parcelable onSaveInstanceState() {
      Logger.i("---");
      return super.onSaveInstanceState();
   }

   private void startTimer() {
      cancelTimer();
      if (timer == null) timer = new Timer();
      if (timerTask == null) timerTask = new TimerTask() {
         @Override public void run() {//一次性发送四个viewAction到messageQueue中去
            for (int i = 0; i < getChildCount(); i++) {
               handler.postDelayed(animRunnableList.get(i), 150 * i);
            }
         }
      };
      timer.schedule(timerTask, 0, 800);
   }

   private class AnimRunnable implements Runnable {
      private int viewIndex;

      public AnimRunnable(int viewIndex) {
         this.viewIndex = viewIndex;
      }

      @Override public void run() {
         getChildAt(viewIndex).startAnimation(shrinkAnimationList.get(viewIndex));
      }
   }

   public void cancelTimer() {
      if (timer != null) {
         timer.cancel();
         timer = null;
      }
      if (timerTask != null) {
         timerTask.cancel();
         timerTask = null;
      }
   }

   class RoundedView extends View {
      private int radius;
      private int color;
      private Paint paint;

      public RoundedView(TimerBossProgress parent) {
         super(parent.getContext());
         this.radius = parent.circleRadius;
         this.color = parent.circlrColor;
         init();
      }

      void init() {
         paint = new Paint();
         paint.setColor(color);
         paint.setAntiAlias(true);
         paint.setStyle(Paint.Style.FILL);
      }

      @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
         setMeasuredDimension(radius * 2, radius * 2);
      }

      @Override protected void onDraw(Canvas canvas) {
         super.onDraw(canvas);
         canvas.drawCircle(radius, radius, radius, paint);
      }
   }
}
