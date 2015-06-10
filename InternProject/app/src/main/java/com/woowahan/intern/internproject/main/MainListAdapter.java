package com.woowahan.intern.internproject.main;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.woowahan.intern.internproject.R;
import com.woowahan.intern.internproject.business.Business;

import java.util.ArrayList;

/**
 * Created by user on 2015. 6. 7..
 */
public class MainListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList mItemList;
    private ViewHoler holder;
    private RViewHodler rholder;
    private NViewHodler nholder;
    private boolean initBusiness;

    public MainListAdapter(Context mContext, ArrayList mItemList){
        this.mContext = mContext;
        this.mItemList = mItemList;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.initBusiness = true;
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
        if(position == 0) {
            if (convertView == null) { // instanceof
                convertView = mInflater.inflate(R.layout.list_item_recomend, parent, false);
                RViewHodler rholder = new RViewHodler();
                rholder.recommendText = (TextView) convertView.findViewById(R.id.recommend_textview);

                convertView.setTag(rholder);

            }

            rholder = (RViewHodler) convertView.getTag();
            Recommend item = (Recommend) mItemList.get(0);
            Log.d("test", "pos : " + position + " : " + item.getName());

            rholder.recommendText.setText(item.getName());



        }else if(position == 1){
            if (position == 1) { // instance of
                convertView = mInflater.inflate(R.layout.list_item_recomend, parent, false);
                NViewHodler nholder = new NViewHodler();
                nholder.newBusiness = (TextView) convertView.findViewById(R.id.recommend_textview);

                convertView.setTag(nholder);

            }

            nholder = (NViewHodler) convertView.getTag();
            NewBusiness item = (NewBusiness) mItemList.get(1);
            Log.d("test", "pos : " + position);
            nholder.newBusiness.setText(item.getName());


        }else{
            if (position > 1) {
                convertView = mInflater.inflate(R.layout.list_item_business, parent, false);
                ViewHoler holder = new ViewHoler();
                holder.businessStatus = (ImageView) convertView.findViewById(R.id.img_business_status);
                holder.businessName = (TextView) convertView.findViewById(R.id.business_name);
                holder.businessAscore = (TextView) convertView.findViewById(R.id.business_ascore);
                holder.businessReview = (TextView) convertView.findViewById(R.id.business_reviwcount);
                holder.businessDistance = (TextView) convertView.findViewById(R.id.business_distance);


                convertView.setTag(holder);

                Log.d("test", "pos : " + position);

            }

            holder = (ViewHoler) convertView.getTag();
            Business item = (Business) mItemList.get(position);

                holder.businessStatus.setImageResource(R.drawable.ready);

            holder.businessName.setText(item.getName());
            holder.businessAscore.setText("" + item.getaScore());
            holder.businessReview.setText("" + item.getReCount());
            holder.businessDistance.setText("" + item.getDistance());

//        holder.nameTextView.setText(item);
        }
        return convertView;
    }


    private class ViewHoler{
        public ImageView businessStatus;
        public TextView businessName;
        public TextView businessAscore;
        public TextView businessReview;
        public TextView businessDistance;

    }

    private class RViewHodler{
        public TextView recommendText;
    }

    private class NViewHodler{
        public TextView newBusiness;

    }

}
