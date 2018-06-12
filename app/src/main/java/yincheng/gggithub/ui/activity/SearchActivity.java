package yincheng.gggithub.ui.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import yincheng.gggithub.R;
import yincheng.gggithub.library.recyclerview.DynamicRecyclerView;
import yincheng.gggithub.mvp.contract.SearchReposContract;
import yincheng.gggithub.mvp.model.Repo;
import yincheng.gggithub.mvp.presenter.SearchReposPresenter;
import yincheng.gggithub.provider.adapter.ReposAdapter;
import yincheng.gggithub.provider.rest.OnLoadMore;
import yincheng.gggithub.view.widget.BossProgress;
import yincheng.gggithub.view.widget.FontAutoCompleteEditText;
import yincheng.gggithub.view.widget.TagGroup;

/**
 * Created by yincheng on 2018/6/4/16:31.
 * github:luoyincheng
 */
public class SearchActivity extends
      BaseActivity<SearchReposContract.View, SearchReposPresenter> implements
      SearchReposContract.View {
   @BindView(R.id.et_search) FontAutoCompleteEditText editText;
   @BindView(R.id.iv_search) ImageView ivSearch;
   @BindView(R.id.tag_hotkey) TagGroup tagGroup;
   @BindView(R.id.rv_search) DynamicRecyclerView recyclerView;
   @BindView(R.id.bossProgress) BossProgress bossProgress;
   private OnLoadMore<String> onLoadMore;
   private String searchKey = "";
   private ReposAdapter adapter;

   @Override public void onNotifyAdapter(@Nullable List<Repo> items, int page) {
      hideProgressView();
      if (items == null || items.isEmpty()) {
         adapter.clear();
         return;
      }
      if (page <= 1) {
         adapter.insertItems(items);
      } else {
         adapter.addItems(items);
      }
   }

   @Override public void onSetTabConut(int count) {

   }

   @Override public void onSetSearchQuery(@NonNull String query) {

   }

   @NonNull @Override public OnLoadMore<String> getLoadMoreClass() {
      if (onLoadMore == null)
         onLoadMore = new OnLoadMore<>(getPresenter(), searchKey);
      onLoadMore.setParameter(searchKey);
      return onLoadMore;
   }

   @Override public void onRefresh() {

   }

   @Override public void onClick(View v) {
      Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
   }

   @Override protected void initData() {
      adapter = new ReposAdapter(getPresenter().getRepos(), true, true);
//      adapter.setListener(getPresenter());
   }


   @Override protected void initView() {
      tagGroup.setTags(
            "adfa",
            "bdfadfw",
            "casdf",
            "dadf",
            "asdasdfsfasdfe",
            "fasafsfasdfasasdfasfas",
            "gwfdf",
            "dfh"
      );
      recyclerView.setAdapter(adapter);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
   }

   @Override protected int getLayoutId() {
      return R.layout.activity_search;
   }

   @Override public void showBlockingProgressView(int resId) {

   }

   @Override public void showMessage(int titleRes, int stringRes) {

   }

   @NonNull @Override public SearchReposPresenter providePresenter() {
      Logger.i("MVP");
      return new SearchReposPresenter();
   }

   @OnTextChanged(value = R.id.et_search, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
   void onViewTextChanged(Editable s) {
   }

   /**
    * 将控件直接传送过去在presenter中就可以操作界面
    */
   @OnClick(R.id.iv_search)
   public void onSearchClicked(View view) {
      getPresenter().onSearch(editText, tagGroup);
   }
}
