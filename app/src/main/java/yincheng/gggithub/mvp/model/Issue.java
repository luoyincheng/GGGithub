package yincheng.gggithub.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yincheng on 2018/6/21/16:50.
 * github:luoyincheng
 */
public class Issue implements Parcelable {
   protected Issue(Parcel in) {
   }

   public static final Creator<Issue> CREATOR = new Creator<Issue>() {
      @Override
      public Issue createFromParcel(Parcel in) {
         return new Issue(in);
      }

      @Override
      public Issue[] newArray(int size) {
         return new Issue[size];
      }
   };

   @Override public int describeContents() {
      return 0;
   }

   @Override public void writeToParcel(Parcel dest, int flags) {}
}
