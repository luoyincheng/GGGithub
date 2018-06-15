package yincheng.gggithub.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import yincheng.gggithub.mvp.contract.base.GGContract;
import yincheng.gggithub.mvp.presenter.RxPresenter;
import yincheng.gggithub.mvparchitecture.TiFragment;

/**
 * Created by yincheng on 2018/6/4/10:37.
 * github:luoyincheng
 */
public abstract class BaseFragment<V extends GGContract.GGView, P extends RxPresenter<V>> extends
      TiFragment<P, V> implements GGContract.GGView {

   private Unbinder unbinder;

   @Override public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
   }

   @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
         container, @Nullable Bundle savedInstanceState) {
      if (getLayoutId() != 0) {
         View view = inflater.inflate(getLayoutId(), container, false);
         unbinder = ButterKnife.bind(this, view);
         return view;
      }
      initData();
      return super.onCreateView(inflater, container, savedInstanceState);
   }

   protected abstract void initData();

   public abstract int getLayoutId();

   @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
   }

   @Override public void showProgressView() {

   }

   @Override public void hideProgressView() {

   }

   @Override public void showBlockingProgressView(int resId) {

   }

   @Override public void showMessage(int titleRes, int stringRes) {

   }

}
