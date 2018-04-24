package com.example.genji.am018_volley;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class ShowActivity extends AppCompatActivity {

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        // as in android developers
        String url = Images.URL[getIntent().getIntExtra("position", 0)];
        mImageView = (ImageView) findViewById(R.id.myImage);

        /* Retrieves an image specified by the URL, displays it in the UI.
         * ImageRequest(String url, Resources resources, ContentResolver contentResolver,
         *       Response.Listener<Bitmap> listener, int maxWidth, int maxHeight, ScaleType scaleType,
         *       Config decodeConfig, Response.ErrorListener errorListener)
         *
         *       PAY ATTENTION: in android developers there is a deprecated form
         *
         */
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ShowActivity.this.mImageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        // mImageView.setImageResource(R.drawable.image_load_error);
                    }
                });
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(request);
    }
}
