package yincheng.gggithub.mvp.contract;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import yincheng.gggithub.mvp.contract.base.GGContract;
import yincheng.gggithub.mvp.model.Repo;
import yincheng.gggithub.provider.rest.OnLoadMore;
import yincheng.gggithub.view.android.SwipeRefreshLayout;
import yincheng.gggithub.view.widget.FontAutoCompleteEditText;
import yincheng.gggithub.view.widget.TagGroup;

/**
 * Created by yincheng on 2018/6/4/17:00.
 * github:luoyincheng
 */
public interface SearchReposContract {
   interface View extends GGContract.GGView, SwipeRefreshLayout.OnRefreshListener,
         android.view.View.OnClickListener {

      void onNotifyAdapter(@Nullable List<Repo> items, int page);

      void onSetTabConut(int count);

      void onSetSearchQuery(@NonNull String query);

      @NonNull OnLoadMore<String> getLoadMoreClass();

      void onToggleFilter(boolean isShowFilter);
   }

   interface Presenter extends GGContract.GGPresenter, GGContract.paginationListener<String>,
         android.view.View.OnFocusChangeListener {
      @NonNull ArrayList<Repo> getRepos();

      void onSearch(@NonNull FontAutoCompleteEditText editText, TagGroup tagGroup, String
            searchType);

   }
}
