package yincheng.gggithub.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:06:12 7:55
 * Github : yincheng.luo
 */
public class BossProgress extends ViewGroup {
   private RoundedView roundedView1, roundedView2, roundedView3;

   public BossProgress(Context context) {
      super(context);
      init();
   }

   public BossProgress(Context context, AttributeSet attrs) {
      super(context, attrs);
      init();
   }

   public BossProgress(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      init();
   }

   public BossProgress(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
      super(context, attrs, defStyleAttr, defStyleRes);
      init();
   }

   private void init() {
      roundedView1 = new RoundedView(this,);
   }

   @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      //强行指定宽高比
   }

   @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {

   }


   private void add3RoundedView() {
      addView();
   }

   class RoundedView extends View {
      private int radius;
      private Paint paint;
      private int cx, cy;

      public RoundedView(Context context, int radius, int cx, int cy) {
         super(context);
         this.radius = radius;
         this.cx = cx;
         this.cy = cy;
         init();
      }

      void init() {
         paint = new Paint(Color.parseColor("#3c5f78"));
         paint.setAntiAlias(true);
         paint.setStyle(Paint.Style.FILL);
      }

      @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      }

      @Override protected void onDraw(Canvas canvas) {
         super.onDraw(canvas);
         canvas.drawCircle(cx, cy, radius, paint);
      }


   }
}
