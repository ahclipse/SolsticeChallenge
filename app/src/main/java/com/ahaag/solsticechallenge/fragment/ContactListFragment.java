package com.ahaag.solsticechallenge.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahaag.solsticechallenge.R;
import com.ahaag.solsticechallenge.model.Contact;
import com.ahaag.solsticechallenge.model.ContactList;


public class ContactListFragment extends Fragment {

    private RecyclerView mContactRecyclerView;
    private ContactAdapter mAdapter;
    private Contact[] mContacts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchContactsTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_contact_list, container, false);
        mContactRecyclerView = (RecyclerView) v.findViewById(R.id.contact_recycler_view);
        mContactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        updateUI();
        setUpAdapter();

        return v;
    }

//    private void updateUI() {
//        ContactList contactList = ContactList.get(getActivity());
//        Contact[] contacts = contactList.getContacts();
//        mAdapter = new ContactAdapter(contacts);
//        mContactRecyclerView.setAdapter(mAdapter);
//    }

    private void setUpAdapter() {
        if (isAdded()) {
            mContactRecyclerView.setAdapter(new ContactAdapter(mContacts));
        }
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
            mNameText.setText(mContact.getName() == null ? "" : mContact.getName());
            mWorkPhoneText.setText(mContact.getPhone().getWork() == null ? "" : mContact.getPhone().getWork());
        }

        @Override
        public void onClick(View view) {
            //TODO
        }
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {

        public ContactAdapter(Contact[] contacts) {
            mContacts = contacts;
        }

        @Override
        public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ContactHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ContactHolder holder, int position) {
            if (mContacts != null) {
                holder.bind(mContacts[position]);
            }
        }

        @Override
        public int getItemCount() {
            if (mContacts != null) {
                return mContacts.length;
            } else {
                return 0;
            }
        }
    }

    private class FetchContactsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            new ContactList().fetchContacts(getActivity(), new ContactList.ContactCallback(){
                @Override
                public void onSuccess(Contact[] result){
                    mContacts = result;
                    setUpAdapter();
                }
            });
            return null;
        }
    }
}
