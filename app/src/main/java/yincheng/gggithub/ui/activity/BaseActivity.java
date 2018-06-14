package yincheng.gggithub.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import yincheng.gggithub.R;
import yincheng.gggithub.mvp.contract.base.GGContract;
import yincheng.gggithub.mvp.presenter.RxPresenter;
import yincheng.gggithub.mvparchitecture.TiActivity;
import yincheng.gggithub.ui.dialog.BuilderDialog;
import yincheng.gggithub.ui.dialogfragment.DialogFragmentLogin;

/**
 * Created by yincheng on 2018/5/25/14:00.
 * github:luoyincheng
 */
public abstract class BaseActivity<V extends GGContract.GGView, P extends RxPresenter<V>> extends
      TiActivity<P, V> implements GGContract.GGView {
   private Unbinder unbinder;
   private DialogFragmentLogin dialogFragmentLogin;
   private BuilderDialog progressDialog;

   @Override protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      if (getLayoutId() != 0) {
         setContentView(getLayoutId());
         unbinder = ButterKnife.bind(this);
      }
      initData();
      initView();
   }

   protected abstract void initData();

   protected abstract void initView();

   @Override protected void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);
      getPresenter().onSaveInstanceState(outState);
   }

   @Override protected void onDestroy() {
      super.onDestroy();
      if (progressDialog != null) {
         progressDialog.cancel();
         progressDialog = null;
      }
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

   /**
    * 为所有继承自BaseActivity的Activity提供显示进度条的显示和隐藏的默认实现
    */
   @Override public void showProgressView() {
      if (progressDialog == null)
         progressDialog = new BuilderDialog
               .DialogBuilder(this)
               .withLayout(R.layout.dialog_progress)
               .withStyle(R.style.progress_dialog)
               .Build();
      progressDialog.show();
   }

   @Override public void hideProgressView() {
      if (progressDialog != null)
         progressDialog.cancel();
   }
}
