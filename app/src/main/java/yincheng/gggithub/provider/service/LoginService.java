package yincheng.gggithub.provider.service;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import yincheng.gggithub.mvp.model.AccessTokenModel;
import yincheng.gggithub.mvp.model.AuthModel;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:05:05 20:23
 * Github : yincheng.luo
 */
public interface LoginService {

   @POST("authorizations")
   Observable<AccessTokenModel> login(@NonNull @Body AuthModel authModel);
}
