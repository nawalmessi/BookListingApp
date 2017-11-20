package com.example.android.booklistingapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by High Spec on 10/23/2017.
 */

public class BookArrayAdapter extends ArrayAdapter<Book> {

    public BookArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        Book item = getItem(position);
        TextView titleTextView = (TextView) listItem.findViewById(R.id.title_text_view);
        TextView authorTextView = (TextView) listItem.findViewById(R.id.author_text_view);
        titleTextView.setText(item.getBookTitle());
        authorTextView.setText("by " + item.getAuthorString());
        return listItem;
    }
}
