package com.woowahan.intern.internproject.main;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.woowahan.intern.internproject.R;
import com.woowahan.intern.internproject.business.Business;

/**
 * Created by user on 2015. 6. 7..
 */
public class MainListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private SparseArray mItemList;
    private ViewHoler holder;
    private RViewHodler rholder;
    private boolean initBusiness;

    public MainListAdapter(Context mContext, SparseArray mItemList){
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
        if(position < 2) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_recomend, parent, false);
                RViewHodler rholder = new RViewHodler();
                rholder.recommendText = (TextView) convertView.findViewById(R.id.recommend_textview);

                convertView.setTag(rholder);

            }

            rholder = (RViewHodler) convertView.getTag();
            Recommend item = (Recommend) mItemList.get(position);
            Log.d("test", "pos : " + position);
            rholder.recommendText.setText(item.getName());

        }else{
            if (position > 1) {
                convertView = mInflater.inflate(R.layout.list_item_business, parent, false);
                ViewHoler holder = new ViewHoler();
                holder.businessName = (TextView) convertView.findViewById(R.id.business_name);
                holder.businessAscore = (TextView) convertView.findViewById(R.id.business_ascore);
                holder.businessReview = (TextView) convertView.findViewById(R.id.business_reviwcount);
                holder.businessDistance = (TextView) convertView.findViewById(R.id.business_distance);


                convertView.setTag(holder);

                Log.d("test", "pos : " + position);

            }

            holder = (ViewHoler) convertView.getTag();
            Business item = (Business) mItemList.get(position);

            holder.businessName.setText(item.getName());
            holder.businessAscore.setText("" + item.getaScore());
            holder.businessReview.setText("" + item.getReCount());
            holder.businessDistance.setText("" + item.getDistance());

//        holder.nameTextView.setText(item);
        }
        return convertView;
    }


    private class ViewHoler{
        public TextView businessName;
        public TextView businessAscore;
        public TextView businessReview;
        public TextView businessDistance;

    }

    private class RViewHodler{
        public TextView recommendText;
    }

}
