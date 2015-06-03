package com.woowahan.intern.viewtest.textexample;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.woowahan.intern.viewtest.R;

import java.util.ArrayList;

/**
 * Created by user on 2015. 6. 3..
 */
public class TestInfoListAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private SparseArray<TestText> mItemList;
    private ViewHoler holder;

    public TestInfoListAdapter(Context mContext, SparseArray<TestText> mItemList){
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
            convertView = mInflater.inflate(R.layout.list_item_tmp_info, parent, false);
            final ViewHoler holder = new ViewHoler();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.name_textview);

            convertView.setTag(holder);

        }
        holder = (ViewHoler) convertView.getTag();
        String item = mItemList.get(position).getName();

        holder.nameTextView.setText(item);

            return convertView;
    }


    private class ViewHoler{
        public TextView nameTextView;

    }
}
