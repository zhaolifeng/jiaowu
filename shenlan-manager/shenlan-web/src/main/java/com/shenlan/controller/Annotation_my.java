package com.shenlan.controller;

import java.lang.annotation.*;

/**
 * Created by 赵利锋 on 2017/3/19.
 */
@Target(value ={ElementType.TYPE,ElementType.METHOD})
@Documented
//@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Annotation_my {

    String name() default "zlf";

    String say() default  "hello world";

    int age() default  21;
}
