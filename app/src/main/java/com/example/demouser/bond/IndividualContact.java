package com.example.demouser.bond;

import android.net.Uri;

import java.util.Date;

/**
 * Created by Olive on 1/16/18.
 * Class to store each contact's info.
 */


public class IndividualContact {
    protected String name;
    protected String email;
    protected String phone;
    protected String lastContact;
    protected String note;
    protected String nextContact;
    protected Uri image;

    public IndividualContact (String name, String email, String phone, String lastContact, String nextContact, String note, Uri image) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.lastContact = lastContact;
        this.image = image;
        this.note = note;
        this.nextContact = nextContact;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public void setLastContact (String lastContact) {
        this.lastContact = lastContact;
    }

    public void setNextContact (String nextContact) {
        this.nextContact = nextContact;
    }

    public void setNoteContact (String note) {
        this.note = note;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

}
