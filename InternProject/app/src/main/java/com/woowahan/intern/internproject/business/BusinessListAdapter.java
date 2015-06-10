package com.woowahan.intern.internproject.business;

import android.content.Context;
import android.media.Image;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.woowahan.intern.internproject.R;

import java.util.ArrayList;

/**
 * Created by user on 2015. 6. 7..
 */
public class BusinessListAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Business> mItemList;
    private ViewHoler holder;

    public BusinessListAdapter(Context mContext, ArrayList<Business> mItemList){
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
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_business, parent, false);
            final ViewHoler holder = new ViewHoler();
            holder.businessStatus = (ImageView) convertView.findViewById(R.id.img_business_status);
            holder.businessName = (TextView) convertView.findViewById(R.id.business_name);
            holder.businessAscore = (TextView) convertView.findViewById(R.id.business_ascore);
            holder.businessReview = (TextView) convertView.findViewById(R.id.business_reviwcount);
            holder.businessDistance = (TextView) convertView.findViewById(R.id.business_distance);


            convertView.setTag(holder);

        }
        holder = (ViewHoler) convertView.getTag();
        Business item = mItemList.get(position);
        if(item.getStatus() == 1){
            holder.businessStatus.setImageResource(R.drawable.ing);
        }else{
            holder.businessStatus.setImageResource(R.drawable.ready);
        }

        holder.businessName.setText(item.getName());
        holder.businessAscore.setText("" + item.getaScore());
        holder.businessReview.setText("" + item.getReCount());
        holder.businessDistance.setText("" + item.getDistance());

//        holder.nameTextView.setText(item);

        return convertView;
    }


    private class ViewHoler{
        public ImageView businessStatus;
        public TextView businessName;
        public TextView businessAscore;
        public TextView businessReview;
        public TextView businessDistance;

    }
}
