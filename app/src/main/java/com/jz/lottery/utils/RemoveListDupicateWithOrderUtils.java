package com.jz.lottery.utils;

/**
 * Created by cheng on 2017/7/25.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveListDupicateWithOrderUtils {
    public static List removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Object element : list) {
            if (set.add(element)) {
                newList.add(element);
            }
        }
        return newList;
    }
}