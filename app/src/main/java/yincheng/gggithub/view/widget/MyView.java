package yincheng.gggithub.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.orhanobut.logger.Logger;

import yincheng.gggithub.R;

/**
 * Created by yincheng on 2018/6/12/10:54.
 * github:luoyincheng
 */
public class MyView extends View {

   private int radius = 60;
   private Paint paint;

   public MyView(Context context) {
      this(context, null);
   }

   public MyView(Context context, @Nullable AttributeSet attrs) {
      this(context, attrs, 0);
   }

   public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      init();
   }

   void init() {
      paint = new Paint();
      paint.setColor(getResources().getColor(R.color.colorAccent));
      paint.setAntiAlias(true);
      paint.setStyle(Paint.Style.FILL);
   }

   @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      setMeasuredDimension(radius * 2, radius * 2);
   }

   @Override protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      canvas.drawCircle(radius, radius, radius, paint);
      Logger.i("onDrawonDrawonDrawonDraw:" + "radius:" + radius);
   }
}
