package yincheng.gggithub.view.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;

import yincheng.gggithub.helper.TypefaceHelper;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:06:09 16:18
 * Github : yincheng.luo
 */
public class FontAutoCompleteEditText extends AppCompatAutoCompleteTextView {

   public FontAutoCompleteEditText(@NonNull Context context) {
      super(context);
      init();
   }

   public FontAutoCompleteEditText(@NonNull Context context, AttributeSet attrs) {
      super(context, attrs);
      init();

   }

   public FontAutoCompleteEditText(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      init();
   }

   private void init() {
      if (isInEditMode()) return;
      if (isInEditMode()) return;
      setInputType(getInputType() | EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN);
      setImeOptions(getImeOptions() | EditorInfo.IME_FLAG_NO_FULLSCREEN);
      TypefaceHelper.applyTypeface(this);
   }
}
