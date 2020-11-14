package com.flys.tools.utils;

public class FacebookUrl {
    private String baseUrl;
    private String hash;
    private String ext;
    private String asid;

    public FacebookUrl() {
    }

    public FacebookUrl(String baseUrl, String hash) {
        this.baseUrl = baseUrl;
        this.hash = hash;
    }

    public FacebookUrl(String baseUrl, String hash, String ext) {
        this.baseUrl = baseUrl;
        this.hash = hash;
        this.ext = ext;
    }

    public FacebookUrl(String baseUrl, String hash, String ext, String asid) {
        this.baseUrl = baseUrl;
        this.hash = hash;
        this.ext = ext;
        this.asid = asid;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getAsid() {
        return asid;
    }

    public void setAsid(String asid) {
        this.asid = asid;
    }

    @Override
    public String toString() {
        return "FacebookUrl{" +
                "baseUrl='" + baseUrl + '\'' +
                ", hash='" + hash + '\'' +
                ", ext='" + ext + '\'' +
                ", asid='" + asid + '\'' +
                '}';
    }
}
