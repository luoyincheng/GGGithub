package yincheng.gggithub.provider.network;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by yincheng on 2018/6/4/18:21.
 * github:luoyincheng
 */
@Getter @Setter @NoArgsConstructor
public class Pageable<M extends Parcelable> implements Parcelable {
   public int first;
   public int next;
   public int prev;
   public int last;
   public int totalCount;
   public boolean incompleteResults;
   public List<M> items;

   public static final Creator<Pageable> CREATOR = new Creator<Pageable>() {
      @Override
      public Pageable createFromParcel(Parcel in) {
         return new Pageable(in);
      }

      @Override
      public Pageable[] newArray(int size) {
         return new Pageable[size];
      }
   };

   @Override public int describeContents() {
      return 0;
   }

   @Override public void writeToParcel(Parcel parcel, int i) {}
}
