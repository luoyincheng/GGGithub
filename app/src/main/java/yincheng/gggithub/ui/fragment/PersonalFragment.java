package yincheng.gggithub.ui.fragment;

import android.support.annotation.NonNull;

import yincheng.gggithub.R;
import yincheng.gggithub.mvp.contract.PersonalContract;
import yincheng.gggithub.mvp.presenter.PersonalPresenter;
import yincheng.gggithub.ui.BaseFragment;

/**
 * Created by yincheng on 2018/6/14/18:07.
 * github:luoyincheng
 */
public class PersonalFragment extends
      BaseFragment<PersonalContract.View, PersonalPresenter> implements PersonalContract.View {


   @Override public int getLayoutId() {
      return R.layout.fragment_personal;
   }

   @NonNull @Override public PersonalPresenter providePresenter() {
      return new PersonalPresenter();
   }
}
