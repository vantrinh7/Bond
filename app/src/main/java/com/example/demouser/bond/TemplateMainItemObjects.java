package com.example.demouser.bond;

import android.graphics.Bitmap;

/**
 * Created by demouser on 1/19/18.
 */


public class TemplateMainItemObjects {

    private String content;
    //private Bitmap image;

    public TemplateMainItemObjects(String content) {
        this.content = content;
        //this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public Bitmap getImageResource() {
//        return image;
//    }
//
//    public void setImageResource(String imageResource) {
//        this.image = image;
//    }
}