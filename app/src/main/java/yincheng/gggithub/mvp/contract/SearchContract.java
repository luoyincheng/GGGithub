package yincheng.gggithub.mvp.contract;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

import yincheng.gggithub.library.viewpager.ViewPager;
import yincheng.gggithub.mvp.contract.base.GGContract;
import yincheng.gggithub.mvp.model.Repo;
import yincheng.gggithub.mvp.model.SearchedResult;

/**
 * Created by yincheng on 2018/6/4/16:32.
 * github:luoyincheng
 */
public interface SearchContract {
   interface View extends GGContract.GGView {

      void onNotifyAdapter(@Nullable Repo repo);

      void onSetCount(int Count, @IntRange(from = 0, to = 3) int index);// TODO: 2018/6/4 to un
   }

   interface Presenter extends GGContract.GGPresenter {

      @NonNull ArrayList<SearchedResult> getResults();

      void onSearchClicked(@NonNull ViewPager viewPager, @NonNull AutoCompleteTextView ediText);
   }
}
