package yincheng.gggithub.view.widget.recyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yincheng.gggithub.helper.AnimHelper;

/**
 * Created by yincheng on 2018/6/4/14:17.
 * github:luoyincheng
 */
public abstract class BaseRecyclerViewAdapter<M, VH extends BaseViewHolder, L extends
      BaseViewHolder.OnItemClickListener<M>> extends
      RecyclerView.Adapter<VH> {

   private final static int PROGRESS_TYPE = 2018;
   @NonNull private List<M> mData;
   @Nullable private L mListener;
   private int lastKnowingPosition = -1;
   private boolean enableAnimation = true;
   private boolean showGuide;
   private GuideListener mGuideListener;
   private boolean progressAdded;
   private int rowWidth;

   public boolean isEnableAnimation() {
      return enableAnimation;
   }

   public boolean isShowGuide() {
      return showGuide;
   }

   public boolean isProgressAdded() {
      return progressAdded;
   }

   protected BaseRecyclerViewAdapter() {
      this(new ArrayList<>());
   }

   protected BaseRecyclerViewAdapter(@NonNull List<M> data) {
      this(data, null);
   }

   protected BaseRecyclerViewAdapter(@NonNull List<M> data, @Nullable L listener) {
      this.mData = data;
      this.mListener = listener;
   }

   protected BaseRecyclerViewAdapter(@Nullable L listener) {
      this(new ArrayList<>(), listener);
   }

   @NonNull public List<M> getData() {return mData;}

   public M getItemData(int position) {return mData.get(position);}

   public int getItemPosition(M m) {return mData.indexOf(m);}

   @SuppressWarnings("unchecked") @NonNull @Override public VH onCreateViewHolder(@NonNull
                                                                                        ViewGroup
                                                                                        parent,
                                                                                  int viewType) {
      if (viewType == PROGRESS_TYPE) {
         addSpanLookup(parent);
         return (VH) new ProgressBarViewHolder(parent);
      } else {
         return genViewHolder(parent, viewType);
      }
   }

   protected abstract VH genViewHolder(ViewGroup parent, int viewType);

   @Override public void onBindViewHolder(@NonNull VH holder, int position, @NonNull List<Object>
         payloads) {
      if (holder instanceof ProgressBarViewHolder) {
         StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager
               .LayoutParams) holder.itemView.getLayoutParams();// TODO: 2018/6/4
         layoutParams.setFullSpan(true);
      } else if (getItemData(position) != null) {
         animate(holder, position);
         onBindView(holder, position);
         onShowGuide(holder, position);
      }
   }

   private void animate(VH holder, int position) {
      // TODO: 2018/6/4
      AnimHelper.startBeatsAnimation(holder.itemView);
   }

   protected abstract void onBindView(VH viewHolder, int position);

   @SuppressWarnings("unchecked")
   private void onShowGuide(@NonNull VH holder, int position) {// give the flexibility to other
      // adapters to override this
      if (position == 0 && !isShowGuide() && mGuideListener != null) {
         mGuideListener.onShowGuide(holder.itemView, getItemData(position));
         showGuide = true;
      }
   }

   @Override public int getItemViewType(int position) {
      if (getItemData(position) == null)
         return PROGRESS_TYPE;
      return super.getItemViewType(position);
   }

   @Override public int getItemCount() {
      return mData.size();
   }

   public void insertItems(@NonNull List<M> items) {
      mData.clear();
      mData.addAll(items);
      notifyDataSetChanged();
      progressAdded = false;
   }

   public void addItem(M item, int position) {
      mData.add(position, item);
      notifyItemInserted(position);
   }

   public void addItem(M item) {
      removeProgress();
      mData.add(item);
      if (mData.size() == 0) {
         notifyDataSetChanged();
      } else {
         notifyItemInserted(mData.size() - 1);
      }
   }

   private void removeProgress() {
      if (!isEmpty()) {
         M m = getItemData(getItemCount() - 1);
         if (m == null) removeItem(getItemCount() - 1);
         progressAdded = false;
      }
   }

   @SuppressWarnings("WeakerAccess") public void addItems(@NonNull List<M> items) {
      removeProgress();
      mData.addAll(items);
      notifyItemRangeInserted(getItemCount(), (getItemCount() + items.size()) - 1);
   }

   @SuppressWarnings("WeakerAccess") public void removeItem(int position) {
      mData.remove(position);
      notifyItemRemoved(position);
   }

   public void removeItem(M item) {
      int position = mData.indexOf(item);
      if (position != -1) removeItem(position);
   }

   public void removeItems(@NonNull List<M> items) {
      int prevSize = getItemCount();
      mData.removeAll(items);
      notifyItemRangeRemoved(prevSize, Math.abs(mData.size() - prevSize));
   }

   public void swapItem(@NonNull M model) {
      int index = getItemPosition(model);
      swapItem(model, index);
   }

   public void swapItem(@NonNull M model, int position) {
      if (position != -1) {
         mData.set(position, model);
         notifyItemChanged(position);
      }
   }

   public void subList(int fromPosition, int toPosition) {
      if (mData.isEmpty()) return;
      mData.subList(fromPosition, toPosition).clear();
      notifyItemRangeRemoved(fromPosition, toPosition);
   }

   public void clear() {
      progressAdded = false;
      mData.clear();
      notifyDataSetChanged();
   }



   public boolean isEmpty() {
      return mData.isEmpty();
   }

   private void addSpanLookup(ViewGroup parent) {
      if (parent instanceof RecyclerView) {
         if (((RecyclerView) parent).getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) ((RecyclerView) parent)
                  .getLayoutManager();// TODO: 2018/6/4
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {// TODO:
               // 2018/6/4
               @Override public int getSpanSize(int position) {
                  return getItemViewType(position) == PROGRESS_TYPE ? gridLayoutManager
                        .getSpanCount() : 1;
               }
            });
         }
      }
   }

   @SuppressWarnings("WeakerAccess") @Nullable public L getListener() {return mListener;}

   public interface GuideListener<M> {
      void onShowGuide(@NonNull View itemView, @NonNull M data);
   }
}
