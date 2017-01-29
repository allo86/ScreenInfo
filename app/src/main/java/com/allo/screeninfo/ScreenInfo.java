package com.allo.screeninfo;

/**
 * Created by ALLO on 29/1/17.
 */
public class ScreenInfo implements KeyValue {

    private String key;

    private String value;

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
