package com.example.demouser.bond;

import android.net.Uri;

import java.util.Date;

/**
 * Created by Olive on 1/16/18.
 */


public class IndividualContact {
    protected String name;
    protected String email;
    protected String phone;
    protected String lastContact;
    protected String note;
    protected Uri image;
    protected Date nextContact;


    public IndividualContact (String name, String email, String phone, String lastContact, Date nextContact, String note, Uri image) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.lastContact = lastContact;
        this.image = image;
        this.note = note;
        this.nextContact = nextContact;
        //
    }
}
