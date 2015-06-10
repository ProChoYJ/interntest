package com.woowahan.intern.internproject.review;

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
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.woowahan.intern.internproject.BaseFragment;
import com.woowahan.intern.internproject.R;
import com.woowahan.intern.internproject.business.Business;
import com.woowahan.intern.internproject.network.GsonRequest;

import java.util.ArrayList;

/**
 * Created by user on 2015. 6. 8..
 */
public class ReviewListFragment extends BaseFragment implements Response.Listener<Reviews>, Response.ErrorListener{



    private ListView mInfoListView;
    private ReviewListAdapter mInfoListAdapter;
    private ArrayList<Review> mInfoList;
    private int BusinessId;
    private TextView reviewCount;
    private TextView reviewAvgScore;
    private String URL = "http://internphp.woowahan.com:8005/intern_pro1/jsonC/storeReviewJson?id=";



    public static ReviewListFragment newInstance(){
        return new ReviewListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInfoList = new ArrayList<>();
        // class set constructor
        mInfoListAdapter = new ReviewListAdapter(getActivity(), mInfoList);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_business_review, container, false);

        reviewAvgScore = (TextView) layout.findViewById(R.id.review_avgscore);
        reviewCount =  (TextView) layout.findViewById(R.id.review_count);

        mInfoListView = (ListView) layout.findViewById(R.id.review_listview);
        mInfoListView.setAdapter(mInfoListAdapter);

        mInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // move view
//                Intent it = new Intent(getActivity(), BusinessInfoActivity.class);
//                it.putExtra("Id", mInfoList.get(position).getId());
//
//                startActivity(it);
            }
        });



        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        requestReviews();
//        updateInfos();
    }

    private void updateInfos(){

//        for(int i= 0; i< 10; i++) {
//            mInfoList.add(new Review("2015-01-01","4.5","홍길","호로로로로로록 호로로로로록"));
//       }
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


    //////// GSON

    private void requestReviews(){
        Log.d("jsontest", "try request");
        GsonRequest reqeust = new GsonRequest(URL+getBusinessId(), Reviews.class, null, this, this);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(reqeust);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Log.d("jsontest", "fail");

        Log.e("jsontest", error.getCause().toString());
    }

    @Override
    public void onResponse(Reviews response) {
        mInfoList.clear();
        mInfoList.addAll(response.getReviewList());
        reviewCount.setText("" + response.getReviewList().size());
        reviewAvgScore.setText(response.getAvg_Grade());
        Log.d("jsontest", "" + response.getReviewList().size());

        mInfoListAdapter.notifyDataSetChanged();
    }
}
