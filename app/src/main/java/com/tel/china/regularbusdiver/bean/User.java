package com.tel.china.regularbusdiver.bean;

import java.io.Serializable;
import java.lang.reflect.Field;


public class User implements Serializable, Cloneable {
    public String user_id;
    public String name;
    //user bean end
    public String user_token;
    public String user_secret;

    public AccountEntry[] account_entries;

    public User(String userId, String name) {
        this.user_id = userId;
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User))
            return false;
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                Object v1 = field.get(this);
                Object v2 = field.get(o);
                if (v1 == null && v2 != null)
                    return false;
                if (v1 != null && v2 == null)
                    return false;
                if (v1 != null && v2 != null && !v1.equals(v2))
                    return false;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public class AccountEntry implements Serializable {
        public String title;
        public String url;
        public String content;
    }
}
