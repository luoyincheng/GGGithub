package yincheng.gggithub.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yincheng on 2018/6/21/16:50.
 * github:luoyincheng
 */
public class Code implements Parcelable {
   protected Code(Parcel in) {
   }

   public static final Creator<Code> CREATOR = new Creator<Code>() {
      @Override
      public Code createFromParcel(Parcel in) {
         return new Code(in);
      }

      @Override
      public Code[] newArray(int size) {
         return new Code[size];
      }
   };

   @Override public int describeContents() {
      return 0;
   }

   @Override public void writeToParcel(Parcel dest, int flags) {}
}
