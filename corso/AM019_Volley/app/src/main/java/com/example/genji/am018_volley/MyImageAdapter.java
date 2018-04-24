package com.example.genji.am018_volley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by genji on 2/13/16: following android developers
 */
public class MyImageAdapter extends BaseAdapter {

    private Context mContext;
    private ImageLoader mImageLoader;

    // ViewHolder pattern
    static class ViewHolder {
        ImageView imageView;
    }

    public MyImageAdapter(Context c, ImageLoader i) {
        mContext = c;
        mImageLoader = i;
    }

    @Override
    public int getCount() {
        return Images.URL.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    // create a new NetworkImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = convertView;
        final ViewHolder viewHolder;
        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // if it's not recycled, initialize some attributes
            itemView = inflater.inflate(R.layout.item, null);
            viewHolder = new ViewHolder();
            // NetworkImageView extends ImageView
            viewHolder.imageView = (ImageView)itemView.findViewById(R.id.networkImageView);
            itemView.setTag(viewHolder);
            // set image based on selected text
            NetworkImageView nImageView = (NetworkImageView) itemView.findViewById(R.id.networkImageView);
        } else {
            viewHolder = (ViewHolder)itemView.getTag();
        }
        NetworkImageView nImageView = ( NetworkImageView) viewHolder.imageView;
        // nImageView.setDefaultImageResId(R.drawable.no_image);
        // nImageView.setErrorImageResId(R.drawable.error);
        // this allows the image boundaries to be adjusted while keeping its aspect ratio
        // nImageView.setAdjustViewBounds(true);
        nImageView.setImageUrl(Images.URL[position], mImageLoader);
        return itemView;
    }
}
