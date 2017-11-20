package com.example.android.booklistingapp;

/**
 * Created by High Spec on 10/23/2017.
 */

public class Book {
    private String bookTitle;
    private String authorString;

    public Book(String bookTitle, String authorString) {
        this.bookTitle = bookTitle;
        this.authorString = authorString;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthorString() {
        return authorString;
    }

}


