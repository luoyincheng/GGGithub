package yincheng.gggithub.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by yincheng on 2018/6/21/10:57.
 * github:luoyincheng
 */
@Getter @Setter @NoArgsConstructor
public class GithubUser implements Parcelable {
   String login;
   int id;
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
   float score;

   protected GithubUser(Parcel in) {
      login = in.readString();
      id = in.readInt();
      node_id = in.readString();
      avatar_url = in.readString();
      gravatar_id = in.readString();
      url = in.readString();
      html_url = in.readString();
      followers_url = in.readString();
      following_url = in.readString();
      gists_url = in.readString();
      starred_url = in.readString();
      subscriptions_url = in.readString();
      organizations_url = in.readString();
      repos_url = in.readString();
      events_url = in.readString();
      received_events_url = in.readString();
      type = in.readString();
      site_admin = in.readByte() != 0;
      score = in.readFloat();
   }

   public static final Creator<GithubUser> CREATOR = new Creator<GithubUser>() {
      @Override
      public GithubUser createFromParcel(Parcel in) {
         return new GithubUser(in);
      }

      @Override
      public GithubUser[] newArray(int size) {
         return new GithubUser[size];
      }
   };

   @Override public int describeContents() {
      return 0;
   }

   @Override public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(login);
      dest.writeInt(id);
      dest.writeString(node_id);
      dest.writeString(avatar_url);
      dest.writeString(gravatar_id);
      dest.writeString(url);
      dest.writeString(html_url);
      dest.writeString(followers_url);
      dest.writeString(following_url);
      dest.writeString(gists_url);
      dest.writeString(starred_url);
      dest.writeString(subscriptions_url);
      dest.writeString(organizations_url);
      dest.writeString(repos_url);
      dest.writeString(events_url);
      dest.writeString(received_events_url);
      dest.writeString(type);
      dest.writeByte((byte) (site_admin ? 1 : 0));
      dest.writeFloat(score);
   }
}
