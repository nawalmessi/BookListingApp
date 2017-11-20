package com.example.android.booklistingapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by High Spec on 10/25/2017.
 */

public class BookAsyncTaskloader extends AsyncTaskLoader {
    private String Url;

    public BookAsyncTaskloader(Context context, String url) {
        super(context);
        Url = url;
    }

    @Override
    public Object loadInBackground() {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        StringBuilder jsonData = new StringBuilder();

        try {
            URL url = new URL(Url);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                jsonData.append(line);
                line = reader.readLine();
            }


        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return jsonData.toString();
    }


}

