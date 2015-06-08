package com.woowahan.intern.internproject.review;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.woowahan.intern.internproject.R;
import com.woowahan.intern.internproject.business.Business;

import java.util.ArrayList;

/**
 * Created by user on 2015. 6. 8..
 */
public class ReviewListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Review> mItemList;
    private ViewHoler holder;

    public ReviewListAdapter(Context mContext, ArrayList<Review> mItemList){
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
            convertView = mInflater.inflate(R.layout.list_item_review, parent, false);
            final ViewHoler holder = new ViewHoler();

            holder.reviewDate = (TextView) convertView.findViewById(R.id.review_date);
            holder.reviewScore = (TextView) convertView.findViewById(R.id.review_score);
            holder.reviewName = (TextView) convertView.findViewById(R.id.review_name);
            holder.reviewContent = (TextView) convertView.findViewById(R.id.review_content);


            convertView.setTag(holder);

        }
        holder = (ViewHoler) convertView.getTag();
        Review item = mItemList.get(position);

        holder.reviewDate.setText(item.getDate());
        holder.reviewScore.setText("" + item.getScore());
        holder.reviewName.setText(item.getName());
        holder.reviewContent.setText(item.getContent());


        return convertView;
    }


    private class ViewHoler{
        public TextView reviewAvgScore;
        public TextView reviewCount;
        public TextView reviewDate;
        public TextView reviewScore;
        public TextView reviewName;
        public TextView reviewContent;

    }

}
