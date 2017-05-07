package com.ahaag.solticechallenge.model;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ContactList {

    private static ContactList sContactList;
    private List<Contact> mContacts;

    public static ContactList get(Context context) {
        if (sContactList == null) {
            sContactList = new ContactList(context);
            }
            return sContactList;
        }
    }

    private ContactList(Context context) {
        mContacts = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Contact contact = new Contact();
            Phone phone = new Phone();
            phone.setmWork("222-333-7676");
            contact.setmName("Andy Android");
            contact.setmPhone(phone);
            contact.setmSmallImageURL("www.purple.com");
            mContacts.add(contact);
        }
    }
}
