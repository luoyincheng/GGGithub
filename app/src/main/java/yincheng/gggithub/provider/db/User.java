package yincheng.gggithub.provider.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:05:05 22:18
 * Github : yincheng.luo
 */
@Entity
public class User {
   @Id
   private long id;

   private String name;

   private String password;

   @Generated(hash = 179890708)
   public User(long id, String name, String password) {
       this.id = id;
       this.name = name;
       this.password = password;
   }

   @Generated(hash = 586692638)
   public User() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
