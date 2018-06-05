package yincheng.gggithub;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by yincheng on 2018/5/25/16:09.
 * github:luoyincheng
 */
public class App extends Application {
   private static App instance;

   public static App getInstance() {return instance;}

   @Override public void onCreate() {
      super.onCreate();
      instance = this;
      Logger.addLogAdapter(new AndroidLogAdapter());
   }
}
