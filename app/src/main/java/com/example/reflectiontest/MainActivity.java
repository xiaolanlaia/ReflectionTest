package com.example.reflectiontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    Button center_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        center_btn = findViewById(R.id.center_btn);

        center_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getReflectionClass();
            }
        });
    }


    private void getReflectionClass(){



        // 方式一 类获取
        Class reflectionClass = Reflection.class;

        try {
            //将对象实例化，与new的区别在于，new可以带参数，newInstance()不可以
            Reflection reflection1 = (Reflection) reflectionClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 方式二 对象获取
        Reflection reflection = new Reflection();
        Class reflectionClass2 = reflection.getClass();

        // 方式三 静态获取
        Class reflectionClass3=null;
        try {
            reflectionClass3 = Class.forName("com.example.reflectiontest.Reflection");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //获取类的完整路径
        String className1 = reflectionClass.getName();


        //获取类名
        String SimpleName = reflectionClass.getSimpleName();


        //包路径
        String packageRoute = reflectionClass.getPackage().getName();

        //获取父类
        String superClassName = reflectionClass.getSuperclass().getSimpleName();

        //获取接口
        Class[] interfaces = reflectionClass.getInterfaces();

        //获取单个属性
        try {
            //获取单个属性,可以获取类中的所有字段
            Field singleField = reflectionClass.getDeclaredField("strPrivate");
            singleField.setAccessible(true);
            //获取单个共有属性，只能获取公有字段
            Field singleFieldPublic = reflectionClass.getField("strPublic");


        } catch (NoSuchFieldException e) {
            e.printStackTrace();

        }

        //获取全部属性
        Field[] fields = reflectionClass.getDeclaredFields();
        //获取全部公有属性
        Field[] fieldsPublic = reflectionClass.getFields();

        //获取和设置属性

        for (Field field : fields){
            //使得可以访问私有变量
            field.setAccessible(true);
            //属性名
            field.getName();

            //变量类型
            field.getType().getName();

            //获取该属性的值

            try {
                Reflection reflection2 = new Reflection();
                field.get(reflection2);
                field.set(reflection2,"admin");

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        try {
            //获得单个公有方法,只能获取公有方法，后面是方法所需的参数的类型
            Method publicMethod = reflectionClass.getMethod("refPublic",String.class);

            // 获取类中单个方法,可以获取所有方法，后面是方法的参数
            Method method =  reflectionClass.getDeclaredMethod("refPrivate");


            Log.d("__method",""+publicMethod.getName());

            //将方法invoke给对象(调用对象中的方法)
            publicMethod.invoke(reflection,"sendInvoke");

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("__method-err",""+e.getMessage());

        }

        //获取所有公有方法
        Method[] methods = reflectionClass.getMethods();
        //获取所有方法
        Method[] methodsAll = reflectionClass.getDeclaredMethods();

        //构造器

        //获取所有构造器，返回所有构造器
        Constructor[] constructorsAll = reflectionClass.getDeclaredConstructors();
        //获取所有公有构造器
        Constructor[] constructorsPublic = reflectionClass.getConstructors();



    }
}