package yincheng.gggithub.provider.rest;

import android.support.annotation.Nullable;

import yincheng.gggithub.mvp.contract.base.GGContract;
import yincheng.gggithub.view.widget.InfiniteScroll;

/**
 * Created by yincheng on 2018/6/4/16:00.
 * github:luoyincheng
 */
public class OnLoadMore<P> extends InfiniteScroll {

   private GGContract.paginationListener<P> presenter;
   @Nullable private P parameter;

   public OnLoadMore(GGContract.paginationListener<P> presenter) {
      this(presenter, null);
   }

   public OnLoadMore(GGContract.paginationListener<P> presenter, @Nullable P parameter) {
      this.presenter = presenter;
      this.parameter = parameter;
   }

   @Nullable public P getParameter() {
      return parameter;
   }

   public void setParameter(@Nullable P parameter) {
      this.parameter = parameter;
   }

   @Override public boolean onLoadMore(String paramInPath, int page, int totalItemsCount) {
      if (presenter != null) {
         presenter.setPreviousTotal(totalItemsCount);
         return presenter.onCallApi(paramInPath, page + 1, parameter);
      }
      return false;
   }
}
