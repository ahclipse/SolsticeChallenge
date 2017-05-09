package com.ahaag.solsticechallenge.model;


import android.content.Context;
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
 * Singleton class representing a list of contacts retrieved from specified endpoint
 */
public class ContactList {

    private static final String TAG = "ContactList";
//    private static ContactList sContactList;
    private final String END_POINT_URL = "https://s3.amazonaws.com/technical-challenge/Contacts_v2.json";

    public void fetchContacts(Context context, final ContactCallback callback) {
        RequestQueue queue = NetworkFetcher.getInstance(context).getRequestQueue();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, END_POINT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            callback.onSuccess(new ObjectMapper().readValue(response, Contact[].class));
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

    public interface ContactCallback {
        void onSuccess(Contact[] result);
    }

//    @Nullable
//    public Contact getContact(Phone phone) {
//        for (Contact contact: mContacts) {
//            if (contact.getPhone().equals(phone)) {
//                return contact;
//            }
//        }
//        return null;
//    }
}
