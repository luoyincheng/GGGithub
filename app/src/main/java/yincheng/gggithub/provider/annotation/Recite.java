package yincheng.gggithub.provider.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yincheng on 2018/6/12/18:33.
 * github:luoyincheng
 */
@Target({
      ElementType.METHOD,
      ElementType.TYPE,
      ElementType.FIELD,
      ElementType.CONSTRUCTOR,
      ElementType.LOCAL_VARIABLE,
      ElementType.PACKAGE,
      ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Recite {
}
