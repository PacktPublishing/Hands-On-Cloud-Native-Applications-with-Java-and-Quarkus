package com.packt.chapter8;

import java.util.StringTokenizer;

public class CustomConfigValue {

    private final String email;
    private final String user;

    public CustomConfigValue(String value) {

        StringTokenizer st = new StringTokenizer(value,";");
        this.user = st.nextToken();
        this.email = st.nextToken();

    }


    public String getEmail() {
        return email;
    }

    public String getUser() {
        return user;
    }


}
