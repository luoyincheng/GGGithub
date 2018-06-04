package yincheng.gggithub.mvp.presenter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Arrays;

import yincheng.gggithub.helper.InputHelper;
import yincheng.gggithub.mvp.contract.LoginContract;
import yincheng.gggithub.mvp.model.AccessTokenModel;
import yincheng.gggithub.mvp.model.AuthModel;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:05:05 17:59
 * Github : yincheng.luo
 */
public class LoginPresenter extends BasePresenter<LoginContract.View>
      implements LoginContract.Presenter {

   @NonNull @Override public Uri getAuthorizationUrl() {
      return null;
   }

   @Override public void onHandleAuthIntent(@Nullable Intent intent) {

   }

   @Override public void onTokenResponse(@Nullable AccessTokenModel response) {
      Log.e("wodeshijie", response.toString());
   }

   @Override public void login(@NonNull String username, @NonNull String password, @NonNull
         String twoFactorCode, boolean isBasicAuth, @NonNull String endPoint) {
      boolean isUsernameEmpty = InputHelper.isEmpty(username);
      boolean isPasswordEmpty = InputHelper.isEmpty(password);
      if (getView() == null) return;
      getView().onEmptyUserName(isUsernameEmpty);
      getView().onEmptyPassword(isPasswordEmpty);
      if (!isUsernameEmpty && !isPasswordEmpty) {
         String autoToken = okhttp3.Credentials.basic(username, password);// TODO: 2018/5/25
         if (isBasicAuth) {
            AuthModel authModel = new AuthModel();
            authModel.setScopes(Arrays.asList("user", "repo", "gist", "notifications", "read:org"));
            authModel.setNote("com.fastaccess.github.debug");
            authModel.setClientSecret("b2d158f949d3615078eaf570ff99eba81cfa1ff9");
            authModel.setClientId("473e333123519beadd63");
            authModel.setNoteUrl("fasthub://login");
//            makeRestCall(LoginProvider.getLoginRestService(autoToken, null, null).login(authModel),
//                  accessTokenModel -> {
//                     Log.e("wodeshijie", "333");
//                     onTokenResponse(accessTokenModel);
//                  });

         }
      }
   }
}
