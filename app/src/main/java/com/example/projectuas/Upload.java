package com.example.projectuas;

import com.google.firebase.database.Exclude;

public class Upload {
    private String mName;
    private String mDeskripsi;
    private String mImageUrl;
    private String mKey;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String deskripsi, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }if (deskripsi.trim().equals("")){
            deskripsi = "No desc";
        }

        mName = name;
        mDeskripsi = deskripsi;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public String getDeskripsi() {
        return mDeskripsi;
    }

    public void setDeskripsi(String mDeskripsi) {
        this.mDeskripsi = mDeskripsi;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}
