package yincheng.gggithub.provider.network;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import yincheng.gggithub.BuildConfig;
import yincheng.gggithub.provider.service.SearchService;

/**
 * Created by yincheng on 2018/6/4/18:13.
 * github:luoyincheng
 */
public class ServiceProvider {
   public static final int pageSize = 30;
   private static OkHttpClient okhttpclient;
   private static final Gson gson = new GsonBuilder()// TODO: 2018/6/5
         .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
         .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
         .setDateFormat("yyyy-MM-dd HH:mm:ss")
         .disableHtmlEscaping()
         .setPrettyPrinting()
         .create();

   public static OkHttpClient provideOkhttpClient() {
      if (okhttpclient == null) {
         OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
         // TODO: 2018/6/5 if  debug
//         builder.addInterceptor()
         // TODO: 2018/6/5 添加拦截器
         okhttpclient = clientBuilder.build();
      }
      return okhttpclient;
   }

   private static Retrofit provideRetrofit(boolean enterprise) {
      return new Retrofit
            .Builder()
            .baseUrl(BuildConfig.REST_URL)
            .client(provideOkhttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(new GithubResponseConverter(gson))
            .build();
   }

   @NonNull public static SearchService getSearchService(boolean enterprise) {
      return provideRetrofit(enterprise).create(SearchService.class);
   }
}
