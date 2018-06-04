package yincheng.gggithub.view.widget.recyclerview;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by yincheng on 2018/6/4/14:12.
 * github:luoyincheng
 */
public abstract class BaseViewHolder<D> extends RecyclerView.ViewHolder implements
      View.OnClickListener, View.OnLongClickListener {
   @Nullable protected final BaseRecyclerViewAdapter adapter;

   public interface OnItemClickListener<D> {
      void onItemClick(int position, View v, D itemData);

      void onItemLongClick(int position, View v, D itemData);
   }

   public static View genItemView(@NonNull ViewGroup parent, @LayoutRes int LayoutRes) {
      return LayoutInflater.from(parent.getContext()).inflate(LayoutRes, parent, false);
   }

   public BaseViewHolder(@NonNull View itemView) {
      this(itemView, null);
   }

   public BaseViewHolder(@NonNull View itemView, @Nullable BaseRecyclerViewAdapter adapter) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      this.adapter = adapter;
      itemView.setOnClickListener(this);// TODO: 2018/6/4
      itemView.setOnLongClickListener(this);// TODO: 2018/6/4
   }

   @SuppressWarnings("unchecked") @Override public void onClick(View view) {
      if (adapter != null && adapter.getListener() != null) {
         int position = getAdapterPosition();// TODO: 2018/6/4
         if (position != RecyclerView.NO_POSITION && position < adapter.getItemCount())
            adapter.getListener().onItemClick(position, view, adapter.getItemData(position));
      }
   }

   @SuppressWarnings("unchecked") @Override public boolean onLongClick(View view) {
      if (adapter != null && adapter.getListener() != null) {
         int position = getAdapterPosition();// TODO: 2018/6/4
         if (position != RecyclerView.NO_POSITION && position < adapter.getItemCount())
            adapter.getListener().onItemLongClick(position, view, adapter.getItemData(position));
      }
      return true;
   }

   public abstract void bind(@NonNull D t);

   protected void onViewisDetaching() {}
}
