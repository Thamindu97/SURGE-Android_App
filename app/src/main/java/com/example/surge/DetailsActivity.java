package com.example.surge;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Database.UsersMaster;

//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;

/**
 * Created by delaroy on 9/10/17.
 */

public class DetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    AccessoriesImagesAdapter accessoriesImagesAdapter;
    RecyclerView mRecyclerView;

    private static final int IMAGES_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_main);

        // Set the RecyclerView to its corresponding view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        accessoriesImagesAdapter = new AccessoriesImagesAdapter(this);
        mRecyclerView.setAdapter(accessoriesImagesAdapter);

        getLoaderManager().initLoader(IMAGES_LOADER, null, this);


    }
        @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            // Define a projection that specifies the columns from the table we care about.
            String[] projection = {
                    UsersMaster.Accessories.COLUMN_NAME_IMAGE,
            };

            // This loader will execute the ContentProvider's query method on a background thread
            return new CursorLoader(this,   // Parent activity context
                    AccessoriesImagesProvider.CONTENT_URI,   // Provider content URI to query
                    projection,             // Columns to include in the resulting Cursor
                    null,                   // No selection clause
                    null,                   // No selection arguments
                    null);
        }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        accessoriesImagesAdapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        accessoriesImagesAdapter.swapCursor(null);

    }
}
