package com.woowahan.intern.internproject.main;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
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
import com.woowahan.intern.internproject.MainActivity;
import com.woowahan.intern.internproject.R;
import com.woowahan.intern.internproject.business.Business;
import com.woowahan.intern.internproject.business.BusinessList;
import com.woowahan.intern.internproject.business.BusinessListAdapter;
import com.woowahan.intern.internproject.business.BusinessListFragment;
import com.woowahan.intern.internproject.network.GsonRequest;

import java.util.ArrayList;

/**
 * Created by user on 2015. 6. 7..
 */
public class MainListFragment extends BaseFragment implements Response.Listener<MainList>, Response.ErrorListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private ListView mInfoListView;
    private MainListAdapter mInfoListAdapter;
    private ArrayList mInfoList;
    private int categoryNum;
    private String URL;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;


    public static MainListFragment newInstance() {
        return new MainListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_business_list, container, false);



        getActivity().setTitle("배달 헬미");

        mInfoList = new ArrayList();
        mInfoListAdapter = new MainListAdapter(getActivity(), mInfoList);

        mInfoListView = (ListView) layout.findViewById(R.id.business_listview);
        mInfoListView.setAdapter(mInfoListAdapter);

        mInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // move view , click -> if instance of Recommend
                if (position == 0) {
                    ((MainActivity) getActivity()).setCurrentItem(((Recommend) mInfoList.get(0)).getCategory());

                    Log.d("Listclick", "posiiton : " + position);
                } else if (position == 1) {
                    Intent it = new Intent(getActivity(), BusinessInfoActivity.class);
                    it.putExtra("Id", ((NewBusiness) mInfoList.get(position)).getId());
                    it.putExtra("Name",((NewBusiness) mInfoList.get(position)).getName());
                    startActivity(it);
                } else {
                    Intent it = new Intent(getActivity(), BusinessInfoActivity.class);
                    it.putExtra("Id", ((Business) mInfoList.get(position)).getId());
                    it.putExtra("Name", ((Business) mInfoList.get(position)).getName());
                    startActivity(it);
                }
//
            }
        });


        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        buildGoogleApiClient();
//        requestReviews();
    }

    private void updateInfos() {
        // category num
//        mInfoList.put(0, new Recommend("오늘은 짜장", 1));
//
//        mInfoList.put(1, new Recommend("석촌 교촌치킨 오픈!!!", 1));
//        mInfoList.put(2, new Business(0, 1, "교촌치킨", 4.5F, 20, 200F));

        mInfoListAdapter.notifyDataSetChanged();

    }
    ///////// GPS

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

        URL = "http://internphp.woowahan.com:8005/intern_pro1/jsonC/appHomeData?Lat=" +
                GPSLocation.getInstance().getLat() + "&Lng=" + GPSLocation.getInstance().getLng();

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



        Log.d("json",URL);

        GsonRequest reqeust = new GsonRequest(URL, MainList.class, null, this, this);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(reqeust);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Log.d("jsontest", "fail");

        Log.e("jsontest", error.getCause().toString());
    }

    @Override
    public void onResponse(MainList response) {

        Log.d("jsonttt", "" + response.getBusinessList().size());

        mInfoList.clear();

        mInfoList.add(response.getRecommend());
//
        mInfoList.add(response.getNewBusiness());
        mInfoList.addAll(response.getBusinessList());

        mInfoListAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //////// id getter setter

    public int getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(int categoryNum) {
        Log.d("testprint", "" + categoryNum);
        this.categoryNum = categoryNum;
    }
}
