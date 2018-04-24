package com.example.genji.am050_contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bAudio = (Button) findViewById(R.id.audio);
        Button bFile = (Button) findViewById(R.id.file);
        final TextView text = (TextView) findViewById(R.id.text);

        bAudio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String[] projection = new String[] {
                        MediaStore.Audio.AudioColumns.ALBUM,
                        MediaStore.Audio.AudioColumns.TITLE,
                        };
                Uri contentUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
                Cursor cursor = getContentResolver().query(contentUri,
                        projection, MediaStore.Audio.AudioColumns.IS_RINGTONE , null , null);
                        // projection, MediaStore.Files.FileColumns.PARENT + " = 'ringtones'" , null , null);
                        // projection, MediaStore.Audio.Media.DATA + " like ? ", new String[] {"%ringtones%"}, null);
                // Get the index of the columns we need.
                int albumIdx = cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM);
                int titleIdx = cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE);
                // Create an array to store the result set.
                String[] result = new String[cursor.getCount()];
                /*
                 * Iterate over the Cursor, extracting each album name and song
                 * title.
                 */
                while (cursor.moveToNext()) {
                    // Extract the song title.
                    String title = cursor.getString(titleIdx);
                    // Extract the album name.
                    String album = cursor.getString(albumIdx);
                    result[cursor.getPosition()] = title + " (" + album + ")";
                }
                // Close the Cursor.
                String allSongs = "";
                cursor.close();
                for(String line : result) allSongs += line + "\n";
                text.setText(allSongs);
            }

        });

        bFile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String[] projection = new String[] {
                        MediaStore.Files.FileColumns.TITLE,
                        MediaStore.Files.FileColumns.PARENT
                };
                Uri contentUri = MediaStore.Files.getContentUri("internal");
                Cursor cursor = getContentResolver().query(contentUri,
                        projection, null, null , null);
                int titleIdx = cursor
                        .getColumnIndexOrThrow(MediaStore.Files.FileColumns.TITLE);
                int parentIdx = cursor
                        .getColumnIndexOrThrow(MediaStore.Files.FileColumns.PARENT);
                String[] result = new String[cursor.getCount()];
                /*
                 * Iterate over the Cursor, extracting each album name and song
                 * title.
                 */
                while (cursor.moveToNext()) {
                    // Extract the song title.
                    String title = cursor.getString(titleIdx);
                    // String parent = cursor.getString(parentIdx);
                    String parent = cursor.getString(parentIdx);
                    result[cursor.getPosition()] = "[" + parent +"]/" + title;
                }
                // Close the Cursor.
                String allSongs = "";
                cursor.close();
                for(String line : result) allSongs += line + "\n";
                text.setText(allSongs);
            }

        });
    }
}
