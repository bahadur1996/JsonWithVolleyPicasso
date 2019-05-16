package com.example.bahadur.recyclerviewjsonexample;

/**
 * Created by Bahadur on 5/15/2019.
 */

public class ExampleItem {
    private String mImageUrl;
    private String mCreator;
    private int mlike;
    public ExampleItem(String imageurl,String creator, int like){
        mImageUrl=imageurl;
        mCreator=creator;
        mlike=like;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmCreator() {
        return mCreator;
    }

    public int getLikeCount() {
        return mlike;
    }
}
