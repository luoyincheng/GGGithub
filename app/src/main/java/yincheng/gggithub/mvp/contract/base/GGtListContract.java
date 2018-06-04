package yincheng.gggithub.mvp.contract.base;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by yincheng on 2018/6/4/17:06.
 * github:luoyincheng
 */
public interface GGtListContract {
   interface View extends GGContract.GGView {
   }

   interface Presenter<M> extends GGContract.GGPresenter {
      @NonNull ArrayList<M> getListData();
   }
}
