package yincheng.gggithub;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by yincheng on 2018/5/23/15:18.
 * github:luoyincheng
 */
public class AtomicRunnable implements Runnable {

   private static AtomicBoolean exists = new AtomicBoolean(false);


   private String name;

   public AtomicRunnable(String name) {
      this.name = name;
   }

   @Override
   public void run() {
      if (exists.compareAndSet(false, true)) {
         System.out.println(name + " enter");
         try {
            System.out.println(name + " working");
            TimeUnit.SECONDS.sleep(2);
         } catch (InterruptedException e) {
            // do nothing
         }
         System.out.println(name + " leave");
         exists.set(false);
      } else {
         System.out.println(name + " give up");
      }
   }

   public static void main(String[] args) {

      AtomicRunnable runnable1 = new AtomicRunnable("runnable1");
      AtomicRunnable runnable2 = new AtomicRunnable("runnable2");
      AtomicRunnable runnable3 = new AtomicRunnable("runnable3");
      AtomicRunnable runnable4 = new AtomicRunnable("runnable4");
      new Thread(runnable1).start();
      new Thread(runnable2).start();
      new Thread(runnable3).start();
      new Thread(runnable4).start();
   }
}
