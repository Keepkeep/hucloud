package com.expand.hucloud.common.utils;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

/**
 * @author hdq
 * @time 2023/12/28 22:40
 */
public class MgrMapUtil {

    /**
     * 取Map集合的差集
     */
    public static <S,T> Map<S, T> getDifferenceSetByGuava(Map<S, T> leftMap, Map<S, T> rightMap) {
        if (null != leftMap && null != rightMap) {

            Set<S> leftMapKey = leftMap.keySet();
            Set<S> rightMapKey = rightMap.keySet();
            Set<S> differenceSet = Sets.difference(leftMapKey, rightMapKey);
            Map<S, T> result = Maps.newHashMap();
            for (S key : differenceSet) {
                result.put(key, leftMap.get(key));
            }
            return result;

        } else {
            return null;
        }
    }

    /**
     * 取Map集合的并集
     */
    public static <S,T> Map<S, T> getUnionSetByGuava(Map<S, T> leftMap, Map<S, T> rightMap) {
        if (null != leftMap && null != rightMap) {

            Set<S> leftMapKey = leftMap.keySet();
            Set<S> rightMapKey = rightMap.keySet();
            Set<S> differenceSet = Sets.union(leftMapKey, rightMapKey);
            Map<S, T> result = Maps.newHashMap();
            for (S key : differenceSet) {
                if (leftMap.containsKey(key)) {
                    result.put(key, leftMap.get(key));
                } else {
                    result.put(key, rightMap.get(key));
                }
            }
            return result;

        } else {
            return null;
        }
    }

    /**
     * 取Map集合的交集（String,String）
     */
    public static <S,T> Map<S, T> getIntersectionSetByGuava(Map<S, T> leftMap, Map<S, T> rightMap) {
        if (null != leftMap && null != rightMap) {

            Set<S> leftMapKey = leftMap.keySet();
            Set<S> rightMapKey = rightMap.keySet();
            Set<S> differenceSet = Sets.intersection(leftMapKey, rightMapKey);
            Map<S, T> result = Maps.newHashMap();
            for (S key : differenceSet) {
                result.put(key, leftMap.get(key));
            }
            return result;

        } else {
            return null;
        }
    }

}
