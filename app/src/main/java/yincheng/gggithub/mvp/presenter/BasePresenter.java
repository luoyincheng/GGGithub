package yincheng.gggithub.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.function.Consumer;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import rx.Single;
import yincheng.gggithub.helper.RxHelper;
import yincheng.gggithub.mvp.contract.GGContract;
import yincheng.gggithub.mvparchitecture.TiPresenter;
import yincheng.gggithub.mvparchitecture.rx2.RxGGPresenterDisposableManager;

/**
 * Created by yincheng on 2018/5/24/10:23.
 * github:luoyincheng
 */
public class BasePresenter<V extends GGContract.GGView> extends TiPresenter<V> implements
      GGContract.GGPresenter {
   private boolean apiCalled;

   //用来根据presenter的状态来管理CompositeDisposable
   private final RxGGPresenterDisposableManager disposableManager
         = new RxGGPresenterDisposableManager(this);

   @Override public void onSaveInstanceState(Bundle outBundle) {

   }

   @Override public void onRestoreInstanceState(Bundle outBundle) {

   }

   //只有添加Disposable，因为移除Disposable是在state变为DESTROY时自动调用的
   @Override public void addDisposable(@Nullable Disposable... disposables) {
      if (disposableManager != null) disposableManager.addDisposables(disposables);

   }

   @Override public void addViewDisposable(@Nullable final Disposable... disposables) {
      if (disposables != null) {
         if (isViewAttached()) disposableManager.addViewDisposables(disposables);
         else sendToView(A -> addViewDisposable(disposables));// TODO: 2018/5/24 lamda表达式
      }
   }

   @Override public <T> void addObservable(@Nullable Observable<T> observable) {

   }

   @Override public <T> void makeRestCall(@NonNull Observable<T> observable, @NonNull io
         .reactivex.functions.Consumer<T> onNext) {
      makeRestCall(observable, onNext, true);
   }

   @Override public <T> void makeRestCall(@NonNull Observable<T> observable, @NonNull io
         .reactivex.functions.Consumer<T> onNext, boolean cancelable) {
      addDisposable(RxHelper.getObservable(observable)
            .doOnSubscribe(disposable -> onSubscribed(cancelable))// TODO: 2018/5/25
            .subscribe(onNext, this::onError, () -> apiCalled = true)// TODO: 2018/5/25
      );
   }

   @Override public void onSubscribed(boolean cancelable) {

   }

   @Override public void onError(@NonNull Throwable throwable) {

   }

}
