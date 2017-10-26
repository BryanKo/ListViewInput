package com.bryankoproject.cmps121asg1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by imbko on 10/18/2017.
 */

public class Three_ListAdapter extends ArrayAdapter<PhotoList> {

    private LayoutInflater mInflater;
    private ArrayList<PhotoList> photos;
    private int mViewResourceId;

    public Three_ListAdapter(Context context, int textViewResourceId,ArrayList<PhotoList> photos) {
        super(context, textViewResourceId, photos);
        this.photos = photos;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView (int position, View convertView, ViewGroup parents) {
        convertView = mInflater.inflate(mViewResourceId,null);
        PhotoList photo = photos.get(position);

        if(photo != null) {
            TextView photoName = (TextView) convertView.findViewById(R.id.tvPhotoName);
            TextView photoDate = (TextView) convertView.findViewById(R.id.tvPhotoDate);
            TextView photoGrapher = (TextView) convertView.findViewById(R.id.tvPhotographer);

            if (photoName != null) {
                photoName.setText(photo.getPhotoName());
            }

            if (photoDate != null) {
                photoDate.setText(photo.getPhotoDate());
            }

            if (photoGrapher != null) {
                photoGrapher.setText(photo.getPhotographer());
            }

        }
        return convertView;
    }
}
