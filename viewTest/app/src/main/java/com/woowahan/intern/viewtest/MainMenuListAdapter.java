package com.woowahan.intern.viewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Handler;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by user on 2015. 5. 28..
 */
public class PeopleListAdapter extends BaseAdapter {

    private final Handler mHandler = new Handler();

    private Context mContext;
    private LayoutInflater mInflater;
    private List<MainMenu> mItemList;

    public PeopleListAdapter(Context mContext, List<MainMenu> mItemList) {
        this.mContext = mContext;
        this.mItemList = mItemList;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_people_info, parent, false);

            ViewHoler holder = new ViewHoler();
            holder.seqTextView = (TextView) convertView.findViewById(R.id.seq_textview);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.name_textview);
            holder.ageTextView = (TextView) convertView.findViewById(R.id.age_textview);
            holder.addressTextView = (TextView) convertView.findViewById(R.id.address_textview);
//            holder.photoImageView = (ImageView) convertView.findViewById(R.id.photo_imageview);

            convertView.setTag(holder);
        }

        ViewHoler holder = (ViewHoler) convertView.getTag();
        People item = mItemList.get(position);

        holder.seqTextView.setText("" + item.getSeq());
        holder.nameTextView.setText(item.getName());
        holder.ageTextView.setText("" + item.getAge());
        holder.addressTextView.setText(item.getAddress());
//        holder.photoImageView.setImageBitmap();
        loadPhoto(holder, item);



        return convertView;
    }

//    private void loadPhoto(final ViewHoler holder, final MAinM item){
//        holder.photoImageView.setTag(item.getPhotoUrl());
//        ImageLoader.getInstance().loadImage(item.getPhotoUrl(), new SimpleImageLoadingListener(){
//
//            @Override
//            public void onLoadingStarted(String imageUri, View view) {
//                holder.photoImageView.setImageDrawable(null);
//            }
//
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                if(imageUri.equals(holder.photoImageView.getTag())) {
//                    holder.photoImageView.setImageBitmap(loadedImage);
//                }
//
//            }
//
//            @Override
//            public void onLoadingCancelled(String imageUri, View view) {
//                if(imageUri.equals(holder.photoImageView.getTag())) {
//
//                    mHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            loadPhoto(holder, item);
//                        }
//                    }, 1000);
//                }
//            }
//        });
//    }

    private class ViewHoler {
        public TextView seqTextView;
        public TextView nameTextView;
        public TextView ageTextView;
        public TextView addressTextView;
        public ImageView photoImageView;
    }
}
