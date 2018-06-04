package yincheng.gggithub.view.widget.recyclerview;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import yincheng.gggithub.R;

/**
 * Created by yincheng on 2018/6/4/15:03.
 * github:luoyincheng
 */
public class ProgressBarViewHolder extends BaseViewHolder {
   public ProgressBarViewHolder(@NonNull View itemView) {
      super(itemView);
   }

   public static ProgressBarViewHolder newInstance(ViewGroup viewGroup) {
      return new ProgressBarViewHolder(genItemView(viewGroup, R.layout.item_recycler_progress));
   }

   @Override public void bind(@NonNull Object t) {

   }
}
