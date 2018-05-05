package yincheng.gggithub.provider.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mail   : luoyincheng@gmail.com
 * Date   : 2018:05:05 16:54
 * Github : yincheng.luo
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CallOnMainThread {
   // TODO: 2018/5/5 这里并没有做具体的实现，待做。。。
}
