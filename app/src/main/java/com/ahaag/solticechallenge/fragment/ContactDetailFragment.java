package com.ahaag.solticechallenge.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahaag.solticechallenge.R;
import com.ahaag.solticechallenge.model.Contact;

public class ContactDetailFragment extends Fragment {

    private Contact mContact;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContact = new Contact();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        return v;
    }
}
