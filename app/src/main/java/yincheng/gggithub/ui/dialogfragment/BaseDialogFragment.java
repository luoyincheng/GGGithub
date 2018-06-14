package yincheng.gggithub.ui.dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEventSource;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import yincheng.gggithub.mvp.contract.base.GGContract;
import yincheng.gggithub.mvp.presenter.RxPresenter;
import yincheng.gggithub.mvparchitecture.TiDialogFragment;

/**
 * Created by yincheng on 2018/5/25/10:57.
 * github:luoyincheng
 */
public abstract class BaseDialogFragment<V extends GGContract.GGView, P extends RxPresenter<V>>
      extends
      TiDialogFragment<P, V> implements GGContract.GGView {
   protected GGContract.GGView ggView;
   private Unbinder unbinder;

   //Called when a fragment is first attached to its context,之后才会调用onCreate()

   @Override public void onAttach(Context context) {
      super.onAttach(context);
      if (context instanceof GGContract.GGView) ggView = (GGContract.GGView) context;
   }

   @Override public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
   }

   @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
      return super.onCreateDialog(savedInstanceState);
   }

   @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
         container, @Nullable Bundle savedInstanceState) {
      if (getLayoutId() != 0) {
         final Context context = new ContextThemeWrapper(getContext(), getContext().getTheme());
         LayoutInflater layoutInflater = inflater.cloneInContext(context);
         View view = inflater.inflate(getLayoutId(), container, false);
         unbinder = ButterKnife.bind(this, view);
         return view;
      }
      return super.onCreateView(inflater, container, savedInstanceState);
   }

   @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      onDialogFragmentCreated(view, savedInstanceState);
   }


   @Override public void onDetach() {
      super.onDetach();
      ggView = null;
   }

   @Override public void dismiss() {
      super.dismiss();
   }

   @Override public void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);
   }

   //**********************************************************************************************
   @LayoutRes protected abstract int getLayoutId();

   protected abstract void onDialogFragmentCreated(AccessibilityEventSource view, Bundle
         savedInstanceState);


   //**********************************************************************************************
   @Override public void showProgressView() { ggView.showProgressView(); }

   @Override public void hideProgressView() {ggView.hideProgressView(); }

   @Override public void showBlockingProgressView(@StringRes int resId) {
      ggView.showBlockingProgressView(resId);
   }

}
