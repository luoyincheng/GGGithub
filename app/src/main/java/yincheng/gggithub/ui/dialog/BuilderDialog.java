package yincheng.gggithub.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import yincheng.gggithub.App;
import yincheng.gggithub.R;

import static android.view.animation.AnimationUtils.loadAnimation;

/**
 * Created by commi on 2018/3/23.
 */

public class BuilderDialog extends Dialog {
   private View dialogView;
   @LayoutRes private int layoutId;
   @AnimRes private int animRes;
   private boolean cancelable;

   private float widthRatio;
   private float aspectRatio;

   public BuilderDialog(@NonNull DialogBuilder dialogBuilder) {
      this(dialogBuilder, 0);
   }

   public BuilderDialog(@NonNull DialogBuilder dialogBuilder, int themeResId) {
      super(dialogBuilder.mContext, themeResId);
      init(dialogBuilder);
   }

   protected BuilderDialog(@NonNull DialogBuilder dialogBuilder, boolean cancelable, @Nullable
         OnCancelListener cancelListener) {
      super(dialogBuilder.mContext, cancelable, cancelListener);
      init(dialogBuilder);
   }

   @Override protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(layoutId);
      setCancelable(cancelable);
   }

   protected void init(DialogBuilder dialogBuilder) {
      dialogView = findViewById(android.R.id.content);
      this.widthRatio = dialogBuilder.widthRatio;
      this.aspectRatio = dialogBuilder.aspectRatio;
      if ((layoutId = dialogBuilder.mLayoutId) == -1)
         throw new IllegalArgumentException("layout cannot be null");
      animRes = dialogBuilder.animRes;
      cancelable = dialogBuilder.cancelable;
   }

   @Override protected void onStart() {
      super.onStart();
      initWindow();
      if (animRes == -1) return;
      dialogView.startAnimation(loadAnimation(this.getContext(), animRes));
   }

   public void initWindow() {
      Window dialogWindow = getWindow();
//      WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
      DisplayMetrics displayMetrics = App.getInstance().getResources().getDisplayMetrics();
//      layoutParams.width = (int) (displayMetrics.widthPixels * 0.9);
//      layoutParams.height = (int) (layoutParams.width * aspectRatio);
      dialogWindow.setLayout((int) (displayMetrics.widthPixels * widthRatio),
            (int) (displayMetrics.widthPixels * widthRatio * aspectRatio));
//      dialogWindow.setAttributes(layoutParams);
      // TODO: 2018/6/9 0.9 应该是计算出来的，如果窗口有动画，就会影响动画的展示
//      因此应该先解析动画中的xml文件，对缩放动画检测是否有大小是否有超过原始动画，然后为其设置margin
   }

   public static class DialogBuilder {
      private Context mContext;
      @LayoutRes private int mLayoutId = -1;
      private FrameLayout.LayoutParams mLayoutParams;
      private String title = "DialogBuilder";
      private float widthRatio = 0.5f;
      private float aspectRatio = 0.5f;
      @AnimRes private int animRes = -1;
      @StyleRes private int styleRes = R.style.progress_dialog;
      private boolean cancelable = false;

      public DialogBuilder(Context context) {
         this.mContext = context;
      }

      public DialogBuilder withRatio(float widthRatio, float aspectRatio) {
         this.widthRatio = widthRatio;
         this.aspectRatio = aspectRatio;
         return this;
      }

      public DialogBuilder withLayout(@LayoutRes int layoutId) {
         this.mLayoutId = layoutId;
         return this;
      }

      public DialogBuilder withLayoutParams(FrameLayout.LayoutParams layoutParams) {
         this.mLayoutParams = layoutParams;
         return this;
      }

      public DialogBuilder withTitle(String title) {
         this.title = title;
         return this;
      }

      public DialogBuilder withAnimation(@AnimRes int animRes) {
         this.animRes = animRes;
         return this;
      }

      public DialogBuilder withStyle(@StyleRes int styleRes) {
         this.styleRes = styleRes;
         return this;
      }

      public DialogBuilder isCancelable(boolean cancelable) {
         this.cancelable = cancelable;
         return this;
      }

      public BuilderDialog Build() {
         return new BuilderDialog(this, styleRes);
      }
   }
}
