package com.shenlan.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 赵利锋 on 2016/12/29.
 */
public class Test {

//    private static String REGEX = "(?<=\\#)(.+?)(?=\\#)";
//    private static String REGEX = "\\#\\{(.+?)\\}\\#";
//    private static String INPUT = "Thedogsays：#{ffdddddf}# meow.All dogs say meow.";
//    private static String REPLACE = "AAAAAAAAAAA";
//
    public static void main(String[] args) {
//        Pattern p = Pattern.compile(REGEX);
//        // get a matcher object
//        Matcher m = p.matcher(INPUT);
//        INPUT = m.replaceAll(REPLACE);
////        String s=INPUT.replace(REGEX,"AAAAAAAAAAAAA");
//        System.out.println(INPUT);
//        PageList<?> pageList = null;
//        List<Integer> pubResList=new ArrayList<Integer>();
//        pubResList.add(1);
//        List<String> a=new ArrayList<String>();
//        System.out.print(pubResList instanceof PageList<?>);


        try {
//            Student student= (Student) Class.forName("com.shenlan.controller.Student").newInstance();
            Class<?> stu=Class.forName("com.shenlan.controller.Student");
            boolean isEmpty=stu.isAnnotationPresent(com.shenlan.controller.Annotation_my.class);
            if(isEmpty){
                Annotation [] annotations= stu.getAnnotations();
                for(Annotation annotation:annotations){
                    Annotation_my a= (Annotation_my) annotation;
                    System.out.println("=====stu=====name" + a.name() + "say" + a.say() + "age" + a.age());
                }


            }
            Method[] methods= stu.getMethods();
            for(Method method:methods){
                boolean isE=method.isAnnotationPresent(com.shenlan.controller.Annotation_my.class);
                if(isE){
                    Annotation [] annotations=  method.getAnnotations();
                    for(Annotation annotation:annotations){
                        Annotation_my a= (Annotation_my) annotation;
                        System.out.println("=====stu=====name"+a.name()+"say"+a.say()+"age"+a.age());
                    }
                }
            }

            Field [] fields=stu.getDeclaredFields();
            for(Field field:fields){
                field.setAccessible(true);
                System.out.println(field.getName());
            }
            Class<?> interfaces[]=stu.getInterfaces();
            for(Class<?> c:interfaces){
                Method[] imethod = c.getMethods();
                for(Method m:imethod){
                    System.out.println(m.getName());

                    Annotation [] annotations=  m.getAnnotations();
                    for(Annotation annotation:annotations){
                        Annotation_my a= (Annotation_my) annotation;
                        System.out.println("=====stu=====name"+a.name()+"say"+a.say()+"age"+a.age());
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
