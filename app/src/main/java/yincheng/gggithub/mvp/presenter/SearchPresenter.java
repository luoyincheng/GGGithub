package yincheng.gggithub.mvp.presenter;

import android.support.annotation.NonNull;
import android.widget.AutoCompleteTextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import yincheng.gggithub.library.viewpager.ViewPager;
import yincheng.gggithub.mvp.contract.SearchContract;
import yincheng.gggithub.mvp.model.SearchedResult;

/**
 * Created by yincheng on 2018/6/4/16:37.
 * github:luoyincheng
 */
public class SearchPresenter extends BasePresenter<SearchContract.View> implements
      SearchContract.Presenter {
   private ArrayList<SearchedResult> results = new ArrayList<>();

   @Override protected void onAttachView(@NonNull SearchContract.View view) {
      super.onAttachView(view);// TODO: 2018/6/4 when did it called first
      if (results.isEmpty()) {
      }
      Logger.e("SearchPresenter.onAttachView()");
   }

   @NonNull @Override public ArrayList<SearchedResult> getResults() {
      return null;
   }

   @Override public void onSearchClicked(@NonNull ViewPager viewPager, @NonNull
         AutoCompleteTextView ediText) {

   }
}
