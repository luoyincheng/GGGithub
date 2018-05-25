/*
 * Copyright (C) 2016 grandcentrix GmbH
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package yincheng.gggithub.mvparchitecture.rx2;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import yincheng.gggithub.mvparchitecture.TiLifecycleObserver;
import yincheng.gggithub.mvparchitecture.TiPresenter;
import yincheng.gggithub.mvparchitecture.TiView;

public class RxGGPresenterDisposableManager {

   //下面的两个CompositeDisposable对应着presenter为不同状态时的情况
   private CompositeDisposable mPresenterDisposables = new CompositeDisposable();

   private CompositeDisposable mUiDisposables;

   public RxGGPresenterDisposableManager(final TiPresenter presenter) {
      presenter.addLifecycleObserver(new TiLifecycleObserver() {// TODO: 2018/5/24 ，具体的回调流程
         @Override
         public void onChange(final TiPresenter.State state,
                              final boolean hasLifecycleMethodBeenCalled) {
            /**
             *在TiPresenter的状态改变过程中，每一次状态的改变都会调用两次
             * {@link TiPresenter#moveToState(TiPresenter.State, boolean)}方法，第一次调用时，
             * hasLifecycleMethodBeenCalled为false，第二次为true,因此，这里会在第一次调用完moveToState()
             * 以后回调
             */
            if (state == TiPresenter.State.VIEW_DETACHED && !hasLifecycleMethodBeenCalled) {
               // dispose all UI disposable created in onAttachView(TiView) and added
               // via addViewDisposable(Disposable...)
               if (mUiDisposables != null) {
                  mUiDisposables.dispose();
                  mUiDisposables = null;
               }
            }

            if (state == TiPresenter.State.VIEW_ATTACHED && !hasLifecycleMethodBeenCalled) {
               mUiDisposables = new CompositeDisposable();
            }

            if (state == TiPresenter.State.DESTROYED && !hasLifecycleMethodBeenCalled) {
               mPresenterDisposables.dispose();
               mPresenterDisposables = null;
            }
         }
      });
   }

   /**
    * Add your disposable(一次性的(用品)，可任意处理的，用完即可丢弃的) here and they will automatically
    * disposed(处理) when {@link TiPresenter#destroy()} gets called
    *
    * @throws IllegalStateException when the presenter has reached
    *                               {@link TiPresenter.State#DESTROYED}
    */
   public Disposable addDisposable(@NonNull final Disposable disposable) {
      if (mPresenterDisposables == null) {
         throw new IllegalStateException("disposable handling doesn't work"
               + " when the presenter has reached the DESTROYED state");
      }

      mPresenterDisposables.add(disposable);
      return disposable;
   }


   /**
    * Add your disposables here and they will automatically disposed when
    * {@link TiPresenter#destroy()} gets called
    *
    * @throws IllegalStateException when the presenter has reached
    *                               {@link TiPresenter.State#DESTROYED}
    * @see #addDisposable(Disposable)
    */
   public void addDisposables(@NonNull final Disposable... disposable) {
      for (int i = 0; i < disposable.length; i++) {
         addDisposable(disposable[i]);
      }
   }

   /**
    * Add your disposable for View events to this method to get them automatically cleaned up
    * in {@link TiPresenter#detachView()}. typically call this in
    * {@link TiPresenter#attachView(TiView)} where you dispose to the UI events.
    *
    * @throws IllegalStateException when no view is attached
    */
   public Disposable addViewDisposable(@NonNull final Disposable disposable) {
      if (mUiDisposables == null) {
         throw new IllegalStateException("view disposable can't be handled"
               + " when there is no view");
      }

      mUiDisposables.add(disposable);
      return disposable;
   }

   /**
    * Add your disposables for View events to this method to get them automatically cleaned up
    * in {@link TiPresenter#detachView()}. typically call this in
    * {@link TiPresenter#attachView(TiView)} where you dispose to the UI events.
    *
    * @throws IllegalStateException when no view is attached
    */
   public void addViewDisposables(@NonNull final Disposable... disposables) {
      for (int i = 0; i < disposables.length; i++) {
         addViewDisposable(disposables[i]);
      }
   }

}
