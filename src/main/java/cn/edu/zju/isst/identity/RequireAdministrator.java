package cn.edu.zju.isst.identity;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import cn.edu.zju.isst.entity.Administrator;

@Target({ ElementType.TYPE, ElementType.METHOD })  
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireAdministrator {
    public int[] value() default { Administrator.ADMIN_BASE };
}