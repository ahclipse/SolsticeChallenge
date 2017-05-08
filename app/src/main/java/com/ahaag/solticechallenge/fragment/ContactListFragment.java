package com.ahaag.solticechallenge.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahaag.solticechallenge.R;
import com.ahaag.solticechallenge.model.Contact;
import com.ahaag.solticechallenge.model.ContactList;

import java.util.List;

public class ContactListFragment extends Fragment {

    private RecyclerView mContactRecyclerView;
    private ContactAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_contact_list, container, false);
        mContactRecyclerView = (RecyclerView) v.findViewById(R.id.contact_recycler_view);
        mContactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return v;
    }

    private void updateUI() {
        ContactList contactList = ContactList.get(getActivity());
        List<Contact> contacts = contactList.getContacts();
        mAdapter = new ContactAdapter(contacts);
        mContactRecyclerView.setAdapter(mAdapter);
    }

    private class ContactHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mNameText;
        private TextView mWorkPhoneText;
        private Contact mContact;

        public ContactHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_contact, parent, false));
            itemView.setOnClickListener(this);
            mNameText = (TextView) itemView.findViewById(R.id.contact_list_name);
            mWorkPhoneText = (TextView) itemView.findViewById(R.id.contact_list_phone);
        }

        public void bind(Contact contact) {
            mContact = contact;
            mNameText.setText(mContact.getmName());
            mWorkPhoneText.setText(mContact.getmPhone().getmWork());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Hazah!", Toast.LENGTH_SHORT).show();
        }
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {

        private List<Contact> mContacts;

        public ContactAdapter(List<Contact> contacts) {
            mContacts = contacts;
        }

        @Override
        public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ContactHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ContactHolder holder, int position) {
            holder.bind(mContacts.get(position));
        }

        @Override
        public int getItemCount() {
            return mContacts.size();
        }
    }
}
