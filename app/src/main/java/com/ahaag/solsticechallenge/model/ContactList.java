package com.ahaag.solsticechallenge.model;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ahaag.solsticechallenge.network.NetworkFetcher;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Singleton class representing a list of contacts
 *
 * @author  Adam Haag
 * @version 1.0
 * @since   2017-05-7
 */
public class ContactList {

    private static final String TAG = "ContactList";
    private final String END_POINT_URL = "https://s3.amazonaws.com/technical-challenge/Contacts_v2.json";
    private static Contact[] mContacts;

    /**
     * Default constructor
     *
     * @param context
     */
    public ContactList(Context context) {}

    /**
     * Users downloaded from END_POINT_URL and then parsed into POJOs via Jackson
     *
     * @param context the application context
     * @param callback callback to signal completion
     */
    public void fetchContacts(Context context, final ContactCallback callback) {
        RequestQueue queue = NetworkFetcher.getInstance(context).getRequestQueue();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, END_POINT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            mContacts = new ObjectMapper().readValue(response, Contact[].class);
                            callback.onSuccess(mContacts);
                        } catch (IOException e) {
                            Log.e(TAG, "Couldn't Parse Data", e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Volley Error", error);
            }
        });
        queue.add(stringRequest);
    }

    /**
     * Notify completion of download and parsing
     */
    public interface ContactCallback {
        void onSuccess(Contact[] result);
    }

    /**
     * Find specific contact by unique work phone number
     *
     * @param workPhone work phone string
     * @return contact matching contact
     */
    @Nullable
    public Contact getContact(String workPhone) {
        for (Contact contact: mContacts) {
            if (contact.getPhone().getWork().equals(workPhone)) {
                return contact;
            }
        }
        return null;
    }
}
