package yincheng.gggithub.mvp.presenter;

import android.os.Bundle;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:05:05 17:59
 * Github : yincheng.luo
 */
public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
   private CompositeDisposable compositeDisposable;
   private T contractView;

   public T getContractView() {
      return contractView;
   }

   public void addDisposable(Disposable disposable) {
      compositeDisposable.add(disposable);
   }

   @Override public void attachView(T view) {
      contractView = view;
      compositeDisposable = new CompositeDisposable();
   }

   @Override public void detachView() {
      this.contractView = null;
      if (compositeDisposable != null)
         compositeDisposable.clear();
   }

   @Override public void onSaveInstanceState(Bundle outState) {

   }

   @Override public void onRestoreInstanceState(Bundle inState) {

   }
}
