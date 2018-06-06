package yincheng.gggithub.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by yincheng on 2018/5/25/11:47.
 * github:luoyincheng
 */
@Getter
@Setter
@NoArgsConstructor
public class AccessTokenModel implements Parcelable {
   // TODO: 2018/5/25 getter setter NoArgsConstructor 测试

   private long id;
   private String token;
   private String hashedToken;
   private String accessToken;
   private String tokenType;

   public static Creator<AccessTokenModel> getCREATOR() {
      return CREATOR;
   }


   protected AccessTokenModel(Parcel in) {
      this.id = in.readLong();
      this.token = in.readString();
      this.hashedToken = in.readString();
      this.accessToken = in.readString();
      this.tokenType = in.readString();
   }

   @Override public void writeToParcel(Parcel dest, int flags) {
      dest.writeLong(this.id);
      dest.writeString(this.token);
      dest.writeString(this.hashedToken);
      dest.writeString(this.accessToken);
      dest.writeString(this.tokenType);
   }

   @Override public int describeContents() {
      return 0;
   }

   public static final Creator<AccessTokenModel> CREATOR = new Creator<AccessTokenModel>() {
      @Override
      public AccessTokenModel createFromParcel(Parcel in) {
         return new AccessTokenModel(in);
      }

      @Override
      public AccessTokenModel[] newArray(int size) {
         return new AccessTokenModel[size];
      }
   };

   @Override public String toString() {
      return "AccessTokenModel{" +
            "id=" + id +
            ", token='" + token + '\'' +
            ", hashedToken='" + hashedToken + '\'' +
            ", accessToken='" + accessToken + '\'' +
            ", tokenType='" + tokenType + '\'' +
            '}';
   }
}
