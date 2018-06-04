package yincheng.gggithub.provider;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import yincheng.gggithub.BuildConfig;
import yincheng.gggithub.helper.InputHelper;
import yincheng.gggithub.helper.LinkParserHelper;
import yincheng.gggithub.network.converters.GithubResponseConverter;
import yincheng.gggithub.network.interceptors.AuthenticationInterceptor;
import yincheng.gggithub.provider.service.LoginService;

/**
 * Created by yincheng on 2018/5/25/15:49.
 * github:luoyincheng
 */
public class LoginProvider {
   private final static Gson gson = new GsonBuilder()
         .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
         .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
         .setDateFormat("yyyy-MM-dd HH:mm:ss")
         .setPrettyPrinting()
         .create();

   private static OkHttpClient provideOkHttpClient(@Nullable String authToken, @Nullable String
         otp) {
      OkHttpClient.Builder client = new OkHttpClient.Builder();
      if (BuildConfig.DEBUG) {
         client.addInterceptor(new HttpLoggingInterceptor()
               .setLevel(HttpLoggingInterceptor.Level.BODY));
      }
      client.addInterceptor(new AuthenticationInterceptor(authToken, otp));
      return client.build();
   }

   private static Retrofit provideRetrofit(@Nullable String authToken, @Nullable String otp,
                                           @Nullable String enterpriseUrl) {
      return new Retrofit.Builder()
            .baseUrl(InputHelper.isEmpty(enterpriseUrl) ? BuildConfig.REST_URL : LinkParserHelper
                  .getEndpoint(enterpriseUrl))
            .client(provideOkHttpClient(authToken, otp))
            .addConverterFactory(new GithubResponseConverter(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
   }

   public static LoginService getLoginRestService() {
      return new Retrofit.Builder()
            .baseUrl("https://github.com/login/oauth/")
            .client(provideOkHttpClient(null, null))
            .addConverterFactory(new GithubResponseConverter(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(LoginService.class);
   }

   @NonNull public static LoginService getLoginRestService(@NonNull String authToken, @Nullable
         String otp,
                                                           @Nullable String endpoint) {
      return provideRetrofit(authToken, otp, endpoint).create(LoginService.class);
   }
}
