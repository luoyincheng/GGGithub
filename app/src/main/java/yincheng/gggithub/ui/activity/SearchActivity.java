package yincheng.gggithub.ui.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnTextChanged;
import yincheng.gggithub.R;
import yincheng.gggithub.mvp.contract.SearchReposContract;
import yincheng.gggithub.mvp.model.Repo;
import yincheng.gggithub.mvp.presenter.SearchReposPresenter;
import yincheng.gggithub.provider.rest.OnLoadMore;

/**
 * Created by yincheng on 2018/6/4/16:31.
 * github:luoyincheng
 */
public class SearchActivity extends
      BaseActivity<SearchReposContract.View, SearchReposPresenter> implements
      SearchReposContract.View {
   @BindView(R.id.sv_search) SearchView mSearchView;

   @Override public void onNotifyAdapter(@Nullable List<Repo> items, int page) {

   }

   @Override public void onSetTabConut(int count) {

   }

   @Override public void onSetSearchQuery(@NonNull String query) {

   }

   @NonNull @Override public OnLoadMore<String> getLoadMoreClass() {
      return null;
   }

   @Override public void onRefresh() {

   }

   @Override public void onClick(View v) {

   }

   @Override protected void initView() {

   }

   @Override protected int getLayoutId() {
      return R.layout.activity_search;
   }

   @Override public void showProgressView(int resId) {

   }

   @Override public void hideProgressView() {

   }

   @Override public void showBlockingProgressView(int resId) {

   }

   @Override public void showMessage(int titleRes, int stringRes) {

   }

   @NonNull @Override public SearchReposPresenter providePresenter() {
      Logger.i("MVP");
      return new SearchReposPresenter();
   }

//   @OnTextChanged(value = R.id.sv_search, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
//   void onViewTextChanged(Editable s) {
//      Toast.makeText(this, "文字改变了", Toast.LENGTH_SHORT).show();
//   }
}
