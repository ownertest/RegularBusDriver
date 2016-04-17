package com.tel.china.regularbusdiver.bean;

import java.io.Serializable;
import java.lang.reflect.Field;


public class User implements Serializable, Cloneable {
    public String user_id;
    public String name;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_secret() {
        return user_secret;
    }

    public void setUser_secret(String user_secret) {
        this.user_secret = user_secret;
    }

    public AccountEntry[] getAccount_entries() {
        return account_entries;
    }

    public void setAccount_entries(AccountEntry[] account_entries) {
        this.account_entries = account_entries;
    }

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
