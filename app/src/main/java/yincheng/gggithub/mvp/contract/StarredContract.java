package yincheng.gggithub.mvp.contract;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import yincheng.gggithub.mvp.contract.base.GGContract;
import yincheng.gggithub.mvp.model.FeedsModel;
import yincheng.gggithub.provider.rest.OnLoadMore;

/**
 * Created by yincheng on 2018/6/1/18:38.
 * github:luoyincheng
 */
public interface StarredContract {
   interface View extends GGContract.GGView {
      void onNotifyAdapter(@Nullable List<FeedsModel> feeds, int page);

      @NonNull OnLoadMore<String> getLoadMoreClass();
   }

   interface Presenter extends GGContract.GGPresenter,
         GGContract.paginationListener {
      void onFragmentCreated(@NonNull Bundle argument);

      boolean onCallApi(int page);

      void onWorkOffline();
   }
}
