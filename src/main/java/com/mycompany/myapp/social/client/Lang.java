package com.mycompany.myapp.social.client;

public enum Lang {
    RU("ru"),
    UA("ua"),
    BE("be"),
    EN("en"),
    ES("es"),
    FI("fi"),
    DE("de"),
    IT("it");

    private final String value;

    private Lang(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
