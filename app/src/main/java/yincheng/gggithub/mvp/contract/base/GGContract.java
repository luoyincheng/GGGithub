package yincheng.gggithub.mvp.contract.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import yincheng.gggithub.mvparchitecture.TiView;
import yincheng.gggithub.mvparchitecture.callonmainthread.CallOnMainThread;

/**
 * Created by yincheng on 2018/5/24/10:23.
 * github:luoyincheng
 */
public interface GGContract {

   interface GGView extends TiView {
      @CallOnMainThread void showProgressView();

      @CallOnMainThread void hideProgressView();

      @CallOnMainThread void showBlockingProgressView(@StringRes int resId);

      @CallOnMainThread void showMessage(@StringRes int titleRes, @StringRes int stringRes);
   }

   interface GGPresenter {
      void onSaveInstanceState(Bundle outBundle);

      void onRestoreInstanceState(Bundle outBundle);

      void addDisposable(@Nullable Disposable... disposables);

      void addViewDisposable(@Nullable Disposable... disposables);

      <T> void addObservable(@Nullable Observable<T> observable);

      <T> void makeRestCall(@NonNull Observable<T> observable, @NonNull Consumer<T> onNext);

      <T> void makeRestCall(@NonNull Observable<T> observable, @NonNull Consumer<T> onNext,
                            boolean cancelable);

      void onSubscribed(boolean cancelable);

      void onError(@NonNull Throwable throwable);

   }

   interface paginationListener<P> {
      int getCurrentPage();

      int getPreviousTotal();

      void setCurrentPage(int currentPage);

      void setPreviousTotal(int previousTotal);

      boolean onCallApi(String paramInPath, int page, @Nullable P parameter);
   }

   interface onScrollTopListener {
      void onScrollToTop(int index);
   }
}
