package yincheng.gggithub.mvp.contract;

import yincheng.gggithub.mvp.presenter.BaseContract;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:05:05 20:10
 * Github : yincheng.luo
 */
public interface LoginContract {
   interface View extends BaseContract.BaseView {

   }

   interface Presenter extends BaseContract.BasePresenter<View> {
      void login(String username, String password);

      void signOut();
   }
}
