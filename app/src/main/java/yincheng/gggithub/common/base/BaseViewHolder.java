package yincheng.gggithub.common.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:05:13 21:10
 * Github : yincheng.luo
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
   public BaseViewHolder(View itemView) {
      super(itemView);
   }

   public interface onItemClickListener<T> {
      void onItemClick(int position, View v, T item);

      void onItemLongClick(int position, View v, T item);
   }

}
