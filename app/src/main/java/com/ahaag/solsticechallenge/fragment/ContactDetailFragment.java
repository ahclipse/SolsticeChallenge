package com.ahaag.solsticechallenge.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahaag.solsticechallenge.R;
import com.ahaag.solsticechallenge.model.Contact;
import com.ahaag.solsticechallenge.model.ContactList;
import com.ahaag.solsticechallenge.network.NetworkFetcher;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.text.DateFormat;

/**
 * Presents detailed contact information to user
 *
 * @author  Adam Haag
 * @version 1.0
 * @since   2017-05-7
 */
public class ContactDetailFragment extends Fragment {

    private static final String ARG_WORK_PHONE = "work_phone";

    private Contact mContact;
    private TextView mName;
    private TextView mCompany;
    private TextView mPhoneWork;
    private TextView mPhoneHome;
    private TextView mPhoneMobile;
    private TextView mAddressStreet;
    private TextView mAddressLocation;
    private TextView mBirthday;
    private TextView mEmail;
    private TextView mBack;
    private NetworkImageView mImageView;
    private ImageView mFavorite;

    /**
     * Create new instance of contact detail fragment
     *
     * @param workPhone unique identifier
     * @return ContactDetailFragment
     */
    public static ContactDetailFragment newInstance(String workPhone) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_WORK_PHONE, workPhone);
        ContactDetailFragment fragment = new ContactDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String workPhone = getArguments().getString(ARG_WORK_PHONE);
        mContact = new ContactList(getActivity()).getContact(workPhone);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        initViews(v);
        setViews();
        return v;
    }

    /**
     * Initialize Views
     *
     * @param v current view
     */
    private void initViews(View v) {
        mName = (TextView) v.findViewById(R.id.contact_name);
        mCompany = (TextView) v.findViewById(R.id.contact_company);
        mPhoneWork = (TextView) v.findViewById(R.id.contact_phone_work);
        mPhoneHome = (TextView) v.findViewById(R.id.contact_phone_home);
        mPhoneMobile = (TextView) v.findViewById(R.id.contact_phone_mobile);
        mAddressStreet = (TextView) v.findViewById(R.id.contact_address_street);
        mAddressLocation = (TextView) v.findViewById(R.id.contact_address_city_zip);
        mBirthday = (TextView) v.findViewById(R.id.contact_birthday);
        mEmail = (TextView) v.findViewById(R.id.contact_email);
        mBack = (TextView) v.findViewById(R.id.contact_detail_back_button);
        mImageView = (NetworkImageView) v.findViewById(R.id.contact_detail_large_image);
        mFavorite = (ImageView) v.findViewById(R.id.contact_favorite);
    }

    /**
     * Set contact detail view with user's information
     */
    private void setViews() {
        if (mContact != null) {
            mName.setText(mContact.getName());
            mCompany.setText(mContact.getCompany());
            mPhoneWork.setText(mContact.getPhone().getWork());
            mPhoneHome.setText(mContact.getPhone().getHome());
            mPhoneMobile.setText(mContact.getPhone().getMobile());
            mAddressStreet.setText(mContact.getAddress().getStreet());
            mAddressLocation.setText(mContact.getAddress().getCity() + ", " +
                mContact.getAddress().getState() + " " + mContact.getAddress().getZip());
            mBirthday.setText(parseBirthDate(mContact.getBirthdate()));
            mEmail.setText(mContact.getEmail());

            mFavorite.setImageDrawable(mContact.getFavorite() ?
                    getResources().getDrawable(R.drawable.favorite_selected, null) :
                    getResources().getDrawable(R.drawable.favorite_not_selected, null));

            ImageLoader imageLoader = NetworkFetcher.getInstance(getActivity()).getImageLoader();
            imageLoader.get(mContact.getLargeImageURL(),
                    ImageLoader.getImageListener(mImageView,
                            R.drawable.photo_placeholder,
                            android.R.drawable.ic_dialog_alert));
            mImageView.setImageUrl(mContact.getLargeImageURL(), imageLoader);
        }

        mBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    /**
     * Take in birthday as a string of digits and return in readable form
     *
     * @param birthday string representation
     * @return human friendly birthday
     */
    public String parseBirthDate(String birthday) {
        return DateFormat.getDateInstance().format(Long.parseLong(birthday));
    }
}
