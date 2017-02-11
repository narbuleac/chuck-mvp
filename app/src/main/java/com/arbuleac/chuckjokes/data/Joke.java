package com.arbuleac.chuckjokes.data;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Joke {

    @SerializedName("id")
    private int id;

    @SerializedName("joke")
    private String joke;

    @SerializedName("categories")
    private List<String> categories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
