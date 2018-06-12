package yincheng.gggithub.ui;

import android.support.annotation.NonNull;

import yincheng.gggithub.mvp.contract.base.GGContract;
import yincheng.gggithub.mvp.presenter.BasePresenter;
import yincheng.gggithub.mvparchitecture.TiFragment;

/**
 * Created by yincheng on 2018/6/4/10:37.
 * github:luoyincheng
 */
public class BaseFragment<V extends GGContract.GGView, P extends BasePresenter<V>> extends
      TiFragment<P, V> implements GGContract.GGView {
   @Override public void showProgressView() {

   }

   @Override public void hideProgressView() {

   }

   @Override public void showBlockingProgressView(int resId) {

   }

   @Override public void showMessage(int titleRes, int stringRes) {

   }

   @NonNull @Override public P providePresenter() {
      return null;
   }
}
