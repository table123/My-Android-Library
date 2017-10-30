package com.example.zhaowt.myandroidlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

/**
 * Created by Witt on 17-10-27.
 * 第一篇：数据持久化
 *  第一节：SharedPreferences
 */

public class SharedPreferencesHelper {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public SharedPreferencesHelper(Context context,String fileName){
        sharedPreferences = context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /**
     * 保存数据的方法
     * @param key   键
     * @param object  值
     */
    public static void put(String key,Object object){
        if(object instanceof String){
            editor.putString(key,(String)object);
        }else if(object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        }else if(object instanceof Boolean) {
            editor.putBoolean(key, (boolean) object);
        }else if(object instanceof Long) {
            editor.putLong(key, (Long) object);
        }else if(object instanceof Float) {
            editor.putFloat(key, (Float) object);
        }else{
            editor.putString(key,object.toString());
        }
        editor.apply();     //这里可能有坑，注意commit()和apply()的区别
                            // 详情：http://blog.csdn.net/u010198148/article/details/51706483?readlog
    }

    /**
     *  获取保存数据的方法
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(String key,Object defaultObject){
        if(defaultObject instanceof String){
            return sharedPreferences.getString(key,(String)defaultObject);
        }else if(defaultObject instanceof Integer){
            return sharedPreferences.getInt(key,(Integer) defaultObject);
        }else if(defaultObject instanceof Boolean){
            return sharedPreferences.getBoolean(key,(Boolean) defaultObject);
        }else if(defaultObject instanceof Long){
            return sharedPreferences.getLong(key,(Long) defaultObject);
        }else if(defaultObject instanceof Float){
            return sharedPreferences.getFloat(key,(Float) defaultObject);
        }else {
            return sharedPreferences.getString(key,null);
        }
    }

    /**
     * 移除某个key值对应的值
     * @param key
     */
    public static void remove(String key){
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有数据
     */
    public static void clear(){
        editor.clear();
        editor.apply();
    }

    /**
     * 查询某个key是否存在
     * @param key
     * @return
     */
    public static boolean contains(String key){
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     * @return
     */
    public static Map<String,?> getAll(){
        return sharedPreferences.getAll();
    }

}
