package yincheng.gggithub.view.widget;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import yincheng.gggithub.library.recyclerview.BaseRecyclerAdapter;
import yincheng.gggithub.mvp.contract.SearchReposContract;


/**
 * Created by yincheng on 2018/6/4/14:05.
 * github:luoyincheng
 */
public abstract class InfiniteScroll extends RecyclerView.OnScrollListener {
   private int visibleThreshold = 3;// TODO: 2018/6/4 items in one row gridview ???
   private int currentPage = 0;
   private int previousTotalItemCount = 0;
   private boolean loading = true;
   private int startingPageIndex = 0;
   private RecyclerView.LayoutManager layoutManager;
   private BaseRecyclerAdapter adapter;
   private boolean newlyAdded = true;

   public InfiniteScroll() {
   }

   private void initLayoutManager(RecyclerView.LayoutManager layoutManager) {
      this.layoutManager = layoutManager;
      if (layoutManager instanceof GridLayoutManager)
         visibleThreshold = visibleThreshold * ((GridLayoutManager) layoutManager).getSpanCount()
               ;// TODO: 2018/6/4
      else if (layoutManager instanceof StaggeredGridLayoutManager)
         visibleThreshold = visibleThreshold * ((StaggeredGridLayoutManager) layoutManager)
               .getSpanCount();
   }

   private int getLastVisibleItem(int[] lastVisibleItemPositions) {
      int maxSize = 0;
      for (int i = 0; i < lastVisibleItemPositions.length; i++) {
         if (i == 0) maxSize = lastVisibleItemPositions[i];
         else if (lastVisibleItemPositions[i] > maxSize) maxSize = lastVisibleItemPositions[i];
      }
      return maxSize;
   }

   @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      if (newlyAdded) {
         newlyAdded = false;
         return;
      }
      // TODO: 2018/6/4
   }

   public void reset() {
      this.currentPage = this.startingPageIndex;
      this.previousTotalItemCount = 0;
      this.loading = true;
   }

   public void initialize(int currentPage, int previousTotalItemCount) {
      this.currentPage = currentPage;
      this.previousTotalItemCount = previousTotalItemCount;
      this.loading = true;
   }

//      public abstract boolean onLoadMore(String paramInPath, int page, int totalItemsCount);
   public abstract boolean onLoadMore(@Nullable SearchReposContract.SearchType searchType, int
         page, int totalItemsCount);
}
