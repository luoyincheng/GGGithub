package yincheng.gggithub.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import yincheng.gggithub.R;

/**
 * Created by yincheng on 2018/6/13/12:24.
 * github:luoyincheng
 */
public class FilterRadioButton extends CompoundButton {
   public FilterRadioButton(Context context) {
      this(context, null);
   }

   public FilterRadioButton(Context context, AttributeSet attrs) {
      this(context, attrs, R.attr.radioButtonStyle);
   }

   public FilterRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
      this(context, attrs, defStyleAttr, 0);
   }

   public FilterRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int
         defStyleRes) {
      super(context, attrs, defStyleAttr, defStyleRes);
   }

   @Override
   public void toggle() {
      if (!isChecked()) {
         super.toggle();
      } else {
         setChecked(false);
      }
   }

   @Override
   public CharSequence getAccessibilityClassName() {
      return RadioButton.class.getName();
   }
}
