package yincheng.gggithub.mvp.contract;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import yincheng.gggithub.mvp.contract.base.GGContract;
import yincheng.gggithub.mvp.model.AccessTokenModel;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:05:05 20:10
 * Github : yincheng.luo
 */
public interface LoginContract {
   interface View extends GGContract.GGView {
      void onEmptyUserName(boolean isEmpty);

      void onEmptyPassword(boolean isEmpty);

      void onLoggedInSuccessfully();
   }

   interface Presenter extends GGContract.GGPresenter {
      @NonNull Uri getAuthorizationUrl();

      void onHandleAuthIntent(@Nullable Intent intent);

      void onTokenResponse(@Nullable AccessTokenModel response);

//      void onUserResponse(@Nullable )

      void login(@NonNull String username, @NonNull String password, @NonNull String
            twoFactorCode, boolean isBasicAuth, @NonNull String endPoint);
   }
}
