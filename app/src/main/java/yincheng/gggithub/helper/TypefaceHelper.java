package yincheng.gggithub.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:06:09 16:18
 * Github : yincheng.luo
 */
public class TypefaceHelper {

   private static Typeface typeFace;

   public static void generateTypeface(Context context) {
      typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/app_font.ttf");
   }

   public static void applyTypeface(TextView textView) {
      textView.setTypeface(typeFace);
   }

   public static Typeface getTypeface() {
      return typeFace;
   }
}
