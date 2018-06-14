package yincheng.gggithub.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import yincheng.gggithub.R;
import yincheng.gggithub.library.bottomnavigation.BottomNavigationBar;
import yincheng.gggithub.library.bottomnavigation.BottomNavigationItem;
import yincheng.gggithub.library.bottomnavigation.ShapeBadgeItem;
import yincheng.gggithub.library.bottomnavigation.TextBadgeItem;
import yincheng.gggithub.mvp.contract.MainContract;
import yincheng.gggithub.mvp.presenter.MainPresenter;
import yincheng.gggithub.provider.annotation.Recite;
import yincheng.gggithub.ui.fragment.PersonalFragment;

import static yincheng.gggithub.library.bottomnavigation.ShapeBadgeItem.SHAPE_OVAL;

/**
 * Created by yincheng on 2018/5/25/14:08.
 * github:luoyincheng
 */
public class MainActivity extends BaseActivity<MainContract.View, MainPresenter> implements
      MainContract.View {
   @BindView(R.id.bottom_navbar_main) BottomNavigationBar bottomNavigationBar;
   @BindView(R.id.container_main) FrameLayout mainContainer;
   @Nullable
   TextBadgeItem numberBadgeItem;

   @Nullable
   ShapeBadgeItem shapeBadgeItem;

   /**
    * 通过注解的方式找到需要加强记忆的代码
    */
   @Recite @Override protected void initData() {
      bottomNavigationBar.setTabSelectedListener(getPresenter());
   }

   @Override protected void initView() {
      int lastSelectedPosition = 1;
      numberBadgeItem = new TextBadgeItem()
            .setBorderWidth(4)
            .setBackgroundColorResource(R.color.pickerview_bgColor_overlay)
            .setText("" + lastSelectedPosition);

      shapeBadgeItem = new ShapeBadgeItem()
            .setShape(SHAPE_OVAL)
            .setShapeColorResource(R.color.pickerview_wheelview_textcolor_out)
            .setGravity(Gravity.TOP | Gravity.END);
      bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED_NO_TITLE);
      bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
      bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable
            .ic_view_category_black_48dp, "Categories")
            .setBadgeItem(shapeBadgeItem))
            .addItem(new BottomNavigationItem(R.drawable.ic_star_black_48dp, "Stared"))
            .addItem(new BottomNavigationItem(R.drawable.ic_trending_up_black_48dp, "Trending")
                  .setBadgeItem(numberBadgeItem))
            .addItem(new BottomNavigationItem(R.drawable.ic_person_black_48dp, "Profile"))
            .setFirstSelectedPosition(lastSelectedPosition > 2 ? 2 : lastSelectedPosition)
            .setBarBackgroundColor(R.color.colorWhite)
            .initialise();
   }

   @Override protected int getLayoutId() {
      return R.layout.app_bar_main;
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
            startActivity(new Intent(this, SearchActivity.class));
            break;
      }
   }

   /**
    * 通过presenter来执行
    */
   @Override public void onNavigationSelected(int navigationType) {
      switch (navigationType) {
         case 0:

            break;
         case 1:
            break;
         case 2:

            break;
         case 3:
            Toast.makeText(this, "22222222", Toast.LENGTH_SHORT).show();
            PersonalFragment personalFragment = new PersonalFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container_main,personalFragment);
            fragmentTransaction.commit();
            break;
      }
   }
}
