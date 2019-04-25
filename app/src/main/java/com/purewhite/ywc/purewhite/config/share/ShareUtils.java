package com.purewhite.ywc.purewhite.config.share;

import android.content.Context;
import android.content.SharedPreferences;

import com.purewhite.ywc.purewhite.app.AppUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * sharedPreferences工具类
 * @author yuwenchao
 */
public class ShareUtils {

    private static final String default_name="pureWhite";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static ShareUtils shareUtils;
    public ShareUtils()
    {
        if (shareUtils==null)
        {
            synchronized (ShareUtils.class)
            {
                if (shareUtils==null)
                {
                    shareUtils=new ShareUtils();
                }
            }
        }
    }


    public ShareUtils init()
    {
        return init(default_name);
    }

    public ShareUtils init(String content)
    {
        sharedPreferences = AppUtils.getContext().getSharedPreferences(default_name,Context.MODE_PRIVATE);
        return this;
    }

    public ShareUtils save()
    {
         editor = sharedPreferences.edit();
         return this;
    }

    public ShareUtils put(String key,Object object)
    {
        if (object instanceof Boolean)
        {
            editor.putBoolean(key, ((Boolean) object));
        }
        else if (object instanceof Float)
        {
            editor.putFloat(key, ((Float) object));
        }
        else if (object instanceof Integer)
        {
            editor.putInt(key, ((Integer) object));
        }
        else if (object instanceof Long)
        {
            editor.putLong(key, ((Long) object));
        }
        else if (object instanceof String)
        {
            editor.putString(key, ((String) object));
        }
        else if (object instanceof Set)
        {
            editor.putStringSet(key, ((Set<String>) object));
        }
        return this;
    }

    public ShareUtils clear()
    {
        editor.clear();
        return this;
    }

    public ShareUtils remove(String key)
    {
        editor.remove(key);
        return this;
    }


    /**
     * 取值
     * @return
     */
    public Map<String, ?> getAll()
    {
        return sharedPreferences.getAll();
    }

    public boolean getBoolean()
    {
        return getBoolean();
    }

    public float getFloat(String key)
    {
        return sharedPreferences.getFloat(key,0);
    }


    public int getInt(String key)
    {
        return sharedPreferences.getInt(key,0);
    }

    public Long getLong(String key)
    {
        return sharedPreferences.getLong(key,0);
    }

    public String getString(String key)
    {
        return sharedPreferences.getString(key,"");
    }

    public Set<String> getStringSet(String key)
    {
        return sharedPreferences.getStringSet(key,null);
    }


    /**
     * 提交
     */
    public void commit()
    {
        editor.commit();
    }

    public void apply()
    {
        editor.apply();
    }

}
