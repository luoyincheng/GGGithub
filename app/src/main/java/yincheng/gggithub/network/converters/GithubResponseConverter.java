package yincheng.gggithub.network.converters;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yincheng on 2018/5/25/16:18.
 * github:luoyincheng
 */
public class GithubResponseConverter extends Converter.Factory {
   private Gson gson;

   public GithubResponseConverter(Gson gson) {
      this.gson = gson;
   }

   @Override public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[]
         annotations, Retrofit retrofit) {
      try {
         if (type == String.class) {
            return new StringResponseConverter();
         }
         return GsonConverterFactory.create(gson).responseBodyConverter(type, annotations,
               retrofit);
      } catch (OutOfMemoryError ignored) {
         return null;
      }
   }

   @Override public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[]
         parameterAnnotations,
                                                                   Annotation[]
                                                                         methodAnnotations,
                                                                   Retrofit retrofit) {
      return GsonConverterFactory.create(gson).requestBodyConverter(type, parameterAnnotations,
            methodAnnotations, retrofit);
   }

   private static class StringResponseConverter implements Converter<ResponseBody, String> {
      @Override public String convert(@NonNull ResponseBody value) throws IOException {
         return value.string();
      }
   }
}
