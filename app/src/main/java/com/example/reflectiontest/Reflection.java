package com.example.reflectiontest;

import android.util.Log;

public class Reflection {

    private String strPrivate = "strPrivate";
    public String strPublic = "strPublic";

    private void refPrivate(){

    }
    public void refPublic(String str){

        Log.d("__invoke","invoke = "+str);

    }
}
