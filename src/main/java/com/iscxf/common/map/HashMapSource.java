package com.iscxf.common.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenxf
 * Created on 2020/4/29
 */
public class HashMapSource {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(16);
        map.put("key1","value1");

        ConcurrentHashMap conMap = new ConcurrentHashMap(16);
//        conMap.
    }
}
