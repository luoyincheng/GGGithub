package yincheng.gggithub.mvp.presenter;

import android.os.Bundle;

import yincheng.gggithub.provider.annotation.CallOnMainThread;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:05:05 16:45
 * Github : yincheng.luo
 */
public interface BaseContract {

   interface BaseView {
      @CallOnMainThread void showLoading();

      @CallOnMainThread void hideLoading();

      void onLoginRequired();

      void onSuccess();

      void onFail();
   }

   interface BasePresenter<V> {
      void attachView(V view);

      void detachView();

      void onSaveInstanceState(Bundle outState);

      void onRestoreInstanceState(Bundle inState);

   }
}
