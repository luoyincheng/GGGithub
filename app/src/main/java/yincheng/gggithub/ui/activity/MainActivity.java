package yincheng.gggithub.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import yincheng.gggithub.R;
import yincheng.gggithub.mvp.contract.MainContract;
import yincheng.gggithub.mvp.presenter.MainPresenter;

/**
 * Created by yincheng on 2018/5/25/14:08.
 * github:luoyincheng
 */
public class MainActivity extends BaseActivity<MainContract.View, MainPresenter> implements
      MainContract.View {

   @Override protected int getLayoutId() {
      return R.layout.activity_main;
   }

   @Override public void showProgressView(int resId) {

   }

   @Override public void hideProgressView() {

   }

   @Override public void showBlockingProgressView(int resId) {

   }

   @Override public void showMessage(int titleRes, int stringRes) {

   }

   @NonNull @Override public MainPresenter providePresenter() {
      return new MainPresenter();
   }

   @OnClick({R.id.fab})
   public void onViewClick(View view) {
      switch (view.getId()) {
         case R.id.fab:
            showLoginDialog();
            break;
      }
   }
}
