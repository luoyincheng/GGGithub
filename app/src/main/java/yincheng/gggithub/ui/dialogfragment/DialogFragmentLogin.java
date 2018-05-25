package yincheng.gggithub.ui.dialogfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.accessibility.AccessibilityEventSource;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import yincheng.gggithub.R;
import yincheng.gggithub.mvp.contract.LoginContract;
import yincheng.gggithub.mvp.presenter.LoginPresenter;

/**
 * Created by yincheng on 2018/5/25/12:08.
 * github:luoyincheng
 */
public class DialogFragmentLogin extends
      BaseDialogFragment<LoginContract.View, LoginPresenter> implements LoginContract.View {
   @BindView(R.id.et_username) TextInputEditText username;
   @BindView(R.id.et_password) TextInputEditText password;

   @Override public void onEmptyUserName(boolean isEmpty) {
      username.setError(isEmpty ? getString(R.string.required_field) : null);
   }

   @Override public void onEmptyPassword(boolean isEmpty) {
      password.setError(isEmpty ? getString(R.string.required_field) : null);
   }

   @Override public void onLoggedInSuccessfully() {

   }

   @Override protected int getLayoutId() {
      return R.layout.dialogfragment_login;
   }

   @Override protected void onDialogFragmentCreated(AccessibilityEventSource view, Bundle
         savedInstanceState) {
   }

   @NonNull @Override public LoginPresenter providePresenter() {
      return new LoginPresenter();
   }

   @OnClick(R.id.fab_login) public void onLogin() {
      realLogin();
   }

   private void realLogin() {
      getPresenter().login(username.getText().toString(),
            password.getText().toString(),
            "",
            true,
            "");
   }

   @Override public void showMessage(int titleRes, int stringRes) {
      Toast.makeText(this.getActivity(), getResources().getString(titleRes), Toast.LENGTH_SHORT).show();
   }
}
