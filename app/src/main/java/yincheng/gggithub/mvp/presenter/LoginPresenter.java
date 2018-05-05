package yincheng.gggithub.mvp.presenter;

import yincheng.gggithub.mvp.contract.LoginContract;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:05:05 17:59
 * Github : yincheng.luo
 */
public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

   @Override public void login(String username, String password) {
      getContractView().showLoading();
//      addDisposable();

   }

   @Override public void signOut() {

   }
}
