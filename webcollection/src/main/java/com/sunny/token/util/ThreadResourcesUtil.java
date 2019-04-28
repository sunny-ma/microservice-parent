package com.sunny.token.util;

import com.sunny.token.module.UserInfoDto;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

public class ThreadResourcesUtil {

    private static ThreadLocal<Map> threadLocal = new ThreadLocal<Map>();

    public static void put(String key, Object value){
        Map map = threadLocal.get();
        if (CollectionUtils.isEmpty(map)) {
            map = new HashMap();
        }
        map.put(key, value);
        threadLocal.set(map);
    }

    public static void putCurrentUser(UserInfoDto userInfoDto){
        put("currentUser", userInfoDto);
    }

    public static UserInfoDto getCurrentUser(){
        return get("curretnUser");
    }

    public static <T> T get(String key){
        Map map = threadLocal.get();
        if (CollectionUtils.isEmpty(map)) {
            return null;
        }
        return (T)map.get(key);
    }

    public static void clean(){
        threadLocal.remove();
    }
}
