package com.example.android.booklistingapp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    Button search;
    EditText searchBar;
    TextView emptyTextView;
    ListView listView;
    Bundle bundle;
    ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (Button) findViewById(R.id.search_button);
        searchBar = (EditText) findViewById(R.id.search_edit_text);
        emptyTextView = (TextView) findViewById(R.id.empty_text_view);
        listView = (ListView) findViewById(R.id.list_item);
        loadingBar = (ProgressBar)findViewById(R.id.loading_indicator);
        loadingBar.setVisibility(View.GONE);
        listView.setEmptyView(emptyTextView);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    String searchBarString = searchBar.getText().toString();
                    if (!searchBarString.isEmpty()) {
                        search.setEnabled(false);
                        bundle = new Bundle();
                        bundle.putString("URL", "https://www.googleapis.com/books/v1/volumes?q=" + searchBarString + "&maxResults=10");
                        if (getSupportLoaderManager().getLoader(1) != null)
                            getSupportLoaderManager().restartLoader(1, bundle, MainActivity.this).forceLoad();


                    }
                } else {
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

        getSupportLoaderManager().initLoader(1, bundle, MainActivity.this);
    }

    private void UpdateUi(String jsonData) {
        search.setEnabled(true);
        ArrayList<Book> books = BookUtl.ExtractData(jsonData);
        BookArrayAdapter adapter = new BookArrayAdapter(this, 0, books);
        listView.setAdapter(adapter);

    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        String url = "";
        if (args != null)
            url = args.getString("URL");
        return new BookAsyncTaskloader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        UpdateUi(data);

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}


