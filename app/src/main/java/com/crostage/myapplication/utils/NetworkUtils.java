package com.crostage.myapplication.utils;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class NetworkUtils {

    private static final String VK_API_BASE_URL = "https://api.vk.com/";
    private static final String VK_USERS_GET = "method/users.get";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_VERSION = "v";
    private static final String PARAM_ACCESS = "access_token";

    public static URL generateUrl(String userId){
        Uri builtUri = Uri.parse(VK_API_BASE_URL+VK_USERS_GET)
                .buildUpon()
                .appendQueryParameter(PARAM_USER_ID,userId)
                .appendQueryParameter(PARAM_VERSION,"5.21")
                .appendQueryParameter(PARAM_ACCESS,"31c33c0031c33c0031c33c00fe31b55255331c331c33c0051ea06d8d541e326924f7fc9")
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        Log.i("URL","URL = "+url);
        return url;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try (InputStream is = urlConnection.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String response = reader.readLine();

            Log.i("response", "response = " + response);
            return response;
        } catch (UnknownHostException e) {
                e.printStackTrace();
                return null;
        } finally {
            urlConnection.disconnect();
        }
    }





}
