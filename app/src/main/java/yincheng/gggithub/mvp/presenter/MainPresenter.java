package yincheng.gggithub.mvp.presenter;


import yincheng.gggithub.mvp.contract.MainContract;
import yincheng.gggithub.mvparchitecture.ViewAction;

/**
 * Created by yincheng on 2018/5/25/14:09.
 * github:luoyincheng
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements
      MainContract.Presenter {

   @Override public void onTabSelected(int position) {
      sendToView(new ViewAction<MainContract.View>() {
         @Override public void call(MainContract.View view) {
            view.onNavigationSelected(position);
         }
      });
   }

   @Override public void onTabUnselected(int position) {

   }

   @Override public void onTabReselected(int position) {

   }
}
