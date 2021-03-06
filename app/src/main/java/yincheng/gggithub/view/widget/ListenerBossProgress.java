package yincheng.gggithub.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import yincheng.gggithub.R;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:06:12 7:55
 * Github : yincheng
 */
public class ListenerBossProgress extends ViewGroup {
   private int circleRadius;
   private int circleMargin;
   private int circleNum;
   private int circlrColor;
   private List<Animation> shrinkAnimationList;
   private List<Animation> expandAnimationList;
   private List<Animation.AnimationListener> shrinkAnimationListenerList;
   private List<Animation.AnimationListener> expandAnimationListenerList;

   public ListenerBossProgress(Context context) {
      this(context, null);
   }

   public ListenerBossProgress(Context context, AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public ListenerBossProgress(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable
                  .ListenerBossProgress,
            defStyleAttr, R.style.ls_BossProgress_default);// TODO: 2018/6/12 作用
      try {
         circleRadius = (int) array.getDimension(R.styleable
               .ListenerBossProgress_ls_circleRadius, 15);
         circleMargin = (int) array.getDimension(R.styleable
               .ListenerBossProgress_ls_circleMargin, 15);
         circleNum = array.getInt(R.styleable.ListenerBossProgress_ls_circleNum, 3);
         circlrColor = array.getColor(R.styleable.ListenerBossProgress_ls_circleColor,
               getResources()
               .getColor(R.color.color_333));
      } finally {
         array.recycle();
      }
      init();
   }

   private void init() {
      addChildren();
      shrinkAnimationList = new ArrayList<>();
      expandAnimationList = new ArrayList<>();
      shrinkAnimationListenerList = new ArrayList<>();
      expandAnimationListenerList = new ArrayList<>();

      for (int i = 0; i < getChildCount(); i++) {
         final int currentIndex = i;
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
         expandAnimationListenerList.add(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {
            }

            @Override public void onAnimationEnd(Animation animation) {
               getChildAt(currentIndex).startAnimation(shrinkAnimationList.get(currentIndex));

            }

            @Override public void onAnimationRepeat(Animation animation) {

            }
         });
         shrinkAnimationList.get(currentIndex).setAnimationListener(shrinkAnimationListenerList
               .get(currentIndex));
         expandAnimationList.get(currentIndex).setAnimationListener(expandAnimationListenerList
               .get(currentIndex));
      }
   }


   @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      setMeasuredDimension(circleRadius * circleNum * 2 + (circleNum - 1) * circleMargin,
            circleRadius * 2);
   }

   @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
      for (int i = 0; i < getChildCount(); i++) {
         final int currentIndex = i;
         final View child = getChildAt(i);
         final int childWidth = circleRadius * 2;
         final int childHeight = circleRadius * 2;
         child.layout((childWidth + circleMargin) * i, 0, (childWidth + circleMargin) *
               i + childWidth, childHeight);
         Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim
               .anim_alpha_expand);
         animation.setStartOffset(500 / getChildCount() * i);
         animation.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {

            }

            @Override public void onAnimationEnd(Animation animation) {
               child.startAnimation(shrinkAnimationList.get(currentIndex));
            }

            @Override public void onAnimationRepeat(Animation animation) {
            }
         });
         child.startAnimation(animation);
      }
   }

   private void addChildren() {
      for (int i = 0; i < circleNum; i++) addView(new RoundedView(this));
   }

   @Override protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      Logger.e("bossProgress:onDetachedFromWindow");
   }

   @Nullable @Override protected Parcelable onSaveInstanceState() {
      return super.onSaveInstanceState();
   }

   class RoundedView extends View {
      private int radius;
      private int color;
      private Paint paint;

      public RoundedView(ListenerBossProgress parent) {
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
