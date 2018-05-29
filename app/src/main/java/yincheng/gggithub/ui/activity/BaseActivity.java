package yincheng.gggithub.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import yincheng.gggithub.mvp.contract.GGContract;
import yincheng.gggithub.mvp.presenter.BasePresenter;
import yincheng.gggithub.mvparchitecture.TiActivity;
import yincheng.gggithub.ui.dialogfragment.DialogFragmentLogin;

/**
 * Created by yincheng on 2018/5/25/14:00.
 * github:luoyincheng
 */
public abstract class BaseActivity<V extends GGContract.GGView, P extends BasePresenter<V>> extends
      TiActivity<P, V> implements GGContract.GGView {
   private Unbinder unbinder;
   private DialogFragmentLogin dialogFragmentLogin;

   @Override protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      if (getLayoutId() != 0) {
         setContentView(getLayoutId());
         unbinder = ButterKnife.bind(this);
      }
      initView();
   }

   protected abstract void initView();

   @Override protected void onDestroy() {
      super.onDestroy();
      unbinder.unbind();
   }

   @LayoutRes protected abstract int getLayoutId();

//   private boolean isAuthorized() {
//
//   }

   protected void showLoginDialog() {
      if (dialogFragmentLogin == null)
         dialogFragmentLogin = new DialogFragmentLogin();
      if (dialogFragmentLogin.getDialog() == null)
         new DialogFragmentLogin().show(getSupportFragmentManager(), "BaseActivity");
      else dialogFragmentLogin.getDialog().show();

   }
}
