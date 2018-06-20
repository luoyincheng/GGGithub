package yincheng.gggithub.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import yincheng.gggithub.R;
import yincheng.gggithub.mvp.contract.PersonalContract;
import yincheng.gggithub.mvp.presenter.PersonalPresenter;
import yincheng.gggithub.ui.BaseFragment;
import yincheng.gggithub.view.android.SwipeRefreshLayout;

/**
 * Created by yincheng on 2018/6/14/18:07.
 * github:luoyincheng
 */
public class PersonalFragment extends
      BaseFragment<PersonalContract.View, PersonalPresenter> implements PersonalContract.View {

   @BindView(R.id.refresh) SwipeRefreshLayout swipeRefreshLayout;
   @BindView(R.id.image_saber) ImageView image_saber;

   @Override protected void initData() {
   }

   @Override public int getLayoutId() {
      int drawable_width = getResources().getDrawable(R.drawable.saber).getIntrinsicWidth();
      int drawable_height = getResources().getDrawable(R.drawable.saber).getIntrinsicHeight();
      int mipmap_width = getResources().getDrawable(R.mipmap.saber).getIntrinsicWidth();
      int mipmap_height = getResources().getDrawable(R.mipmap.saber).getIntrinsicHeight();
      Log.i("saber_intrinsic:",
            "drawable_width" + ":" + drawable_width +
                  "drawable_height" + ":" + drawable_height +
                  "mipmap_width" + ":" + mipmap_width +
                  "mipmap_height" + ":" + mipmap_height
      );
      return R.layout.fragment_personal;
   }

   @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      swipeRefreshLayout.setOnRefreshListener(this);
   }

   @NonNull @Override public PersonalPresenter providePresenter() {
      return new PersonalPresenter();
   }

   @Override public void onRefresh() {
      Toast.makeText(getActivity(), "personal on refresh", Toast.LENGTH_SHORT).show();
   }
}
