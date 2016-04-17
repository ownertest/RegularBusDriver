package com.tel.china.regularbusdiver.util;

import java.util.List;


public class ListUtils {

    public static Object getIndex(List<?> list, int index) {
        if (null == list || list.size() == 0 || index < 0 || index > list.size())
            return null;
        else
            return list.get(index);
    }
}
