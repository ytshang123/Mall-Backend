package project.linkortech.test.utils;

import java.util.Map;

public class MapUtils {

    public static void merge(Map<String,Object> map1, Map<String,Object> map2){
        if(map2==null) return;
        for(String key:map2.keySet()){
            Object obj = map2.get(key);
            if(obj instanceof Map && map1.get(key)!=null && map1.get(key) instanceof Map){
                merge((Map<String, Object>) map1.get(key),(Map<String, Object>) obj);
            }else{
                map1.put(key,obj);
            }
        }
    }
}
