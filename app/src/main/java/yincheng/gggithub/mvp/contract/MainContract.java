package yincheng.gggithub.mvp.contract;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import yincheng.gggithub.library.bottomnavigation.BottomNavigationBar;
import yincheng.gggithub.mvp.contract.base.GGContract;

/**
 * Created by yincheng on 2018/5/25/14:09.
 * github:luoyincheng
 */
public interface MainContract {
   int CATEGORY = 0;
   int STARRED = 1;
   int TRENDING = 2;
   int PERSONAL = 3;

   @IntDef({
         CATEGORY,
         STARRED,
         TRENDING,
         PERSONAL
   })

   @Retention(RetentionPolicy.SOURCE) @interface NavigationType {}

   /**
    * // TODO: 2018/6/12 这里厉害了
    */
   interface View extends GGContract.GGView {
      void onNavigationSelected(@NavigationType int navigationType);
   }

   interface Presenter extends GGContract.GGPresenter, BottomNavigationBar.OnTabSelectedListener {

   }
}
