package yincheng.gggithub.mvp.contract;

import yincheng.gggithub.mvp.contract.base.GGContract;
import yincheng.gggithub.view.android.SwipeRefreshLayout;

/**
 * Created by yincheng on 2018/6/14/18:08.
 * github:luoyincheng
 */
public interface PersonalContract {
   interface View extends GGContract.GGView, SwipeRefreshLayout.OnRefreshListener {
   }

   interface Presenter extends GGContract.GGPresenter {

   }
}
