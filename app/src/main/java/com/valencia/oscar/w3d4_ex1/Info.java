package com.valencia.oscar.w3d4_ex1;

public class Info {
    String seed;
    int result;
    int page;
    String version;

    public Info() {
    }

    public Info(String seed, int result, int page, String version) {
        this.seed = seed;
        this.result = result;
        this.page = page;
        this.version = version;
    }

    public String getSeed() {
        return seed;
    }

    public int getResult() {
        return result;
    }

    public int getPage() {
        return page;
    }

    public String getVersion() {
        return version;
    }
}
