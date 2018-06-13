package yincheng.gggithub.ui.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;
import yincheng.gggithub.R;
import yincheng.gggithub.helper.KeyboardHelper;
import yincheng.gggithub.library.recyclerview.DynamicRecyclerView;
import yincheng.gggithub.mvp.contract.SearchReposContract;
import yincheng.gggithub.mvp.model.Repo;
import yincheng.gggithub.mvp.presenter.SearchReposPresenter;
import yincheng.gggithub.provider.adapter.ReposAdapter;
import yincheng.gggithub.provider.annotation.Recite;
import yincheng.gggithub.provider.rest.OnLoadMore;
import yincheng.gggithub.view.widget.BossProgress;
import yincheng.gggithub.view.widget.FilterRadioButton;
import yincheng.gggithub.view.widget.FontAutoCompleteEditText;
import yincheng.gggithub.view.widget.TagGroup;

/**
 * Created by yincheng on 2018/6/4/16:31.
 * github:luoyincheng
 */
public class SearchActivity extends
      BaseActivity<SearchReposContract.View, SearchReposPresenter> implements
      SearchReposContract.View, View.OnKeyListener {
   @BindView(android.R.id.content) View contentView;
   @BindView(R.id.et_search) FontAutoCompleteEditText editText;
   @BindView(R.id.iv_clean) ImageView ivClean;
   @BindView(R.id.iv_search) ImageView ivSearch;
   @BindView(R.id.tag_hotkey) TagGroup tagGroup;
   @BindView(R.id.rv_search) DynamicRecyclerView recyclerView;
   @BindView(R.id.bossProgress) BossProgress bossProgress;
   @BindView(R.id.filter_view) LinearLayout filterView;
   @BindView(R.id.search_type) RadioGroup search_type;
   //   @BindViews({R.id.filter_repository, R.id.filter_user, R.id.filter_issue, R.id.filter_code})
//   List<FilterRadioButton> filterRadioButtons;
   private OnLoadMore<String> onLoadMore;
   private boolean[] filterResults;
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

   @Override public void onShowFilter() {
      Toast.makeText(this, "onShowFilter:", Toast.LENGTH_SHORT).show();
      filterView.setVisibility(View.VISIBLE);
      KeyboardHelper.toggleSoftInput();
   }

   @Override public void onRefresh() {

   }

   @Override public void onClick(View v) {
      Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
   }

   @Recite @Override protected void initData() {
//      filterResults = new boolean[filterRadioButtons.size()];
      adapter = new ReposAdapter(getPresenter().getRepos(), true, true);
      editText.setOnFocusChangeListener(getPresenter());
//      editText.setOnKeyListener(getPresenter());
      editText.setOnKeyListener(this);
//      adapter.setListener(getPresenter());
   }


   @Override protected void initView() {
//      contentView = findViewById(android.R.id.content);
      tagGroup.setTags(
            "dfh"
      );
      recyclerView.setAdapter(adapter);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      if (editText.isFocused())
         Toast.makeText(this, "输入框正在持有焦点", Toast.LENGTH_SHORT).show();
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
      ivClean.setVisibility(s.toString().length() > 0 ? View.VISIBLE : View.GONE);
   }

   /**
    * 将控件直接传送过去在presenter中就可以操作界面
    */
   @OnClick(R.id.iv_search)
   public void onSearch() {
      getPresenter().onSearch(editText, tagGroup, getSearchType());
   }

   private String getSearchType() {
//      return (String) ((FilterRadioButton) search_type.getChildAt
//            (search_type.getCheckedRadioButtonId())).getText();
      Toast.makeText(this, search_type.getCheckedRadioButtonId() + "", Toast.LENGTH_SHORT).show();
//      ((FilterRadioButton) search_type.getChildAt(1)).setChecked(true);
      return "fsdaf";
   }

   @OnClick(R.id.iv_clean)
   public void onClean() {
      editText.setText("");
   }

   @Recite @OnEditorAction(R.id.et_search) boolean onEditor() {
      onSearch();
      Toast.makeText(this, "0.将toolbar中坐边的图标在“x”和“+”之间转换.\n" +
            "1.在设置中可以控制默认的搜索结果排序.\n2.将star数量和fork数量用rxjava2的zip" +
            "方法整合为一个数.\n3.在主界面添加以为taggroup形式显示的过滤.\n4.将toolbar中向右的箭头改为中间是一个“搜”字的button" +
            ".\n5.filterradiobutton中的文字没有居中", Toast
            .LENGTH_SHORT).show();
      return true;
   }

   @Override public boolean onKey(View v, int keyCode, KeyEvent event) {
      Toast.makeText(this, "keycode:" + keyCode, Toast.LENGTH_SHORT).show();
      return false;
   }
}
