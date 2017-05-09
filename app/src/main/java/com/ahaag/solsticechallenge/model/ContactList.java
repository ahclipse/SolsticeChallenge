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
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Singleton class representing a list of contacts retrieved from specified endpoint
 */
public class ContactList {

    private static ContactList sContactList;
    private Contact[] mContacts;
    private final String END_POINT_URL = "https://s3.amazonaws.com/technical-challenge/Contacts_v2.json";

    public static ContactList get(Context context) {
        if (sContactList == null) {
            sContactList = new ContactList(context);
        }
        return sContactList;
    }

    private ContactList(Context context) {
        RequestQueue queue = NetworkFetcher.getInstance(context).getRequestQueue();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, END_POINT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            mContacts = new ObjectMapper().readValue(response, Contact[].class);
                        } catch (JsonMappingException e) {
                            Log.e("Error: ", e.getLocalizedMessage());
                        } catch (JsonGenerationException e) {
                            Log.e("Error: ", e.getLocalizedMessage());
                        } catch (IOException e) {
                            Log.e("Error: ", e.getLocalizedMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", "Volley Error");
            }
        });
        queue.add(stringRequest);
    }

    public Contact[] getContacts() {
        return mContacts;
    }

    @Nullable
    public Contact getContact(Phone phone) {
        for (Contact contact: mContacts) {
            if (contact.getPhone().equals(phone)) {
                return contact;
            }
        }
        return null;
    }
}
