/**
 * @Copyright (C)2018, pcitech
 */
package com.expand.hucloud.common.utils;

import cn.hutool.core.collection.CollectionUtil;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author hdq
 * @time 2023/12/28 22:40
 */
public class ListUtil {

    /**
     * 将列表中的key转换成大写字母
     * @param datas
     * @return
     */
    public static List<Map<String, Object>> toUpperCaseKey(List<Map<String, Object>> datas) {
        if (null == datas) {
            return datas;
        }
        List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
        datas.forEach(map -> {
            HashMap<String, Object> newMap = new HashMap<String, Object>();
            Iterator<Entry<String, Object>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Entry<String, Object> entry = it.next();
                String key = entry.getKey().toUpperCase();
                Object value = entry.getValue();
                newMap.put(key, value);
            }
            newList.add(newMap);
        });
        return newList;
    }
    
    /**
     * 将大列表拆分成多个子列表
     * @param datas 整个列表数据
     * @param number 子列表大小
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> datas, int number){
        if(CollectionUtil.isEmpty(datas)){
            return new ArrayList<>();
        }
        List<List<T>> list = new ArrayList<List<T>>();
        int size = datas.size();
        int count = size / number;
        if(size % number == 0){
            for(int i = 0;i < count; i++){
                int fromIndex = i * number;
                int toIndex = fromIndex + number;
                list.add(datas.subList(fromIndex, toIndex));
            }
        }else{
            for(int i = 0;i < count; i++){
                int fromIndex = i * number;
                int toIndex = fromIndex + number;
                list.add(datas.subList(fromIndex, toIndex));
            }
            list.add(datas.subList(count * number, size));
        }
        
        return list;
    }
}
