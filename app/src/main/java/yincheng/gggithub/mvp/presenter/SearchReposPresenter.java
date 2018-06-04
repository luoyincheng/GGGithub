package yincheng.gggithub.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.AutoCompleteTextView;

import com.annimon.stream.internal.Params;

import java.util.ArrayList;
import java.util.List;

import yincheng.gggithub.library.viewpager.ViewPager;
import yincheng.gggithub.mvp.contract.SearchContract;
import yincheng.gggithub.mvp.contract.SearchReposContract;
import yincheng.gggithub.mvp.model.Repo;
import yincheng.gggithub.mvparchitecture.ViewAction;

/**
 * Created by yincheng on 2018/6/4/17:13.
 * github:luoyincheng
 */
class SearchReposPresenter extends BasePresenter<SearchReposContract.View> implements
      SearchReposContract.Presenter {
   private ArrayList<Repo> repos = new ArrayList<>();
   private int page;
   private int previousTotalItems;
   private int lastPage = Integer.MAX_VALUE;

   @NonNull @Override public ArrayList<Repo> getRepos() {
      return repos;
   }

   //this is no generated from "getter and setter",this is from Presenter
   @Override public int getCurrentPage() {
      return page;
   }

   //this is no generated from "getter and setter",this is from Presenter
   @Override public int getPreviousTotal() {
      return previousTotalItems;
   }

   //this is no generated from "getter and setter",this is from Presenter
   @Override public void setCurrentPage(int currentPage) {
      this.page = currentPage;
   }

   //this is no generated from "getter and setter",this is from Presenter
   @Override public void setPreviousTotal(int previousTotal) {
      this.previousTotalItems = previousTotal;
   }

   //real call
   @Override public boolean onCallApi(int page, @Nullable String parameter) {
      if (page == 1) {
         lastPage = Integer.MAX_VALUE;
         sendToView(new ViewAction<SearchReposContract.View>() {
            @Override public void call(SearchReposContract.View view) {
               view.getLoadMoreClass().reset();
            }
         });
      }
      setCurrentPage(page);
      if (page > lastPage || lastPage == 0 || parameter == null) {
         sendToView(new ViewAction<SearchReposContract.View>() {
            @Override public void call(SearchReposContract.View view) {
               view.hideProgressView();
            }
         });
         return false;
      }
//      makeRestCall(ResePro);
      return false;
   }
}
