package com.woowahan.intern.internproject.business;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.woowahan.intern.internproject.BaseFragment;
import com.woowahan.intern.internproject.BusinessInfoActivity;
import com.woowahan.intern.internproject.R;

/**
 * Created by user on 2015. 6. 7..
 */
public class BusinessListFragment extends BaseFragment{

    private ListView mInfoListView;
    private BusinessListAdapter mInfoListAdapter;
    private SparseArray<Business> mInfoList;
    private int BusinessId;




    public static BusinessListFragment newInstance(){
        return new BusinessListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mInfoList = new SparseArray<>();

        mInfoListAdapter = new BusinessListAdapter(getActivity(), mInfoList);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_business_list, container, false);

        mInfoListView = (ListView) layout.findViewById(R.id.business_listview);
        mInfoListView.setAdapter(mInfoListAdapter);

        mInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // move view
                Intent it = new Intent(getActivity(), BusinessInfoActivity.class);
                it.putExtra("Id", mInfoList.get(position).getId());

                startActivity(it);
            }
        });



        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();

        updateInfos();
    }

    private void updateInfos(){

        for(int i= 0; i< 10; i++) {
            mInfoList.put(i, new Business(0, i, "교촌치킨", 4.5F, 20, "200M"));
        }
        mInfoListAdapter.notifyDataSetChanged();

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //////// businessid getter setter


    public int getBusinessId() {
        return BusinessId;
    }

    public void setBusinessId(int businessId) {
        BusinessId = businessId;
    }
}
