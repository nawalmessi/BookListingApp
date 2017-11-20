package com.example.android.booklistingapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by High Spec on 10/23/2017.
 */

public class BookUtl {
    public static ArrayList<Book> ExtractData(String jsonData) {
        String bookTitle;
        JSONArray author;
        ArrayList<Book> books = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            if (jsonObject.has("items")) {
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++) {
                    StringBuilder authorString = new StringBuilder();
                    JSONObject item = jsonArray.getJSONObject(i);
                    JSONObject volumeOf = item.getJSONObject("volumeInfo");
                    if (volumeOf.has("title")) {
                        bookTitle = volumeOf.getString("title");
                    } else bookTitle = "title N/A";
                    if (volumeOf.has("authors")) {
                        author = volumeOf.getJSONArray("authors");

                        for (int j = 0; j < author.length(); j++) {
                            authorString.append(author.getString(j) + " ");
                        }
                    } else {
                        authorString.append("Author N/A");
                    }
                    books.add(new Book(bookTitle, authorString.toString()));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return books;
    }

}
