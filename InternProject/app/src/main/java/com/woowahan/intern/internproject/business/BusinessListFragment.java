package com.woowahan.intern.internproject.business;

import android.content.Intent;
import android.location.Location;
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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.woowahan.intern.internproject.BaseFragment;
import com.woowahan.intern.internproject.BusinessInfoActivity;
import com.woowahan.intern.internproject.GPSLocation;
import com.woowahan.intern.internproject.R;
import com.woowahan.intern.internproject.imformation.BusinessInfomation;
import com.woowahan.intern.internproject.network.GsonRequest;

import java.util.ArrayList;

/**
 * Created by user on 2015. 6. 7..
 */
public class BusinessListFragment extends BaseFragment implements Response.Listener<BusinessList>, Response.ErrorListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private ListView mInfoListView;
    private BusinessListAdapter mInfoListAdapter;
    private ArrayList<Business> mInfoList;
    private int BusinessId;
    private String URL;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private String categoryName;


    public static BusinessListFragment newInstance(){
        return new BusinessListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_business_list, container, false);


        mInfoList = new ArrayList<>();

        mInfoListAdapter = new BusinessListAdapter(getActivity(), mInfoList);


        mInfoListView = (ListView) layout.findViewById(R.id.business_listview);
        mInfoListView.setAdapter(mInfoListAdapter);

        mInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // move view
                Intent it = new Intent(getActivity(), BusinessInfoActivity.class);
//                it.putExtra("Id", mInfoList.get(position).getId());

                it.putExtra("Id", mInfoList.get(position).getId());
                it.putExtra("Name",mInfoList.get(position).getName());
                Log.d("jsontest","1");
                startActivity(it);
            }
        });



        return layout;
    }

    /////// onstart
    @Override
    public void onStart() {
        super.onStart();

//        requestReviews();
        buildGoogleApiClient();
    }

    private void updateInfos(){

//        for(int i= 0; i< 10; i++) {
//            mInfoList.add(new Business(0, i, "교촌치킨", 4.5F, 20, 200F));
//        }
//        mInfoListAdapter.notifyDataSetChanged();

        requestReviews();

    }




    ////////// business id getter setter

    ///// google service

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle connectionHint) {


        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {

            GPSLocation.getInstance().setLat("" + mLastLocation.getLatitude());
            Log.d("json", "" + GPSLocation.getInstance().getLat());

            GPSLocation.getInstance().setLng("" + mLastLocation.getLongitude());
            Log.d("json", "" + GPSLocation.getInstance().getLng());
        }

        URL = "http://internphp.woowahan.com:8005/intern_pro1/jsonC/storeAccess?id=" + BusinessId + "&Lat=" +
                mLastLocation.getLatitude() + "&Lng=" + mLastLocation.getLongitude();

        Log.d("jsonlng", URL);

        requestReviews();

    }



    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //Todo Google Play Service Fail 에러처리.
        Log.e("json", "connection fail");
    }


    //////// GSON

    private void requestReviews() {
        Log.d("jsontest", "try request");
        GsonRequest reqeust = new GsonRequest(URL, BusinessList.class, null, this, this);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(reqeust);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Log.d("jsontest", "fail");

        Log.e("jsontest", error.getCause().toString());
    }

    @Override
    public void onResponse(BusinessList response) {


        mInfoList.clear();
        mInfoList.addAll(response.getBusinessList());
        Log.d("jsoncateList", "" + response.getBusinessList().size());

        mInfoListAdapter.notifyDataSetChanged();
    }





    //////////////// option
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
