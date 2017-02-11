package com.arbuleac.chuckjokes.service.response;


import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    @SerializedName("type")
    private String type;

    @SerializedName("value")
    private T value;


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
