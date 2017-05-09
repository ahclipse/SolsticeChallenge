package com.ahaag.solsticechallenge.fragment;


import android.content.Intent;
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
import com.ahaag.solsticechallenge.activity.ContactDetailActivity;
import com.ahaag.solsticechallenge.model.Contact;
import com.ahaag.solsticechallenge.model.ContactList;
import com.ahaag.solsticechallenge.network.NetworkFetcher;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Display available contacts in RecyclerView
 *
 * @author  Adam Haag
 * @version 1.0
 * @since   2017-05-7
 */
public class ContactListFragment extends Fragment {

    private static final String TAG = "ContactListFragment";

    private RecyclerView mContactRecyclerView;
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
        setUpAdapter();

        return v;
    }

    private void setUpAdapter() {
        if (isAdded()) {
            mContactRecyclerView.setAdapter(new ContactAdapter(mContacts));
        }
    }

    /**
     * Recycler ViewHolder implementation; binds user data to view
     */
    private class ContactHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Contact mContact;
        private TextView mNameText;
        private TextView mWorkPhoneText;
        private NetworkImageView mImageView;

        public ContactHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_contact, parent, false));
            itemView.setOnClickListener(this);
            mNameText = (TextView) itemView.findViewById(R.id.contact_list_name);
            mWorkPhoneText = (TextView) itemView.findViewById(R.id.contact_list_phone);
            mImageView = (NetworkImageView) itemView.findViewById(R.id.contact_list_image);
        }

        public void bind(Contact contact, String url, ImageLoader imageLoader) {
            mContact = contact;
            mNameText.setText(mContact.getName() == null ? "" : mContact.getName());
            mWorkPhoneText.setText(contact.getPhone().getWork() == null ? "" : mContact.getPhone().getWork());
            mImageView.setImageUrl(url, imageLoader);
        }

        @Override
        public void onClick(View view) {
            Intent intent = ContactDetailActivity.newIntent(getActivity(), mContact.getPhone().getWork());
            startActivity(intent);
        }
    }

    /**
     * Recycler adapter implementation; contains logic to download contact thumbnail image
     * via Volley
     */
    private class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {

        private ImageLoader imageLoader;

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
            if (mContacts != null && mContacts[position] != null) {
                imageLoader = NetworkFetcher.getInstance(getActivity()).getImageLoader();
                imageLoader.get(mContacts[position].getSmallImageURL(),
                        ImageLoader.getImageListener(holder.mImageView,
                                R.drawable.photo_placeholder,
                                android.R.drawable.ic_dialog_alert));
                holder.bind(mContacts[position], mContacts[position].getSmallImageURL(), imageLoader);
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

    /**
     * Retrieve contact information on child thread
     */
    private class FetchContactsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            new ContactList(getActivity()).fetchContacts(getActivity(), new ContactList.ContactCallback(){
                @Override
                public void onSuccess(Contact[] result){
                    //Contacts have been retrieved, set adapter and UI
                    mContacts = result;
                    setUpAdapter();
                }
            });
            return null;
        }
    }
}
