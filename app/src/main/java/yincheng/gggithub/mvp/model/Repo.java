package yincheng.gggithub.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by yincheng on 2018/6/4/17:04.
 * github:luoyincheng
 */
@Getter @Setter @NoArgsConstructor
public class Repo implements Parcelable {
   int id;

   protected Repo(Parcel in) {
      id = in.readInt();
   }

   public static final Creator<Repo> CREATOR = new Creator<Repo>() {
      @Override
      public Repo createFromParcel(Parcel in) {
         return new Repo(in);
      }

      @Override
      public Repo[] newArray(int size) {
         return new Repo[size];
      }
   };

   @Override public int describeContents() {
      return 0;
   }

   @Override public void writeToParcel(Parcel parcel, int i) {parcel.writeInt(id);}
}
