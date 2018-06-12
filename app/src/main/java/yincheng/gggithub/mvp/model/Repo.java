package yincheng.gggithub.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by yincheng on 2018/6/4/17:04.
 * github:luoyincheng
 */
@Getter
@Setter
@NoArgsConstructor
public class Repo implements Parcelable {
   int id;
   String node_id;
   String name;
   String full_name;
   Owner owner;
   @SerializedName("private")
   boolean privateX;
   String html_url;
   boolean fork;
   long size;
   String language;
   long forks;
   long stargazers_count;
   String updated_at;


   @Getter
   @Setter
   public class Owner {
      String login;
      long id;
      String node_id;
      String avatar_url;
      String gravatar_id;
      String url;
      String html_url;
      String followers_url;
      String following_url;
      String gists_url;
      String starred_url;
      String subscriptions_url;
      String organizations_url;
      String repos_url;
      String events_url;
      String received_events_url;
      String type;
      boolean site_admin;
   }

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

   @Override public void writeToParcel(Parcel parcel, int i) {
      parcel.writeInt(id);
   }
}
